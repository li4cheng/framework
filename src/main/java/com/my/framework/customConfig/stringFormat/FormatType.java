package com.my.framework.customConfig.stringFormat;

import com.my.framework.customConfig.baseEnum.BaseEnum;

public enum FormatType implements BaseEnum {

    NULL("无"),
    CHINESE_NAME("中文名"),
    ID_CARD("身份证号"),
    FIXED_PHONE("座机号"),
    MOBILE_PHONE("手机号"),
    ADDRESS("地址"),
    EMAIL("电子邮件"),
    BANK_CARD("银行卡"),
    PASSWORD("密码"),
    CAR_NUMBER("车牌号");

    private final String message;

    FormatType(String message) {
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
