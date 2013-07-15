package com.hnong.crawler;

import java.util.Date;

/**
 * User: chris
 * Date: 13-7-14
 */
public abstract class BaseParser {

    public BaseModel parser(String html) {
        init(html);

        BaseModel model = new BaseModel();
        model.setAuthor(parserAuthor());
        model.setContent(parserContent());
        model.setPublishDate(parserPublishDate());
        model.setSource(parserSource());
        model.setTags(parserTags());
        model.setTitle(parserTitle());


        return model;
    }

    abstract public void init(String html);
    abstract public String parserTitle();
    abstract public String parserContent();
    abstract public String parserSource();
    abstract public String parserAuthor();
    abstract public String parserTags();
    abstract public Date parserPublishDate();

}
