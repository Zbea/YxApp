package com.yx.Pharmacy.util;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.util
 *  @文件名:   ViewUtil
 *  @创建者:   CC
 *  @创建时间:  2018/7/22 16:41
 *  @描述：    TODO
 */

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class ViewUtil {

    public static int getColor(Context conetxt, @ColorRes int id){
        return  ContextCompat.getColor(conetxt, id);
    }

    public static int getColor(@ColorRes int id){
        return  ContextCompat.getColor(UiUtil.getContext(), id);
    }

    public static <T extends View> T findViewById(View v, int id) {
        return (T) v.findViewById(id);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */

    public static int dp2px(Context context,float dip) {
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
}
