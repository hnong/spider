package com.hnong.crawler.site.template;

import com.hnong.common.util.DateUtil;
import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.TmpConstant;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;

/**
 * User: chris
 * Date: 13-7-14
 */
public class CommonSiteParser extends TmpSiteParser {

    private TabModel tabModel;

    public CommonSiteParser(TabModel tabModel) {
        this.tabModel = tabModel;
    }

    @Override
    public Element init(Document doc) {
        String key = tabModel.getNode().attr(TmpConstant.CLAZZ);
        if (StringUtil.isNotEmpty(key)) {
            return doc.getElementsByAttributeValue(TmpConstant.CLAZZ, key).first();
        }

        key = tabModel.getNode().attr(TmpConstant.ID);
        if (StringUtil.isNotEmpty(key)) {
            return doc.getElementById(key);
        }
        return null;
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
        String v = parser(e);
        if (v == null) {
            return "";
        }
        return v;
    }

    @Override
    public String parserTags() {
        Elements es = getNode().getElementsByTag("i");
        StringBuilder sb = new StringBuilder();
        for (Element e : es) {
            sb.append(e.text()).append(",");
        }
        sb.substring(0, sb.length() - 2);
        return sb.toString();
    }

    @Override
    public Date parserPublishDate() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.PUBLISH_DATE).first();
        String vDate = parser(e);
        String format = e.attr(TmpConstant.FORMAT);
        if (StringUtil.isEmpty(vDate) || StringUtil.isEmpty(format)) {
            return null;
        }
        return DateUtil.parseDate(vDate, format);//"yyyy-MM-dd HH:mm:SS"
    }

    private String parser(Element e) {
        if (e == null) {
            return null;
        }
        if (e.hasAttr(TmpConstant.CLAZZ)) {
            return getNode().getElementsByAttributeValue(TmpConstant.CLAZZ, e.attr(TmpConstant.CLAZZ)).first().text();
        }

        if (e.hasAttr(TmpConstant.ID)) {
            return getNode().getElementById(e.attr(TmpConstant.ID)).text();
        }

        if (e.hasAttr(TmpConstant.TAG)) {
            return getNode().getElementsByTag(e.attr(TmpConstant.TAG)).first().text();
        }

        if (e.hasAttr(TmpConstant.STYLE)) {
            return getNode().getElementsByAttributeValue(TmpConstant.STYLE, e.attr(TmpConstant.STYLE)).first().text();
        }

        return null;
    }
}
