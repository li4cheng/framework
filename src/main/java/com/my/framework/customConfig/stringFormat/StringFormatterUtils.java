package com.my.framework.customConfig.stringFormat;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringFormatterUtils {

    /**
     * 【中文姓名】只显示第一个汉字，其他隐藏为2个星号，比如：李**
     */
    public static String chineseName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        // 获取姓氏
        String firstName = nameSplit(fullName).get(0);
        return StringUtils
            .rightPad(
                firstName,
                StringUtils.length(fullName),
                "*"
            );
    }

    /**
     * 【身份证号】显示最后四位，其他隐藏。共计18位或者15位，比如：*************1234
     */
    public static String idCardNum(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return "";
        }
        String num = StringUtils.right(idCard, 4);
        return StringUtils
            .leftPad(
                num,
                StringUtils.length(idCard),
                "*"
            );
    }

    /**
     * 【固定电话 后四位，其他隐藏，比如*****1234
     */
    public static String fixedPhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils
            .leftPad(
                StringUtils.right(num, 4),
                StringUtils.length(num),
                "*"
            );
    }

    /**
     * 【手机号码】前三位，后四位，其他隐藏，比如135****6610
     */
    public static String mobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils
            .left(num, 3)
            .concat(
                StringUtils.removeStart(
                    StringUtils.leftPad(
                        StringUtils.right(num, 4),
                        StringUtils.length(num),
                        "*"
                    ),
                    "***"
                )
            );
    }

    /**
     * 【地址】只显示到地区，不显示详细地址，比如：北京市海淀区****
     */
    public static String address(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils
            .rightPad(
                StringUtils.left(
                    address,
                    length - sensitiveSize
                ),
                length,
                "*"
            );
    }

    /**
     * 【电子邮箱 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com>
     */
    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils
                .rightPad(
                    StringUtils.left(email, 1),
                    index,
                    "*"
                )
                .concat(
                    StringUtils.mid(
                        email,
                        index,
                        StringUtils.length(email)
                    )
                );
        }
    }

    /**
     * 【银行卡号】前六位，后四位，其他用星号隐藏每位1个星号，比如：6222600**********1234>
     */
    public static String bankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils
            .left(cardNum, 6)
            .concat(
                StringUtils.removeStart(
                    StringUtils.leftPad(
                        StringUtils.right(cardNum, 4),
                        StringUtils.length(cardNum),
                        "*"
                    ),
                    "******"
                )
            );
    }

    /**
     * 【密码】密码的全部字符都用*代替，比如：******
     */
    public static String password(String password) {
        if (StringUtils.isBlank(password)) {
            return "";
        }
        return StringUtils
            .repeat(
                "*",
                StringUtils.length(password)
            );
    }

    /**
     * 【车牌号】前两位后一位，比如：苏M****5
     */
    public static String carNumber(String carNumber) {
        if (StringUtils.isBlank(carNumber)) {
            return "";
        }
        return StringUtils
            .left(carNumber, 2)
            .concat(
                StringUtils.removeStart(
                    StringUtils.leftPad(
                        StringUtils.right(carNumber, 1),
                        StringUtils.length(carNumber),
                        "*"
                    ),
                    "**"
                )
            );
    }

    private static List<String> nameSplit(String name) {
        String[] bai = {"欧阳", "太史", "上官", "端木", "司马", "东方", "独孤", "南宫", "万俟", "闻人", "夏侯", "诸葛", "尉迟", "公羊", "赫连", "澹台",
            "皇甫", "宗政", "濮阳", "公冶", "太叔", "申屠", "公孙", "慕容", "仲孙", "钟离", "长孙", "宇文", "司徒", "鲜于", "司空", "闾丘", "子车",
            "亓官", "司寇", "巫马", "公西", "颛孙", "壤驷", "公良", "漆雕", "乐正", "宰父", "谷梁", "拓跋", "夹谷", "轩辕", "令狐", "段干", "百里",
            "呼延", "东郭", "南门", "羊舌", "微生", "公户", "公玉", "公仪", "梁丘", "公仲", "公上", "公门", "公山", "公坚", "左丘", "公伯", "西门",
            "公祖", "第五", "公乘", "贯丘", "公皙", "南荣", "东里", "东宫", "仲长", "子书", "子桑", "即墨", "达奚", "褚师", "吴铭"};
        String newName = name.substring(0, 2);
        List<String> names = new ArrayList<String>();
        boolean flag = false;
        for (String s : bai) {
            if (newName.equals(s)) {
                names.add(newName);
                names.add(name.substring(2));
                flag = true;
                break;
            }
        }
        if (!flag) {
            names.add(name.charAt(0) + "");
            names.add(name.substring(1));
            return names;
        }
        return names;
    }
}
