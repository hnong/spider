package com.hnong.crawler;

import com.hnong.crawler.constant.TypeEnum;
import com.hnong.crawler.sina.SinaHomeSite;
import com.hnong.crawler.sina.SinaParser;
import com.hnong.crawler.wugu.WuguHomeSite;
import com.hnong.crawler.wugu.WuguParser;

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
        BaseParser newsPar = new WuguParser();
        ret.add(new TabCrawler(news, newsPar));

        HomeSite market = new WuguHomeSite(TypeEnum.MARKET, "market");
        BaseParser marketPar = new WuguParser();
        ret.add(new TabCrawler(market, marketPar));

        HomeSite nyscdt = new SinaHomeSite(TypeEnum.MARKET, "nyscdt");
        BaseParser nyscdtPar = new SinaParser();
        ret.add(new TabCrawler(nyscdt, nyscdtPar));

        HomeSite nygszx = new SinaHomeSite(TypeEnum.NEWS, "nygszx");
        BaseParser nygszxPar = new SinaParser();
        ret.add(new TabCrawler(nygszx, nygszxPar));

        return ret;
    }

    public void start() {
        List<TabCrawler> tabs = submit();
        for (TabCrawler tab: tabs) {
            executorService.submit(tab);
        }
    }
}
