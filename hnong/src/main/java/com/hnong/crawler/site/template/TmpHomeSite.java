package com.hnong.crawler.site.template;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.HNongConstant;
import com.hnong.crawler.constant.TmpConstant;
import com.hnong.crawler.exception.SpiderException;
import com.hnong.crawler.core.spider.CommonSpider;
import com.hnong.crawler.core.spider.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 抓取站点的入口
 * User: chris
 * Date: 13-7-13
 */
public abstract class TmpHomeSite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TmpHomeSite.class);
    private Spider spider = new CommonSpider(HNongConstant.getProxy()).useProxy(true);
    private TabModel tabModel;
    private int size = 0;

    public TmpHomeSite(TabModel tabModel) {
        this.tabModel = tabModel;
    }

    public String download(String url) {
        try {
            return spider.download(url, HNongConstant.CHARSET_UTF8);
        } catch (IOException e) {
            LOGGER.error("download", e);
        } catch (SpiderException e) {
            LOGGER.error("download", e);
        }
        return null;
    }

    /**
     * @return null or List
     */
    public Set<String> getTargetUrls() {
        String homeHtml = download(tabModel.getSiteBaseModel().getHomeUrl());
        Document doc = Jsoup.parse(homeHtml);
        Element pageElement = getTabModel().getNode().getElementsByTag(TmpConstant.PAGE).first();
        List<String> urls = getPageUrls(doc, pageElement);
        if (urls == null) {
            return Collections.emptySet();
        }

        String html = null;
        Set<String> ret = new HashSet<String>();
        Set<String> tmp = null;
        for (String url : urls) {
            html = download(url);
            if (StringUtil.isEmpty(html)) {
                LOGGER.warn("getTargetUrls {} failed.", url);
                continue;
            }
            tmp = parserUrl(html);
            if (ret != null) {
                LOGGER.info("{} has {} articles", url, tmp.size());
                ret.addAll(tmp);
                size += tmp.size();
            }
        }
        return ret;
    }

    /**
     * 解析home所有分页列表，没有分页则返回首页url
     *
     * @return
     */
    abstract protected List<String> getPageUrls(Document doc, Element pageElement);

    /**
     * 抓解析分页或者首页的文章url列表
     *
     * @param html
     * @return
     */
    abstract protected Set<String> parserUrl(String html);

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Spider getSpider() {
        return spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }

    public TabModel getTabModel() {
        return tabModel;
    }

    public void setTabModel(TabModel tabModel) {
        this.tabModel = tabModel;
    }
}
