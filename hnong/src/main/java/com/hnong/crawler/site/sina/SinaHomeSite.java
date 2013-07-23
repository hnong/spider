package com.hnong.crawler.site.sina;

import com.hnong.crawler.HomeSite;
import com.hnong.crawler.constant.TypeEnum;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * todo 分页不号处理，没有给出最大分页值
 * sina站点抓取入口，
 * User: chris
 * Date: 13-7-13
 */
public class SinaHomeSite extends HomeSite {

    private static String PAGING_REG = "http://roll.finance.sina.com.cn/finance/ny2/$/index_#.shtml";
    private static String ARTICLE_URL = "http://finance.sina.com.cn/nongye/$/*.shtml"; //20130707/111516040583
    private static String HOME_URL = "http://roll.finance.sina.com.cn/finance/ny2/$/index.shtml"; //nyscdt

    //分栏的分页url模式
    private String paging_reg;// = "http://news.wugu.com.cn/main/index/#.html";
    private String article_url;// = "http://news.wugu.com.cn/article/*";
    //分栏的首页，用于获取是否有分页，并抓取分页最大值
    private String home_url;// = "http://news.wugu.com.cn/";

    public SinaHomeSite(TypeEnum type, String home_url, String article_url, String paging_reg) {
        super("nongye.sina.com.cn", type);
        this.home_url = home_url;
        this.article_url = article_url;
        this.paging_reg = paging_reg;
    }

    /**
     * @param type 信息类型
     * @param tab  子栏目名称
     */
    public SinaHomeSite(TypeEnum type, String tab) {
        super("nongye.sina.com.cn", type);
        this.home_url = HOME_URL.replace("$", tab);
        this.article_url = ARTICLE_URL.replace("$", tab);
        this.paging_reg = PAGING_REG.replace("$", tab);
    }

    @Override
    protected List<String> getUrls() {
        List<String> ret = new ArrayList<String>();
        String html = download(home_url);
        if (StringUtil.isBlank(html)) {
            return Collections.emptyList();
        }

        if (StringUtil.isBlank(paging_reg)) {
            ret.add(home_url);
        } else {
            Elements els = Jsoup.parse(html).getElementsByAttributeValue("class", "pagebox").first().getElementsByTag("a");
            int max = Integer.valueOf(els.last().attr("href").replaceAll("[^0-9]", ""));
            //<a href="./index_10.shtml" title="下5页">下5页</a>
            for (int i = 1; i <= max; i++) {
                ret.add(paging_reg.replace("#", i + ""));
            }
        }
        return ret;
    }

    @Override
    protected Set<String> parserUrl(String html) {
        if (StringUtil.isBlank(html)) {
            return Collections.emptySet();
        }

        Elements els = Jsoup.parse(html).getElementsByAttributeValueMatching("href", ARTICLE_URL);
        if (els == null) {
            return Collections.emptySet();
        }

        Set<String> ret = new HashSet<String>();
        String href = null;
        for (Element e : els) {
            href = e.attr("href");
            ret.add(href);
        }
        return ret;
    }
}
