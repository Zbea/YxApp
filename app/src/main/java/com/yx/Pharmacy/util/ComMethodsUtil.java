package com.yx.Pharmacy.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

/**
 * Created by KID on 2018/3/26.
 */

public class ComMethodsUtil {

    //隐藏软键盘
    public static void hideSoftKeyBoard(Activity activity){
        try {
            ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
        }
    }

    public static void showSoftKetBoard(Activity activity){
        try {
            ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                    .toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
        }
    }
    public static void installApk(Context context,File file){
        Intent intent = new Intent();
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0需要用到flieProvider,并且需要权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |Intent.FLAG_ACTIVITY_NEW_TASK);
            fileUri= FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        }else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            fileUri=Uri.fromFile(file);
        }
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
    public static String phoneFormat(String phone){
        if(phone.length()==11){
            String phoneMa=phone.substring(0,3)+"****"+phone.substring(7,11);
            return phoneMa;
        }else {
            return TextUtils.isEmpty(phone)?"":phone;
        }
    }
    //判断两个时间是否同一天
    public static boolean isSameDay(long time1,long time2){
        if(time1==0||time2==0){
            return false;
        }
        String date1=DateUtil.formatyyyyMMdd(time1);
        String date2=DateUtil.formatyyyyMMdd(time2);
        if(date1.equals(date2)){
            return true;
        }
        return false;
    }

}
