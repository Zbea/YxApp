package com.yx.Pharmacy.util;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final long SECOND = 1000;
    public static final long MINUTES = SECOND * 60;
    public static final long HOUR = 60 * MINUTES;
    public static final long DAY = 24 * HOUR;

    public static String getTimeByMills(long mills) {
        if (mills < MINUTES) {
            return mills / SECOND + "秒";
        }
        if (mills < HOUR) {
            return mills / MINUTES + "分钟";
        }
        if (mills < DAY) {
            return mills / DAY + "小时";
        }
        return mills / DAY + "天";
    }

    public static String getHMSByMills(long mills) {
        if (mills < MINUTES) {
            return mills / SECOND + "秒";
        }
        if (mills < HOUR) {
            return mills
                    / MINUTES
                    + "分"
                    + (mills % MINUTES / 1000 == 0 ? "" : mills % MINUTES
                    / 1000 + "秒");
        }
        return mills / HOUR + "小时";
    }

    /**
     * 获取指定时间，往前推，第一个时间的分数末尾是5的时间的long
     *
     * @param ctime
     * @return
     */
    public static long getPreLast5(long ctime, int time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        long ret = 0L;
        // 当前时间的分数
        int minite = new Integer(sdf.format(ctime));
        if (minite / time == 0) {
            System.out.println("0 : " + (time + minite % time));
            ret = ctime - (time + minite % time) * 60 * 1000L;
        } else if (minite / time % 2 == 0) {
            System.out.println("d : " + (minite - (minite / time - 1) * time));
            ret = ctime - (minite - (minite / time - 1) * time) * 60 * 1000L;
        } else {
            System.out.println("j : " + (minite - (minite / time) * time));
            ret = ctime - (minite - (minite / time) * time) * 60 * 1000L;
        }
        return ret;
    }

    /**
     * 取得日期的long时间
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static long getLong(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return (long) sdf.parse(date).getTime();
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 取得long时间表示的小时,最小精度小时
     *
     * @param mills
     * @return
     */
    public static String getMinute(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return sdf.format(new Date(mills));
    }

    /**
     * 获取指定日期当前最早时刻 00:00:00
     *
     * @return
     */
    public static long getDayBegin(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日志当天最晚时刻 23:59:59
     *
     * @return
     */
    public static long getDayEnd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis();
    }

    /**
     * 获取指定日期下一天 00:00:00
     *
     * @return
     */
    public static long getNextDay(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取时间自然周的周一00:00:00
     *
     * @param time
     * @return
     */
    public static long getWeekBegin(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        int dayOfWeekInUs = cal.get(Calendar.DAY_OF_WEEK);
        int beginDayInCh = dayOfWeekInUs == 1 ? -6 : 2 - dayOfWeekInUs;
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() + beginDayInCh * DAY;
    }

    /**
     * 获取时间自然周的周日23:59:59
     *
     * @param time
     * @return
     */
    public static long getWeekEnd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        int dayOfWeekInUs = cal.get(Calendar.DAY_OF_WEEK);
        int beginDayInCh = dayOfWeekInUs == 1 ? 0 : 8 - dayOfWeekInUs;
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis() + beginDayInCh * DAY;
    }

    /**
     * 获取时间自然月的1号00:00:00
     *
     * @param time
     * @return
     */
    public static long getMonthBegin(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取时间自然月的最后一日23:59:59
     *
     * @param time
     * @return
     */
    public static long getMonthEnd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis();
    }

    public static int birth2Age(long birth) {
        Calendar last = Calendar.getInstance();
        int age = 0;
        last.setTimeInMillis(birth);
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        if ((now.get(Calendar.MONTH) > last.get(Calendar.MONTH))
                || (now.get(Calendar.MONTH) == last.get(Calendar.MONTH) && (now
                .get(Calendar.DATE) >= last.get(Calendar.DATE)))) {
            age = (byte) (now.get(Calendar.YEAR) - last.get(Calendar.YEAR));
        } else {
            age = (byte) (now.get(Calendar.YEAR) - last.get(Calendar.YEAR) - 1);
        }
        return age > 0 ? age : 0;
    }

    public static long age2Birth(byte age) {
        if (age < 1) {
            return 0L;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - age - 1);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 提示区间
     */
    private static enum timeZone {

        second(60 * 1000), minute(second.value * 60), hour(minute.value * 24), day(
                hour.value * 31), month(day.value * 3);

        private final long value;

        private timeZone(long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }
    }

    /**
     * 提示信息
     */
    private static enum hintDate {

        second("秒前"), minute("分钟前"), hour("小时前"), day("天前"), month("个月前"), maxMonth(
                "3个月前");

        private final String value;

        private hintDate(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    ;

    /**
     * 获取date至今的时间间隔 时间规则为：0-59秒 1-59分 1-23小时 1-30天 1-2月 3个月以前
     *
     * @param date 之前的时间
     * @return 错误的输入返回"error"
     */
    public static String getTimeIntervalSince(Date date) {
        if (null == date) {
            return "error";
        }
        long since = System.currentTimeMillis() - date.getTime();
        if (since < 500) {// 小于500毫秒设为500
            since = 500;
        }
        /**
         * 区间判断,结果取整
         */
        if (since < timeZone.second.getValue()) {
            return (since / 1000L) + hintDate.second.getValue();
        }
        if (since < timeZone.minute.getValue()) {
            return (since / timeZone.second.getValue())
                    + hintDate.minute.getValue();
        }
        if (since < timeZone.hour.getValue()) {
            return (since / timeZone.minute.getValue())
                    + hintDate.hour.getValue();
        }
        if (since < timeZone.day.getValue()) {
            return (since / timeZone.hour.getValue()) + hintDate.day.getValue();
        }
        if (since < timeZone.month.getValue()) {
            return (since / timeZone.day.getValue())
                    + hintDate.month.getValue();
        }
        return hintDate.maxMonth.getValue();
    }

    /**
     * 获取date至今的时间间隔 时间规则为：0-59秒 1-59分 1-23小时 1-30天 1-2月 3个月以前
     *
     * @return 错误的输入返回"error"
     */
    public static String getTimeIntervalSince(long dateTime) {
        if (dateTime < 1) {
            return "error";
        }
        return getTimeIntervalSince(new Date(dateTime));
    }

    public static String getyyyyMMdd(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(mills));
    }

    public static final String[] constellationArr = {"水瓶座", "双鱼座", "白羊座",
            "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    public static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22,
            23, 23, 23, 23, 22, 22};

    public static String getStarByBirth(long birth) {
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(birth);
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }

    static SimpleDateFormat sdf = null;

    /**
     * 年月日
     *
     * @param time
     * @return
     */
    public static Date getDate(String time) {
        Date date = new Date(System.currentTimeMillis());
        sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            date = sdf.parse(time);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 年月日时分
     *
     * @param time
     * @return
     */
    public static String formatyyyyMMddHHmmss(long time) {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
    public static String formatlogtimestamp(long time) {
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(time);
    }
    /**
     * 年月日时分
     *
     * @param time
     * @return
     */
    public static String formatyyyyMMdd(long time) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    /**
     * 月日
     *
     * @param time
     * @return
     */
    public static String formatMMdd(long time) {
        sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(time);
    }
    /**
     * 年月
     *
     * @param time
     * @return
     */
    public static String formatyyyymm(long time) {
        sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(time);
    }

    /**
     * 月日时分
     *
     * @param time
     * @return
     */
    public static String formatMMddHHmm(long time) {
        sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(time);
    }

    /**
     * 星期几 小时:分钟
     *
     * @param time
     * @return
     */
    public static String formatEHHmm(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        sdf = new SimpleDateFormat("HH:mm");
        String day_of_weekString = "";
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                day_of_weekString = "日";
                break;
            case 2:
                day_of_weekString = "一";
                break;
            case 3:
                day_of_weekString = "二";
                break;
            case 4:
                day_of_weekString = "三";
                break;
            case 5:
                day_of_weekString = "四";
                break;
            case 6:
                day_of_weekString = "五";
                break;
            case 7:
                day_of_weekString = "六";
                break;
        }
        return "星期" + day_of_weekString + " " + sdf.format(time);
    }

    /**
     * 时分
     *
     * @param time
     * @return
     */
    public static String formatHHmm(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(time);
    }

    /**
     * 时分
     *
     * @param time
     * @return
     */
    public static String formatHHmmss(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * 分秒
     *
     * @param time
     * @return
     */
    public static String formatmmss(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(time);
    }

    public static String formatDate(Context context, long date) {
        @SuppressWarnings("deprecation")
        int format_flags = android.text.format.DateUtils.FORMAT_NO_NOON_MIDNIGHT
                | android.text.format.DateUtils.FORMAT_ABBREV_ALL
                | android.text.format.DateUtils.FORMAT_CAP_AMPM
                | android.text.format.DateUtils.FORMAT_SHOW_DATE
                | android.text.format.DateUtils.FORMAT_SHOW_DATE
                | android.text.format.DateUtils.FORMAT_SHOW_TIME;
        return android.text.format.DateUtils.formatDateTime(context, date,
                format_flags);
    }

    public static String getDuration(long duration) {
        long tempTime = duration / 1000;
        if (0 < tempTime && tempTime < 60) {// 秒
            return tempTime + "秒";
        }
        tempTime = tempTime / 60;
        if (0 < tempTime && tempTime < 60) {// 分钟
            return tempTime + "分钟";
        }
        tempTime = tempTime / 60;
        if (0 < tempTime && tempTime < 24) {// 小时
            return tempTime + "小时";
        }
        tempTime = tempTime / 24;
        return tempTime + "天";
    }

    /**
     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String longToStrng(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        // System.out.println("TIME:::"+dateString);
        return dateString;
    }

    /**
     * //-------------------------显示时间格式,通常用在显示 消息 的时间------------------------//
     *
     * @param ctime
     * @return
     */
    public static String getFormatTime(long ctime) {
        if (ctime > getDayBegin(System.currentTimeMillis())) {
            // 当天时间
            return formatHHmm(ctime);
        } else if (ctime > getDayBegin(getDayBegin(System
                .currentTimeMillis()) - 3000 * 1000)) {
            // 昨天时间
            return ("昨天 " + formatHHmm(ctime));
        } else if (ctime > getDayBegin(getDayBegin(System
                .currentTimeMillis()) - 7 * 24 * 60 * 60 * 1000)) {
            // 一周内时间
            return formatEHHmm(ctime);
        } else {
            return formatMMddHHmm(ctime);
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author zfz
     */
    public static long getDaySub(long beginDateStr, long endDateStr) {
        long day = 0;
        if (endDateStr == 0){
            return day;
        }
        day = (endDateStr - beginDateStr) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * <li>功能描述：时间相减得到剩余分钟数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author zfz
     */
    public static long getMinutesSub(long beginDateStr, long endDateStr) {
        long day = 0;
        if (endDateStr == 0){
            return day;
        }
        day = (endDateStr - beginDateStr) / (60 * 1000);
        return day;
    }
}
