package com.hnong.crawler;

import com.hnong.crawler.constant.TypeEnum;
import com.hnong.crawler.site.sina.SinaHomeSite;
import com.hnong.crawler.site.wugu.WuguHomeParser;
import com.hnong.crawler.site.wugu.WuguHomeSite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: chris
 * Date: 13-7-14
 */
public class BootCrawler {
    private static final int THREAD_SIZE = 5;
    protected static final ExecutorService executorService = Executors.newScheduledThreadPool(THREAD_SIZE);

    public List<TabCrawler> submit() {
        List<TabCrawler> ret = new ArrayList<TabCrawler>();

        HomeSite news = new WuguHomeSite(TypeEnum.NEWS, "news");
        BaseParser newsPar = new WuguHomeParser();
        ret.add(new TabCrawler(news, newsPar));

        HomeSite market = new WuguHomeSite(TypeEnum.MARKET, "market");
        BaseParser marketPar = new WuguHomeParser();
        ret.add(new TabCrawler(market, marketPar));

        HomeSite nyscdt = new SinaHomeSite(TypeEnum.MARKET, "nyscdt");
        BaseParser nyscdtPar = new WuguHomeParser();
        ret.add(new TabCrawler(nyscdt, nyscdtPar));

        HomeSite nygszx = new SinaHomeSite(TypeEnum.NEWS, "nygszx");
        BaseParser nygszxPar = new WuguHomeParser();
        ret.add(new TabCrawler(nygszx, nygszxPar));

        return ret;
    }

    public void start() {
        List<TabCrawler> tabs = submit();
        for (TabCrawler tab : tabs) {
            executorService.submit(tab);
        }
    }
}
