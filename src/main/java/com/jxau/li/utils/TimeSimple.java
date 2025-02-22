package com.jxau.li.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSimple {

    public static String getTime() {
        Date date = new Date();

        // 创建 SimpleDateFormat 对象，指定目标格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化 Date 对象
        return formatter.format(date);
    }
}
