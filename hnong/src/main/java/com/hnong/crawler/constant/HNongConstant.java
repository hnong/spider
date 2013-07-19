package com.hnong.crawler.constant;

/**
 * User: zhong.huang
 * Date: 13-5-21
 */
public class HNongConstant {

    public static String CHARSET_UTF8 = "utf-8";
    public static String CONFIG_KEY_COOKIE = "cookie";
    public static final String CONFIG_KEY_PROXY_IP = "proxy_ip";
    public static final String CONFIG_KEY_SITE_TMP_PATH = "site_tmp_path";


    public static String getCookie() {
        return "";//todo AppConfigs.getInstance().get(CONFIG_KEY_COOKIE);
    }

    public static String getProxy() {
        return "";//todo AppConfigs.getInstance().get(CONFIG_KEY_PROXY_IP);
    }


}
