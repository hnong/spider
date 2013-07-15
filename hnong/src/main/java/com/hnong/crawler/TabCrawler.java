package com.hnong.crawler;

import com.hnong.common.util.StringUtil;

import java.util.Date;
import java.util.Set;

/**
 * User: chris
 * Date: 13-7-14
 */
public class TabCrawler implements Runnable {

    private HomeSite homesite;
    private BaseParser parser;

    public TabCrawler(HomeSite site, BaseParser parser) {
        this.homesite = site;
        this.parser = parser;
    }

    @Override
    public void run() {
        ///获取数据页面url
        Set<String> ret = homesite.getTargetUrls();
        if (ret == null) {
            return;
        }

        String html = "";
        BaseModel model = null;
        for (String target : ret) {
            html = homesite.download(target);
            model = parser.parser(html);
            model.setUrl(target);
            model.setMd5Url(StringUtil.md5(target));
            model.setSiteName(homesite.getName());
            model.setType(homesite.getTypeEnum().getType());
            model.setCreateDate(new Date());
        }
    }
}
