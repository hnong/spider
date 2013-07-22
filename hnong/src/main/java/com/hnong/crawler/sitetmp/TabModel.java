package com.hnong.crawler.sitetmp;

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
}
