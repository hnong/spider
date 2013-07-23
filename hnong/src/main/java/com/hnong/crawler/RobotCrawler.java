package com.hnong.crawler;

import com.hnong.crawler.site.SiteTabCrawler;
import com.hnong.crawler.site.sina.SinaSite;
import com.hnong.crawler.site.template.CommonSiteParser;
import com.hnong.crawler.site.template.TabModel;
import com.hnong.crawler.site.template.TmpConfig;
import com.hnong.crawler.site.template.TmpHomeSite;
import com.hnong.crawler.site.wugu.WuguSite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chris
 * Date: 13-7-14
 */
public class RobotCrawler {
    private static final int THREAD_SIZE = 5;
    protected static final ExecutorService executorService = Executors.newScheduledThreadPool(THREAD_SIZE);

    public List<SiteTabCrawler> submit() {
        List<SiteTabCrawler> ret = new ArrayList<SiteTabCrawler>();

        for (TabModel model : TmpConfig.getTabModels()) {
            if (model.getSiteBaseModel().getName().contains("wugu")) {
                TmpHomeSite news = new WuguSite(model);
                CommonSiteParser newsPar = new CommonSiteParser(model);
                ret.add(new SiteTabCrawler(news, newsPar));
            } else if (model.getSiteBaseModel().getName().contains("sina")) {
               TmpHomeSite news = new SinaSite(model);
                CommonSiteParser newsPar = new CommonSiteParser(model);
                ret.add(new SiteTabCrawler(news, newsPar));
            }
        }
        return ret;
    }

    public void start() {
        List<SiteTabCrawler> tabs = submit();
        for (SiteTabCrawler tab : tabs) {
            executorService.submit(tab);
        }
    }
}
