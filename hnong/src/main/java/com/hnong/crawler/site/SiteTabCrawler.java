package com.hnong.crawler.site;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.BaseModel;
import com.hnong.crawler.constant.TypeEnum;
import com.hnong.crawler.exception.TmpException;
import com.hnong.crawler.site.template.CommonSiteParser;
import com.hnong.crawler.site.template.TmpHomeSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Set;

/**
 * User: chris
 * Date: 13-7-14
 */
public class SiteTabCrawler implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteTabCrawler.class);
    private TmpHomeSite site;
    private CommonSiteParser parser;

    public SiteTabCrawler(TmpHomeSite site, CommonSiteParser parser) {
        this.site = site;
        this.parser = parser;
    }

    @Override
    public void run() {
        ///获取数据页面url
        Set<String> ret = site.getTargetUrls();
        if (ret == null) {
            return;
        }

        String html = "";
        Document doc = null;
        BaseModel model = null;
        for (String target : ret) {
            html = site.download(target);
            doc = Jsoup.parse(html);
            try {
                model = parser.parser(doc);
            } catch (TmpException e) {
                LOGGER.error("SiteTabCrawler parser {} error. ", target, e);
                //todo
            }
            model.setUrl(target);
            model.setMd5Url(StringUtil.md5(target));
            model.setSiteName(site.getTabModel().getSiteBaseModel().getName());
            model.setType(site.getTabModel().getTypInt());
            model.setCreateDate(new Date());
        }
    }
}
