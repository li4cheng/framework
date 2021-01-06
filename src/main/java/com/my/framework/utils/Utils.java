package com.my.framework.utils;

import java.util.UUID;

public class Utils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 首字母大写
     * */
    public static String firstUpperCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 切换为驼峰命名
     * - / .后字母换为大写
     * */
    public static String camelCase(String s) {
        String[] strings = s.replaceAll("-", "/").replaceAll("\\.", "/").split("/");
        String string = strings[0];
        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i++) {
                string += Utils.firstUpperCase(strings[i]);
            }
        }
        return string;
    }
}

