package com.yx.Pharmacy.base;

import android.support.multidex.MultiDexApplication;

import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.loader.QiyuImageLoader;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.AppStatusTracker;
import com.yx.Pharmacy.util.UiUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/4/28.
 */
public class YxApp
        extends MultiDexApplication
{

    public static IWXAPI mWxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        UiUtil.init(this);
        AppStatusTracker.init(this);
        Unicorn.init(this, Constants.QIYU_ID, options(), new QiyuImageLoader(this));
        initBugly();
        initJPush();// 推送
        registToWX();// 注册微信
    }

    private void initBugly() {
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Beta.upgradeDialogLayoutId = R.layout.dialog_upgrade;
/*
        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:

                // 通过id方式获取控件，并更改imageview图片
                TextView tv_upgrade_cancel = (TextView) view.findViewById(R.id.tv_upgrade_cancel);
                TextView tv_upgrade_info = (TextView) view.findViewById(R.id.tv_upgrade_info);
                tv_upgrade_cancel.setVisibility(View.GONE);
                tv_upgrade_info.setVisibility(View.GONE);
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
            }

        };*/
        Bugly.init(getApplicationContext(), "b25dc92ff5", false);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.setAlias(this, 0, NetUtil.getToken());
    }


    private void registToWX() {
        mWxApi = WXAPIFactory.createWXAPI(this, Constants.WX_ID, true);
        // 将该app注册到微信
        mWxApi.registerApp(Constants.WX_ID);
    }

    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        return options;
    }
}
