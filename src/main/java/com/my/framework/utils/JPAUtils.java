package com.my.framework.utils;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;

import java.util.Collections;

public class JPAUtils {

    public static StringTemplate groupConcat(Object args) {
        //"CONCAT(GROUP_CONCAT({0}) SEPARATOR ',')"    自定义分隔符报空指针？？？？？？
        return Expressions.stringTemplate("CONCAT(GROUP_CONCAT({0}))", Collections.singletonList(args));
    }
}
