package com.hnong.crawler.sitetmp;

import com.hnong.common.util.DateUtil;
import com.hnong.common.util.StringUtil;
import com.hnong.crawler.BaseParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;

/**
 * User: chris
 * Date: 13-7-14
 */
public class CommonParser extends BaseParser {
    private Element node;
    private TabModel tabModel;

    public CommonParser(TabModel tabModel) {
        this.tabModel = tabModel;
    }

    @Override
    public void init(String html) {
        //this.node = Jsoup.parse(html).getElementsByAttributeValue("class", "Armartop Arbottomline").first();
        String key = tabModel.getNode().attr(TmpConstant.CLAZZ);
        if (StringUtil.isNotEmpty(key)) {
            this.node = Jsoup.parse(html).getElementsByAttributeValue(TmpConstant.CLAZZ, key).first();
            return;
        }

        key = tabModel.getNode().attr(TmpConstant.ID);
        if (StringUtil.isNotEmpty(key)) {
            this.node = Jsoup.parse(html).getElementById(key);
            return;
        }
    }

    @Override
    public String parserTitle() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.TITLE).first();
        return parser(e);
    }

    @Override
    public String parserContent() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.CONTENT).first();
        return parser(e);
    }

    @Override
    public String parserSource() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.SOURCE).first();
        String value = parser(e);
        //todo 不同site需要做不同的处理
//        if (value != null) {
//            value = value.substring(2);
//        }
        return value;
    }

    @Override
    public String parserAuthor() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.AUTHOR).first();
        return parser(e);
    }

    @Override
    public String parserTags() {
        //Element e = tabModel.getValue().getElementsByTag(TmpConstant.TAGS).first();

        Elements es = node.getElementsByTag("i");
        StringBuilder sb = new StringBuilder();
        for (Element e : es) {
            sb.append(e.text()).append(",");
        }
        sb.substring(0, sb.length() - 2);
        return sb.toString();
    }

    @Override
    public Date parserPublishDate() {
        String vDate = node.getElementsByTag("em").text();
        return DateUtil.parseDate(vDate, "yyyy-MM-dd HH:mm:SS");
    }

    private String parser(Element e) {
        if (e.hasAttr(TmpConstant.CLAZZ)) {
            return node.getElementsByAttributeValue(TmpConstant.CLAZZ, e.attr(TmpConstant.CLAZZ)).first().text();
        }

        if (e.hasAttr(TmpConstant.ID)) {
            return node.getElementById(e.attr(TmpConstant.ID)).text();
        }

        if (e.hasAttr(TmpConstant.TAG)) {
            return node.getElementsByTag(e.attr(TmpConstant.TAG)).first().text();
        }

        if (e.hasAttr(TmpConstant.STYLE)) {
            return node.getElementsByAttributeValue(TmpConstant.STYLE, e.attr(TmpConstant.STYLE)).first().text();
        }
        return null;
    }
}
