package com.hnong.crawler.sitetmp;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.HomeSite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * wugu站点抓取入口，
 * User: chris
 * Date: 13-7-13
 */
public class CommonHomeSite extends HomeSite {

    private TabModel tabModel;

    public CommonHomeSite(TabModel tabModel) {
        this.tabModel = tabModel;
        super.setName(tabModel.getName());
        super.setTypeEnum(tabModel.getType());

    }

    @Override
    protected List<String> getUrls() {
        List<String> ret = new ArrayList<String>();
        String html = download(tabModel.getSiteBaseModel().getHomeUrl());
        if (StringUtil.isEmpty(html)) {
            return Collections.emptyList();
        }

        if (StringUtil.isEmpty(tabModel.getSiteBaseModel().getPageUrl())) {
            ret.add(tabModel.getSiteBaseModel().getHomeUrl());
        } else {
            Element e = tabModel.getNode().getElementsByTag(TmpConstant.PAGE).first();
            Elements els = null;

            if (e.hasAttr(TmpConstant.CLAZZ)) {
                els = Jsoup.parse(html).getElementsByAttributeValue("class", e.attr(TmpConstant.CLAZZ)).first().getElementsByTag("a");
            } else if (e.hasAttr(TmpConstant.ID)) {
                els = Jsoup.parse(html).getElementById(e.attr(TmpConstant.ID)).getElementsByTag("a");
            }

            //TODO 分页处理存在问题 (有的没有给出最大page值)
            int max = Integer.valueOf(els.last().attr("href").replaceAll("[^0-9]", ""));
            for (int i = 1; i <= max; i++) {
                ret.add(tabModel.getSiteBaseModel().getPageUrl().replace("#", i + ""));
            }
        }
        return ret;
    }

    @Override
    protected Set<String> parserUrl(String html) {
        if (StringUtil.isEmpty(html)) {
            return Collections.emptySet();
        }

        Elements els = Jsoup.parse(html).getElementsByAttributeValueMatching("href", tabModel.getSiteBaseModel().getArticleUrl());
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
