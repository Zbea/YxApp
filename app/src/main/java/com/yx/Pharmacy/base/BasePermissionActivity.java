package com.yx.Pharmacy.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yx.Pharmacy.activity.CheckPermissionsActivity;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/12/8.
 */
public abstract class BasePermissionActivity
        extends CheckPermissionsActivity
{
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        init();
    }

    @Override
    public Resources getResources() {
        Resources     res    = super.getResources();
        Configuration config =new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutId();


    protected abstract void init();


    /**
     * 显示短时间Toast
     * @param hint
     */
    public void getShortToastByString(String hint){
        if(TextUtils.isEmpty(hint)) return;
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), hint, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }


    public View getView(int viewId) {
        return LayoutInflater.from(this).inflate(viewId, new RelativeLayout(this));
    }

}
