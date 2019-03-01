package com.yx.Pharmacy.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.MyIntegralActivity;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ChooseStoreDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.manage.StoreManage;
import com.yx.Pharmacy.model.BackHhBean;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.SelectStoreHhBean;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.LoadingLayout;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.yx.Pharmacy.activity.LoginActivity.START_LOGIN_RESULT;

/**
 * Created by Administrator on 2017/5/17.
 */
public class HHActivity
        extends CordovaActivity {
    @BindView(R.id.webview)
    SystemWebView mWebview;
    @BindView(R.id.loadlayout)
    LoadingLayout loadlayout;
    @BindView(R.id.include_title)
    RelativeLayout include_title;
    @BindView(R.id.rl_h5_back)
    RelativeLayout rl_h5_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_h5_title)
    TextView tv_h5_title;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.rl_more)
    RelativeLayout mRlMore;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    private ChooseStoreDialog mChooseStoreDialog;

    public static void startActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, HHActivity.class);
        intent.putExtra(Constants.H5_URL, url);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, String url, int type) {
        Intent intent = new Intent(activity, HHActivity.class);
        intent.putExtra(Constants.H5_URL, url);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    @SuppressLint({"JavascriptInterface",
            "SetJavaScriptEnabled",
            "AddJavascriptInterface"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        super.init();


        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        String url = getIntent().getStringExtra(Constants.H5_URL);
        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            include_title.setVisibility(View.GONE);
            rl_title.setVisibility(View.VISIBLE);
        } else {
            include_title.setVisibility(View.VISIBLE);
            rl_title.setVisibility(View.GONE);
            if (type == 1) {
                tv_title.setText("退换政策");
            }
        }
        loadlayout.setStatus(LoadingLayout.Success);
        tv_h5_title.setVisibility(View.GONE);
        L.i("onCreate: " + url);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        url=url.replace("#","");
        url=url.replace("/?","?");
        if (!url.contains("token")) {
            if (url.contains("?")) {
                mWebview.loadUrl(url + "&platform=android&token=" + NetUtil.isStringNull(NetUtil.getToken()) + "&storeId=" + NetUtil.isStringNull(NetUtil.getStoreid()) +
                        "&itemId=" + NetUtil.isStringNull(NetUtil.getItemId()));

            } else {
                mWebview.loadUrl(url + "?platform=android&token=" + NetUtil.isStringNull(NetUtil.getToken()) + "&storeId=" + NetUtil.isStringNull(NetUtil.getStoreid()) +
                        "&itemId=" + NetUtil.isStringNull(NetUtil.getItemId()));
            }
        } else {
            mWebview.loadUrl(url);
        }
        L.i("onCreate: getUrl" + mWebview.getUrl());

//        mWebview.setWebViewClient(new SystemWebViewClient(mWebview.getParentEngine()) {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                loadlayout.setStatus(LoadingLayout.Success);
//                String title = view.getTitle();
//                if(!TextUtils.isEmpty(title)&&!title.contains("http")){
//                    tv_h5_title.setText(title);
//                    tv_h5_title.setVisibility(View.VISIBLE);
//                }else {
//                    tv_h5_title.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                loadlayout.setStatus(LoadingLayout.Loading);
//            }
//
//        });

//        mWebview.setWebViewClient(new SystemWebViewClient(mWebview.getParentEngine()) {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (!url.contains("token"))
//                {
//                    mWebview.loadUrl(url + "?platform=android&token=" + NetUtil.isStringNull(NetUtil.getToken()) + "&storeId=" + NetUtil.isStringNull(NetUtil.getStoreid()) +
//                            "&itemId=" + NetUtil.isStringNull(NetUtil.getItemId()));
//                }
//                else
//                {
//                    mWebview.loadUrl(url);
//                }
//                return true;
//            }
//        });

        mWebview.setWebChromeClient(new SystemWebChromeClient(mWebview.getParentEngine())

                                    {
                                        @Override
                                        public void onReceivedTitle(WebView view, String title) {
                                            super.onReceivedTitle(view, title);
                                            L.i("title:" + title);
                                            String[] infos = title.split("-");
                                            if (infos != null && infos.length > 1) {
                                                String titleStr = infos[0];
                                                String rightStr = infos[1];
                                                if (!TextUtils.isEmpty(titleStr)) {
                                                    tv_h5_title.setText(titleStr);
                                                    tv_h5_title.setVisibility(View.VISIBLE);
                                                }
                                                if (!TextUtils.isEmpty(rightStr)) {
                                                    tv_right.setText(rightStr);
                                                    tv_right.setVisibility(View.VISIBLE);
                                                    tv_right.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            loadUrl("javascript:rightItemClick()");
                                                        }
                                                    });
                                                }
                                            }

                                        }
                                    }
        );

    }

    @Override
    protected CordovaWebView makeWebView() {
        return new CordovaWebViewImpl(new SystemWebViewEngine(mWebview));
    }

    @Override
    protected void createViews() {
        appView.getView().requestFocusFromTouch();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.rl_back,
            R.id.rl_h5_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                if (mWebview.canGoBack()) {
                    mWebview.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.rl_h5_back:
                if (mWebview.canGoBack()) {
                    mWebview.goBack();
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == START_LOGIN_RESULT) {
            String url = "javascript:setToken('" + NetUtil.getToken() + "')";
            mWebview.loadUrl(url);
            loadMyShop(this, true);
        }
    }


    public void loadMyShop(Activity activity, boolean isShow) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("doplace", "home");
        HomeNet.getHomeApi().getMyShop(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<List<MyShopModel>>>(activity, isShow) {
                    @Override
                    public void onSuccess(BasisBean<List<MyShopModel>> response) {
                        if (response.getData() != null) {
                            showShopData(response.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }


    public void showShopData(List<MyShopModel> data) {
        if (data != null && data.size() > 0) {
            if (data.size() == 1) {
                MyShopModel myShopModel = data.get(0);
                myShopModel.storeStutus=true;
                StoreManage.newInstance().saveStore(myShopModel);
                String format = "setStoreIdAndItemId('" + myShopModel.storeid + "','" + myShopModel.itemid + "')";
                mWebview.loadUrl("javascript:" + format);
                return;
            }
            showChooseStoreDialog(data);
        }
    }


    private void showChooseStoreDialog(List<MyShopModel> data) {
        if (mChooseStoreDialog != null) {
            if (mChooseStoreDialog.isShown()) {
                return;
            }
        }
        mChooseStoreDialog = new ChooseStoreDialog(this, data);
        mChooseStoreDialog.builder().show();
        mChooseStoreDialog.setDialogClickListener(new ChooseStoreDialog.DialogClickListener() {
            @Override
            public void select(MyShopModel myShopModel) {
                myShopModel.storeStutus=true;
                StoreManage.newInstance().saveStore(myShopModel);
                String format = "setStoreIdAndItemId('" + myShopModel.storeid + "','" + myShopModel.itemid + "')";
                mWebview.loadUrl("javascript:" + format);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectStore(SelectStoreHhBean bean) {
        loadMyShop(this, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBack(String type) {
        if (type.equals("back")) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
            } else {
                finish();
            }
        }
    }
}
