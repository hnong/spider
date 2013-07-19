package com.hnong.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * User: zhong.huang
 * Date: 13-7-19
 */
public class TmpConfig {
     private static final Logger LOGGER = LoggerFactory.getLogger(TmpConfig.class);

    static {
        try {
            ClassLoader classLoader = (new TmpConfig()).getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("SCHOOL_DICT.txt");
        } catch (Exception e) {
            LOGGER.error("init ConfigLoader error:" + e.getMessage());
            throw new ExceptionInInitializerError("Failed to load config ");
        }
    }
}
