package com.yx.Pharmacy.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/12/8.
 */
public abstract class BaseActivity
        extends AppCompatActivity
{
    private Toast toast;
    public Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext=this;
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


    public void contactService()
    {
        YSFUserInfo userInfo = new YSFUserInfo();
        String title = "";
        if (TextUtils.isEmpty(NetUtil.getToken())){
            title = "游客"+ DensityUtils.getRandomString(16);
            userInfo.userId = title;
        }else {
            if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE);
            }else {
                title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
            }
            userInfo.userId = NetUtil.getToken();
        }
        userInfo.data = "[{\"key\":\"real_name\", \"value\":"+title+"}]";
        Unicorn.setUserInfo(userInfo);
        ConsultSource source = new ConsultSource("我的门店", title, "custom information string");
        Unicorn.openServiceActivity(mContext, "点药呗", source);
    }

}
