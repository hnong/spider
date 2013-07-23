package com.hnong.crawler.site.template;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.TmpConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * wugu站点抓取入口，
 * User: chris
 * Date: 13-7-13
 */
public abstract class CommonHomeSite extends TmpHomeSite {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonHomeSite.class);

    public CommonHomeSite(TabModel tabModel) {
        super(tabModel);
    }

    @Override
    protected List<String> getPageUrls(Document doc, Element pageElement) {
        List<String> ret = new ArrayList<String>();

        if (doc != null) {
            return Collections.emptyList();
        }

        if (StringUtil.isEmpty(getTabModel().getSiteBaseModel().getPageUrl())) {
            ret.add(getTabModel().getSiteBaseModel().getHomeUrl());
        } else {
            //for max
            int max = getMaxPageSize(doc, pageElement);
            if (max <= 0) {
                max = getTabModel().getPageMax();
            }

            if (max <= 0) {
                LOGGER.error("parser max page error...");
                return Collections.emptyList();
            }

            // for start
            int start = getTabModel().getPageStart();
            if (start <= 0) {
                start = 1;
            }

            for (int i = start; i <= max; i++) {
                ret.add(getTabModel().getSiteBaseModel().getPageUrl().replace("#", i + ""));
            }
        }
        return ret;
    }

    @Override
    protected Set<String> parserUrl(String html) {
        if (StringUtil.isEmpty(html)) {
            return Collections.emptySet();
        }

        Elements els = Jsoup.parse(html).getElementsByAttributeValueMatching("href", getTabModel().getSiteBaseModel().getArticleUrl());
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

    /**
     * 对于页面没有给出最大值的分页站点，需要冲洗此方法
     *
     * @param doc
     * @return
     */
    protected int getMaxPageSize(Document doc, Element e) {
        if (doc == null && e == null) {
            return -1;
        }

        Elements els = null;
        if (e.hasAttr(TmpConstant.CLAZZ)) {
            els = doc.getElementsByAttributeValue(TmpConstant.CLAZZ, e.attr(TmpConstant.CLAZZ)).first().getElementsByTag("a");
        } else if (e.hasAttr(TmpConstant.ID)) {
            els = doc.getElementById(e.attr(TmpConstant.ID)).getElementsByTag("a");
        } else {
            return -1;
        }

        String maxV = els.last().attr("href").replaceAll("[^0-9]", "");
        if (StringUtil.isNumeric(maxV)) {
            return Integer.valueOf(maxV);
        }

        return -1;
    }
}
