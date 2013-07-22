package com.hnong.crawler.sitetmp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zhong.huang
 * Date: 13-7-19
 */
public class TmpConfig {
     private static final Logger LOGGER = LoggerFactory.getLogger(TmpConfig.class);
     private static List<TabModel> tabModels = new ArrayList<TabModel>();

    private void load() {
        try {
            File tmpFile = new File("/Users/chris/hnong/spider/hnong/src/main/resources/site_tmp.xml");
            Elements sites = Jsoup.parse(tmpFile,"utf-8").getElementsByTag("site");

            SiteBaseModel siteBaseModel = null;
            TabModel tabModel = null;

            String key = null;
            String value = null;
            List<Attribute> attrs = null;
            Elements tabs = null;
            for (Element site : sites) {
                attrs =  site.attributes().asList();
                siteBaseModel = new SiteBaseModel();

                for (Attribute attr:  attrs) {
                    tabModel = new TabModel();
                    key = attr.getKey();
                    value = attr.getValue();

                    // for siteBaseModel
                    if (key.equals(TmpConstant.NAME)) {
                        siteBaseModel.setName(value);
                    }

                    if (key.equals(TmpConstant.CHARSET)) {
                        siteBaseModel.setCharset(value);
                    }

                    if (key.equals(TmpConstant.HOME_URL)) {
                        siteBaseModel.setHomeUrl(value);
                    }

                    if (key.equals(TmpConstant.ARTICLE_URL)) {
                        siteBaseModel.setArticleUrl(value);
                    }

                    if (key.equals(TmpConstant.PAGE_URL)) {
                        siteBaseModel.setPageUrl(value);
                    }
                }

                // for tab
                tabs = site.children();

                for (Element tab : tabs) {
                    tabModel.setName(tab.attr(TmpConstant.NAME));
                    tabModel.setType(tab.attr(TmpConstant.TYPE));


                }

            }
            
        } catch (Exception e) {
            LOGGER.error("init ConfigLoader error:" + e.getMessage());
            throw new ExceptionInInitializerError("Failed to load config ");
        }
    }
    
}
