package com.jxau.li.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeSimple {

    //返回当前时间
    public static String getTime() {
        Date date = new Date();

        // 创建 SimpleDateFormat 对象，指定目标格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化 Date 对象
        return formatter.format(date);
    }

    //返回当时时间加上n个月的时间

    public static String getTime(int monthsToAdd) {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();

        // 添加 n 个月
        calendar.add(Calendar.MONTH, monthsToAdd);

        // 创建 SimpleDateFormat 对象，指定目标格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化 Date 对象
        return formatter.format(calendar.getTime());
    }
}
