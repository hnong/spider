package com.hnong.crawler.core.util;

/**
 * User: chris
 * Date: 13-7-13
 */

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * User: huangzhong
 * Date: 2011-11-30
 * Time: 22:17:20
 */
public class AppHolder
{
    private static Injector injector = Guice.createInjector();
    private static final Object lock = new Object();
    private static AppHolder instance;

    private AppHolder(){}

    public Injector getInjector()
    {
        return injector;
    }

    public static AppHolder getInstance()
    {
        if (instance == null) {
            synchronized(lock) {
                if(instance == null){
                    instance = new AppHolder();
                }
            }
        }
        return instance;
    }
}

