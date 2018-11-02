package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.L;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends BaseActivity {
    @BindView(R.id.wv_view)
    WebView wvView;

    public static void startActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, WebviewActivity.class);
        intent.putExtra(Constants.H5_URL, url);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {

        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        String url = getIntent().getStringExtra(Constants.H5_URL);
//        url="file:///android_asset/b2b1030/html/coupon.html";
        wvView.getSettings().setJavaScriptEnabled(true);
//        mWebview.getSettings().setDomStorageEnabled(true);
        wvView.loadUrl(url + "?platform=android&token=" + NetUtil.isStringNull(NetUtil.getToken()) + "&storeId=" + NetUtil.isStringNull(NetUtil.getStoreid()) +
                "&itemId=" + NetUtil.isStringNull(NetUtil.getItemId()));
        L.i("onCreate: " + wvView.getUrl());
    }


}
