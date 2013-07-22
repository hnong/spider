package com.hnong.crawler.sitetmp;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.HNongConstant;
import com.hnong.crawler.core.exception.SpiderException;
import com.hnong.crawler.core.spider.CommonSpider;
import com.hnong.crawler.core.spider.Spider;
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

    private int size = 0;

    public TmpHomeSite() {
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
        List<String> urls = getUrls();
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
    abstract protected List<String> getUrls();

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

}
