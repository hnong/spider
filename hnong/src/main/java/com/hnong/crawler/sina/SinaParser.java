package com.hnong.crawler.sina;

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
public class SinaParser extends BaseParser {
    private Element node;

    @Override
    public void init(String html) {
        this.node = Jsoup.parse(html).getElementsByAttributeValue("class", "blkContainerSblk").first();
    }

    @Override
    public String parserTitle() {
        return node.getElementsByTag("h1").text();
    }

    @Override
    public String parserContent() {
        return node.getElementById("artibody").text();
    }

    @Override
    public String parserSource() {
        return node.getElementById("media_name").text();
    }

    @Override
    public String parserAuthor() {
        return "";
    }

    @Override
    public String parserTags() {
        Elements es = node.getElementsByAttributeValue("class", "content_label_list").first().getElementsByTag("a");
        StringBuilder sb = new StringBuilder();
        for (Element e : es) {
            sb.append(e.text()).append(",");
        }
        sb.substring(0, sb.length() - 2);
        return sb.toString();
    }

    @Override
    public Date parserPublishDate() {
        String vDate = node.getElementById("pub_date").text();
        return DateUtil.parseDate(vDate, "yyyy-MM-dd HH:mm:SS"); //<span id="pub_date">2013年07月07日 11:15</span>
    }
}
