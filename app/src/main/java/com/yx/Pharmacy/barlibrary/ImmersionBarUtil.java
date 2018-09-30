package com.yx.Pharmacy.barlibrary;

import android.app.Activity;

/**
 * Created by KID on 2018/7/19.
 */

public class ImmersionBarUtil {
    /**
     * 设置状态栏颜色
     * @param color
     * @param activity
     */
    public static void setBarColor(int color,Activity activity,boolean needChangeIconColor){
        ImmersionBar.with(activity).statusBarColor(color).init();
        if(needChangeIconColor){
            ImmersionBar.with(activity).fitsSystemWindows(true).statusBarDarkFont(true, 0.2f).init();
        }
    }
}
