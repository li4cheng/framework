package com.my.framework.customConfig.stringFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class StringFormatter implements Formatter<String> {

    private FormatType formatType;

    public FormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(FormatType formatType) {
        this.formatType = formatType;
    }

    @Override
    public String parse(String text, Locale locale) throws ParseException {
        // to backend
        if (StringUtils.isNotBlank(text)) {
            switch (formatType) {
                case NULL:
                    break;
                case CHINESE_NAME:
                    text = StringFormatterUtils.chineseName(text);
                    break;
                case ID_CARD:
                    text = StringFormatterUtils.idCardNum(text);
                    break;
                case FIXED_PHONE:
                    text = StringFormatterUtils.fixedPhone(text);
                    break;
                case MOBILE_PHONE:
                    text = StringFormatterUtils.mobilePhone(text);
                    break;
                case ADDRESS:
                    text = StringFormatterUtils.address(text, 8);
                    break;
                case EMAIL:
                    text = StringFormatterUtils.email(text);
                    break;
                case BANK_CARD:
                    text = StringFormatterUtils.bankCard(text);
                    break;
                case PASSWORD:
                    text = StringFormatterUtils.password(text);
                    break;
                case CAR_NUMBER:
                    text = StringFormatterUtils.carNumber(text);
                    break;
                default:
            }
        }
        return text;
    }

    @Override
    public String print(String object, Locale locale) {
        // to front

        return object;
    }
}
