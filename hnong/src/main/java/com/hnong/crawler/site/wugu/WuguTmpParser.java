package com.hnong.crawler.site.wugu;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.TmpConstant;
import com.hnong.crawler.site.template.TabModel;
import com.hnong.crawler.site.template.TmpSiteParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;

/**
 * User: zhong.huang
 * Date: 13-7-24
 */
public class WuguTmpParser extends TmpSiteParser {

    private TabModel tabModel;

    public WuguTmpParser(TabModel tabModel) {
        this.tabModel = tabModel;
    }

    @Override
    public Element init(Document doc) {
        String key = tabModel.getNode().attr(TmpConstant.CLAZZ);
        if (StringUtil.isNotEmpty(key)) {
            return doc.getElementsByAttributeValue(TmpConstant.CLAZZ, key).first();
        }
        return null;
    }


    @Override
    public String parserTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String parserContent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String parserSource() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String parserAuthor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String parserTags() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Date parserPublishDate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
