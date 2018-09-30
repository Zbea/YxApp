package com.yx.Pharmacy.util;

import android.content.Context;
import android.os.Handler;

import com.yx.Pharmacy.base.YxApp;


/**
 * 获取context,提交任务，读取string。。。。
 */
public class UiUtil {

    private static Context context;
    private static Handler mHandler;

    public static Context getContext() {
        return context;
    }

    public static void init(YxApp gpApplication) {
        context = gpApplication;
        mHandler = new Handler();
    }

    /**
     * 提交一个任务
     */
    public static void post(Runnable task){
        mHandler.post(task);
    }

    /**
     * 延时提交一个任务
     */
    public static void postDelay(Runnable task, long delay){
        mHandler.postDelayed(task, delay);
    }

    /**
     * 取消一个任务
     */
    public static void cacel(Runnable task) {
        mHandler.removeCallbacks(task);
    }

    public static void removerBack() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public static String[] getStringArray(int resId){
        return context.getResources().getStringArray(resId);
    }

    /**
     * 获取带占位符的string,
     * @param resId
     * @return
     */
    public static String getString(int resId, Object... formatArgs){
        return context.getResources().getString(resId, formatArgs);
    }

    //半角转全角
    public static String toDBC(String input){
        char c[] = input.toCharArray();
        for ( int i=0; i<c.length;i++ ) {
            if (c[i] ==' ') {
                c[i] = '\u3000';
            }
            else if (c[i]<'\177') {
                c[i]= (char) (c[i]+65248);
            }
        }
        return new String(c);
    }
}
