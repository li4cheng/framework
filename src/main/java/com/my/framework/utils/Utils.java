package com.my.framework.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Utils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 字符串首字母大写
     */
    public static String firstUpperCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 字符串切换为驼峰命名
     * - .后字母换为大写
     */
    public static String camelCase(String s) {
        String[] strings = s.replaceAll("-", "/").replaceAll("\\.", "/").split("/");
        StringBuilder string = new StringBuilder(strings[0]);
        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i++) {
                string.append(Utils.firstUpperCase(strings[i]));
            }
        }
        return string.toString();
    }

    /**
     * json string to map
     */
    public static HashMap jsonToMap(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return JSONObject.parseObject(String.valueOf(JSON.parse(JSONObject.toJSONString(jsonObject))), HashMap.class);
    }

    /**
     * json object to map
     */
    public static HashMap jsonToMap(Object jsonObject) {
        return jsonToMap(String.valueOf(jsonObject));
    }

    /**
     * .json file to map
     */
    public static HashMap jsonToMap(MultipartFile file) throws IOException {
        String s = new String(file.getBytes());
        return jsonToMap(s);
    }
}

