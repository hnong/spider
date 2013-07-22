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
    private Element value;

    private Element title;
    private Element content;
    private Element source;
    private Element author;
    private Element tags;
    private Element publishDate;

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

    public Element getValue() {
        return value;
    }

    public void setValue(Element value) {
        this.value = value;
    }

    public Element getTitle() {
        return title;
    }

    public void setTitle(Element title) {
        this.title = title;
    }

    public Element getContent() {
        return content;
    }

    public void setContent(Element content) {
        this.content = content;
    }

    public Element getSource() {
        return source;
    }

    public void setSource(Element source) {
        this.source = source;
    }

    public Element getAuthor() {
        return author;
    }

    public void setAuthor(Element author) {
        this.author = author;
    }

    public Element getTags() {
        return tags;
    }

    public void setTags(Element tags) {
        this.tags = tags;
    }

    public Element getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Element publishDate) {
        this.publishDate = publishDate;
    }
}
