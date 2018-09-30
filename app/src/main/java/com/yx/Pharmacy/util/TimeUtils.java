package com.yx.Pharmacy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created time  2018/8/21 0021
 * @author : mcx
 * 类描述 : 
 */

public class TimeUtils {


    /**
     * 获取年份 格式化为"yyyy"
     * @return
     */
    public static String getDateYear(long l){
        SimpleDateFormat formatter =   new SimpleDateFormat("yyyy");
        Date             curDate   = new Date(l);//获取当前时间
        String           dateStr   =formatter.format(curDate);
        return dateStr;
    }

    /**
     * 获取月份 格式化为"MM"
     * @return
     */
    public static String getDateMonth(long l){
        SimpleDateFormat formatter =   new SimpleDateFormat("MM");
        Date             curDate   = new Date(l);//获取当前时间
        String           dateStr   =formatter.format(curDate);
        return dateStr;
    }

    /**
     * 获取日子 格式化为"dd"
     * @return
     */
    public static String getDateDay(long l){
        SimpleDateFormat formatter =   new SimpleDateFormat("dd");
        Date             curDate   = new Date(l);//获取当前时间
        String           dateStr   =formatter.format(curDate);
        return dateStr;
    }
}
