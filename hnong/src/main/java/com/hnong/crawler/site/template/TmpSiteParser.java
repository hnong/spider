package com.hnong.crawler.site.template;

import com.hnong.crawler.BaseModel;
import com.hnong.crawler.exception.TmpException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;

/**
 * User: chris
 * Date: 13-7-14
 */
public abstract class TmpSiteParser {
    private Element node;

    public BaseModel parser(Document doc) throws TmpException {
        node = init(doc);
        if (node == null) {
            throw new TmpException("TmpSiteParser init node exception.");
        }

        BaseModel model = new BaseModel();
        model.setAuthor(parserAuthor());
        model.setContent(parserContent());
        model.setPublishDate(parserPublishDate());
        model.setSource(parserSource());
        model.setTags(parserTags());
        model.setTitle(parserTitle());
        return model;
    }

    abstract public Element init(Document doc);

    abstract public String parserTitle();

    abstract public String parserContent();

    abstract public String parserSource();

    /*
     * 不是必须的数据
     */
    abstract public String parserAuthor();

    abstract public String parserTags();

    abstract public Date parserPublishDate();

    public Element getNode() {
        return node;
    }

    public void setNode(Element node) {
        this.node = node;
    }
}
