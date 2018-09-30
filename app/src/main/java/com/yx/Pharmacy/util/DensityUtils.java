package com.yx.Pharmacy.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class DensityUtils {

     private DensityUtils(){
          /* cannot be instantiated */
         throw new UnsupportedOperationException("cannot be instantiated");
     }
     /**
       * dp 转 px
       *
       * @param context
       * @param
       * @return
       */
     public static int dp2px(Context context, float dpVal) {
         return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dpVal, context.getResources().getDisplayMetrics());
     }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
         public static int sp2px(Context context, float spVal) {
             return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                  spVal, context.getResources().getDisplayMetrics());
         }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
         final float scale = context.getResources().getDisplayMetrics().density;
         return (pxVal / scale);
    }

    /**
     * px转sp
     * @param context
     * @param pxVal
     * @return
     */
     public static float px2sp(Context context, float pxVal) {
         return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
     }

    /**
     * String 转 int
     */
    public static int parseInt(String birthYear) {
        try {
            if (TextUtils.isEmpty(birthYear)) {
                return 0;
            }
            return Integer.parseInt(birthYear);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * String 转 double
     */
    public static double parseDouble(String birthYear) {
        try {
            return Double.parseDouble(birthYear);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * String 转 long
     */
    public static long parseLong(String birthYear) {
        try {
            return Long.parseLong(birthYear);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * double类型如果小数点后为零显示整数否则保留
     * @param num
     * @return
     */
    public static String doubleTrans2(double num){
        if(Math.round(num)-num==0){
            return String.valueOf((long)num);
        }
        return String.valueOf(num);
    }

    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    /**
     * 保留到小数点两位，四舍五入
     * @param scale 传入保存小数点后几位的值。
     * @return
     */
    public static double round(Double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = null == v
                       ? new BigDecimal("0.0")
                       : new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    // length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String       str    ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random       random =new Random();
        StringBuffer sb     =new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 转化时间 格式化为"yyyy-MM-dd"
     * @return
     * @param time
     */
    public static String getDayMothDate(Long time){
        SimpleDateFormat formatter =   new SimpleDateFormat("yyyy-MM-dd");
        Date             curDate   = new Date(time);//获取当前时间
        String           dateStr   =formatter.format(curDate);
        return dateStr;
    }

    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return 屏幕宽px
     */
    public static int getScreenWidth() {
        WindowManager  windowManager = (WindowManager) UiUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm            = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) UiUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }


    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
