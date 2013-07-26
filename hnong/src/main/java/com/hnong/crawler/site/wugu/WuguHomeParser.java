package com.hnong.crawler.site.wugu;

import com.hnong.common.util.DateUtil;
import com.hnong.crawler.BaseParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;

/**
 * User: chris
 * Date: 13-7-14
 */
public class WuguHomeParser extends BaseParser{
    private Element node;

    @Override
    public void init(String html) {
        this.node =  Jsoup.parse(html).getElementsByAttributeValue("class", "Armartop Arbottomline").first();
    }

    @Override
    public String parserTitle() {
        return node.getElementsByTag("h1").text();
    }

    @Override
    public String parserContent() {
        return node.getElementsByAttributeValue("style","text-indent:2em;").first().text();
    }

    @Override
    public String parserSource() {
        String value = node.getElementsByAttributeValue("class","Artime").first().text();
        value = value.substring(2);
        return value;
    }

    @Override
    public String parserAuthor() {
        return "";
    }

    @Override
    public String parserTags() {
        Elements es =  node.getElementsByTag("i");
        StringBuilder sb= new StringBuilder();
        for (Element e:es) {
            sb.append( e.text()).append(",");
        }
        sb.substring(0,sb.length() - 2);
        return sb.toString();
    }

    @Override
    public Date parserPublishDate() {
        String vDate = node.getElementsByTag("em").text();
        return DateUtil.parseDate(vDate, "yyyy-MM-dd HH:mm:SS");
    }
}
