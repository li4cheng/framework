package com.my.framework.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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

    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            // 使用newInstance来创建对象
            obj = clazz.newInstance();
            // 获取类中的所有字段
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                // 判断是拥有某个修饰符
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                // 当字段使用private修饰时，需要加上
                field.setAccessible(true);
                // 获取参数类型名字
                String filedTypeName = field.getType().getName();
                // 判断是否为时间类型，使用equalsIgnoreCase比较字符串，不区分大小写
                // 给obj的属性赋值
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = (String) map.get(field.getName());
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, sdf.parse(datetimestamp));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

