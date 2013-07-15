package com.hnong.crawler.core.example;

import com.hnong.crawler.core.exception.ParserException;
import com.hnong.crawler.core.exception.SpiderException;
import com.hnong.crawler.core.parser.Parser;
import com.hnong.crawler.core.spider.CommonSpider;
import com.hnong.crawler.core.spider.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * User: zhong.huang
 * Date: 13-5-29
 */
public class BaseTest {
    static Spider spider = new CommonSpider().useProxy(true);
    static Parser parser = new BaseModelParser();

    public static void main(String[] args) {
        try {
            String v = spider.download("http://www.zhihu.com/question/19937781", "utf-8");
            Document doc = Jsoup.parse(v);
            BaseModel model = (BaseModel) parser.parser(doc, BaseModel.class);
            System.out.println(model.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpiderException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
}
