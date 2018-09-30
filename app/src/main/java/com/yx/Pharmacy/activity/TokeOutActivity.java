package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.base.BaseActivity;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   TokeOutActivity
 *  @创建者:   CC
 *  @创建时间:  2018/8/13 22:35
 *  @描述：    TODO
 */

public class TokeOutActivity
        extends BaseActivity
{

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, TokeOutActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_takeout;
    }

    @Override
    protected void init() {

    }
}
