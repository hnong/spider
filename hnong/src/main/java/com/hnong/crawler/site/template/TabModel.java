package com.hnong.crawler.site.template;

import com.hnong.common.util.StringUtil;
import com.hnong.crawler.constant.SiteEnum;
import com.hnong.crawler.constant.TmpConstant;
import com.hnong.crawler.constant.TypeEnum;
import org.jsoup.nodes.Element;

import java.io.Serializable;

/**
 * User: chris
 * Date: 13-7-20
 */
public class TabModel implements Serializable {

    private SiteBaseModel siteBaseModel;
    // for tab arr
    private String name;
    private String type;

    // for tab node
    private Element node;

    public SiteBaseModel getSiteBaseModel() {
        return siteBaseModel;
    }

    public void setSiteBaseModel(SiteBaseModel siteBaseModel) {
        this.siteBaseModel = siteBaseModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Element getNode() {
        return node;
    }

    public void setNode(Element node) {
        this.node = node;
    }

    /**
     * 返回Page 的max值
     *
     * @return -1 or grate 0
     */
    public int getPageMax() {
        String maxV = node.getElementsByTag(TmpConstant.PAGE).first().attr(TmpConstant.MAX);
        if (StringUtil.isNumeric(maxV)) {
            return Integer.valueOf(maxV);
        } else {
            return -1;
        }
    }

    /**
     * 返回Page 的max值
     *
     * @return -1 or grate 0
     */
    public int getPageStart() {
        String startV = node.getElementsByTag(TmpConstant.PAGE).first().attr(TmpConstant.START);
        if (StringUtil.isNumeric(startV)) {
            return Integer.valueOf(startV);
        } else {
            return -1;
        }
    }

    public int getTypInt() {
        return TypeEnum.getBusinessByType(getType()).getType();
    }

}
