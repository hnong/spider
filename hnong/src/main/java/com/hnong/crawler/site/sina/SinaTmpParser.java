package com.hnong.crawler.site.sina;

import com.hnong.common.util.DateUtil;
import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.TmpConstant;
import com.hnong.crawler.site.template.TabModel;
import com.hnong.crawler.site.template.TmpSiteParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;

/**
 * User: zhong.huang
 * Date: 13-7-24
 */
public class SinaTmpParser extends TmpSiteParser {

    private TabModel tabModel;

    public SinaTmpParser(TabModel tabModel) {
        this.tabModel = tabModel;
    }

    @Override
    public Element init(Document doc) {
        String key = tabModel.getNode().attr(TmpConstant.ID);
        if (StringUtil.isNotEmpty(key)) {
            return doc.getElementById(key);
        }
        return null;
    }


    @Override
    public String parserTitle() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.TITLE).first();
        return parserElement(e);
    }

    @Override
    public String parserContent() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.CONTENT).first();
        return parserElement(e);
    }

    @Override
    public String parserSource() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.SOURCE).first();
        return parserElement(e);
    }

    @Override
    public String parserAuthor() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.AUTHOR).first();
        String v = parserElement(e);
        if (v == null) {
            return "";
        }
        return v;
    }

    @Override
    public String parserTags() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.TAGS).first();
        Elements tags = parserTagNodes(e);
        if (tags != null) {
            StringBuilder sb = new StringBuilder();
            for (Element tag : tags) {
                sb.append(tag.text()).append(",");
            }
            sb.substring(0, sb.length() - 2);
            return sb.toString();
        } else {
            return "";
        }
    }

    @Override
    public Date parserPublishDate() {
        Element e = tabModel.getNode().getElementsByTag(TmpConstant.PUBLISH_DATE).first();
        String vDate = parserElement(e);
        String format = e.attr(TmpConstant.FORMAT);
        if (StringUtil.isEmpty(vDate) || StringUtil.isEmpty(format)) {
            return null;
        }
        return DateUtil.parseDate(vDate, format);//"yyyy-MM-dd HH:mm:SS"
    }
}
