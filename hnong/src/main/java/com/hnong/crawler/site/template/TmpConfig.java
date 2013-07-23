package com.hnong.crawler.site.template;

import com.hnong.crawler.constant.TmpConstant;
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

    public TmpConfig() {
        try {
            File tmpFile = new File("/Users/chris/hnong/spider/hnong/src/main/resources/site_tmp.xml");
            Elements sites = Jsoup.parse(tmpFile, "utf-8").getElementsByTag("site");

            SiteBaseModel siteBaseModel = null;
            TabModel tabModel = null;

            String key = null;
            String value = null;
            String tabName = null;
            List<Attribute> attrs = null;
            Elements tabs = null;
            for (Element site : sites) {
                attrs = site.attributes().asList();
                siteBaseModel = new SiteBaseModel();

                for (Attribute attr : attrs) {
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
                    tabName = tab.attr(TmpConstant.NAME);
                    tabModel.setName(tabName);

                    tabModel.setType(tab.attr(TmpConstant.TYPE));
                    siteBaseModel.setArticleUrl(siteBaseModel.getArticleUrl().replace(TmpConstant.TAB_CHAR, tabName));
                    siteBaseModel.setPageUrl(siteBaseModel.getPageUrl().replace(TmpConstant.TAB_CHAR, tabName));
                    siteBaseModel.setHomeUrl(siteBaseModel.getHomeUrl().replace(TmpConstant.TAB_CHAR, tabName));

                    tabModel.setNode(tab);
                    tabModel.setSiteBaseModel(siteBaseModel);
                    tabModels.add(tabModel);
                }
            }

        } catch (Exception e) {
            LOGGER.error("init ConfigLoader error:" + e.getMessage());
            throw new ExceptionInInitializerError("Failed to load config ");
        }
    }

    public static List<TabModel> getTabModels() {
        return tabModels;
    }

    public static void setTabModels(List<TabModel> tabModels) {
        TmpConfig.tabModels = tabModels;
    }
}
