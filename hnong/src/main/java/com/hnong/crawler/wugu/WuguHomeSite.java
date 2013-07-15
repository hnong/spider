package com.hnong.crawler.wugu;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.HomeSite;
import com.hnong.crawler.constant.TypeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * wugu站点抓取入口，
 * User: chris
 * Date: 13-7-13
 */
public class WuguHomeSite extends HomeSite{

    private static String PAGING_REG = "http://$.wugu.com.cn/main/index/#.html";
    private static String ARTICLE_URL = "http://$.wugu.com.cn/article/*";
    private static String HOME_URL = "http://$.wugu.com.cn/";

    //分栏的分页url模式
    private String paging_reg;// = "http://news.wugu.com.cn/main/index/#.html";
    private String article_url = "http://news.wugu.com.cn/article/*";
    //分栏的首页，用于获取是否有分页，并抓取分页最大值
    private String home_url;// = "http://news.wugu.com.cn/";

    public WuguHomeSite(TypeEnum type,String home_url,String article_url, String paging_reg) {
        super("www.wugu.com.cn",type);
        this.home_url = home_url;
        this.article_url = article_url;
        this.paging_reg = paging_reg;
    }

    /**
     *
     * @param type 信息类型
     * @param tab 子栏目名称
     */
    public WuguHomeSite(TypeEnum type,String tab) {
        super("www.wugu.com.cn",type);
        this.home_url = HOME_URL.replace("$",tab);
        this.article_url = ARTICLE_URL.replace("$",tab);
        this.paging_reg = PAGING_REG.replace("$",tab);
    }

    @Override
    protected List<String> getUrls() {
        List<String> ret = new ArrayList<String>();
        String html = download(home_url);
        if (StringUtil.isEmpty(html)) {
            return Collections.emptyList();
        }

        if (StringUtil.isEmpty(paging_reg)) {
            ret.add(home_url);
        } else {
            Elements els = Jsoup.parse(html).getElementsByAttributeValue("class","pagelist cls").first().getElementsByTag("a");
            int max = Integer.valueOf(els.last().attr("href").replaceAll("[^0-9]", ""));
            for (int i=1;i<=max;i++) {
               ret.add(paging_reg.replace("#",i+""));
            }
        }
        return ret;
    }

    @Override
    protected Set<String> parserUrl(String html) {
        if (StringUtil.isEmpty(html)) {
            return Collections.emptySet();
        }

        Elements els = Jsoup.parse(html).getElementsByAttributeValueMatching("href",ARTICLE_URL);
        if (els == null) {
          return Collections.emptySet();
        }

        Set<String> ret = new HashSet<String>();
        String href=null;
        for (Element e: els) {
           href = e.attr("href");
            ret.add(href);
        }
        return  ret;
    }
}
