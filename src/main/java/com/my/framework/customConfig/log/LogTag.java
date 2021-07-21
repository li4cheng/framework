package com.my.framework.customConfig.log;

import com.my.framework.customConfig.baseEnum.BaseEnum;

public enum LogTag implements BaseEnum {
    NULL("无"),
    SYS("系统"),
    LOGIN("登录"),
    TEST("测试");

    private final String message;

    LogTag(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
