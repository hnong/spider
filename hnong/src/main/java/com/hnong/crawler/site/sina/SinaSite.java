package com.hnong.crawler.site.sina;

import com.hnong.crawler.site.template.CommonHomeSite;
import com.hnong.crawler.site.template.TabModel;
import org.jsoup.nodes.Document;

import javax.lang.model.element.Element;

/**
 * wugu站点抓取入口，
 * User: chris
 * Date: 13-7-13
 */
public class SinaSite extends CommonHomeSite {

    public SinaSite(TabModel tabModel) {
        super(tabModel);
    }

    protected int getMaxPageSize(Document doc, Element page) {
        return -1;
    }

}
