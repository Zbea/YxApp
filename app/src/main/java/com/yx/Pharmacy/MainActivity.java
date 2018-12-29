/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.yx.Pharmacy;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yx.Pharmacy.activity.MyShopAddActivity;
import com.yx.Pharmacy.activity.AfterOrderDetailActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.OrderDetailActivity;
import com.yx.Pharmacy.activity.ProductCartActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.activity.SearchActivity;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ComDialog;
import com.yx.Pharmacy.dialog.FunctionGuidDialog;
import com.yx.Pharmacy.fragment.CategoryFragment;
import com.yx.Pharmacy.fragment.HomePageFragment;
import com.yx.Pharmacy.fragment.MessageFragment;
import com.yx.Pharmacy.fragment.MyFragment;
import com.yx.Pharmacy.manage.LocalUrlManage;
import com.yx.Pharmacy.model.SplashData;
import com.yx.Pharmacy.model.UrlBean;
import com.yx.Pharmacy.presenter.MainPresenter;
import com.yx.Pharmacy.receiver.ReceiverDialogManage;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.view.IMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView, ReceiverDialogManage.ReceiverDialogManageListener {
    @BindView(R.id.ll_main_container)
    LinearLayout ll_main_container;
    @BindView(R.id.iv_message)
    ImageView iv_message;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.iv_type)
    ImageView iv_type;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.iv_home_page)
    ImageView iv_home_page;
    @BindView(R.id.tv_home_page)
    TextView tv_home_page;
    @BindView(R.id.iv_shopping_car)
    ImageView iv_shopping_car;
    @BindView(R.id.tv_shopping_car)
    TextView tv_shopping_car;
    @BindView(R.id.iv_my)
    ImageView iv_my;
    @BindView(R.id.tv_my)
    TextView tv_my;
    //当前选中的页面
    private int curPage = 3;
    private Fragment currentFragment;

    private MainPresenter mPresenter;

    private FragmentManager fragmentManager;
    private MessageFragment fragment1;
    private CategoryFragment fragment2;
    private HomePageFragment fragment3;
    private MyFragment fragment5;
    private SelectStoreUtil mSelectStoreUtil;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int startin) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("start_in", startin);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private ImmersionBar mImmersionBar;

    @Override
    protected void init() {

        EventBus.getDefault().register(this);
        ReceiverDialogManage.newInstance().setReceiverDialogManageListener(this);
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
//        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(true, 0.2f).init();

        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getFragments().size() > 0) {
            fragment1 = (MessageFragment) fragmentManager.findFragmentByTag(MessageFragment.class.getName().toString());
            fragment2 = (CategoryFragment) fragmentManager.findFragmentByTag(CategoryFragment.class.getName().toString());
            fragment3 = (HomePageFragment) fragmentManager.findFragmentByTag(HomePageFragment.class.getName().toString());
            fragment5 = (MyFragment) fragmentManager.findFragmentByTag(MyFragment.class.getName().toString());
        }
        showFragment(3);
        mPresenter = new MainPresenter(this);
        mPresenter.getSplashAdData(this);
        mPresenter.getUrl(this);
        if (SPUtil.getBoolean(mContext,"mainFrist",true))
        {
            new FunctionGuidDialog(mContext,0).builder();
            SPUtil.putBoolean(mContext,"mainFrist",false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String type) {
        if (type.equals("gotohome")) {
            showFragment(3);
        }
//        if (type.equals("changeStore")) {
//            notifyData();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int start_in = intent.getIntExtra("start_in", 0);
        if (start_in == 1) {
            showFragment(3);
        }
        else if (start_in == 2) {
            getMyShop();
        }
        else if (start_in == 0) {
            notifyData();
        }
    }

    @OnClick({R.id.ll_message, R.id.ll_type, R.id.ll_home_page, R.id.ll_shopping_car, R.id.ll_my})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_message://消息
                showFragment(1);
                break;
            case R.id.ll_type://分类
                showFragment(2);
                break;
            case R.id.ll_home_page://首页
                showFragment(3);

                break;
            case R.id.ll_shopping_car://购物车
                ProductCartActivity.startActivity(this);
                break;
            case R.id.ll_my://我的
                showFragment(5);
                if (SPUtil.getBoolean(mContext,"myFrist",true))
                {
                    new FunctionGuidDialog(mContext,1).builder();
                    SPUtil.putBoolean(mContext,"myFrist",false);
                }

                break;
        }

    }

    public void showFragment(int page) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);
        switch (page) {
            case 1:
                // 如果fragment1已经存在则将其显示出来
                if (fragment1 != null&&currentFragment!=null) {
                    ft.hide(currentFragment).show(fragment1);
                }
                // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    fragment1 = new MessageFragment();
                    ft.add(R.id.ll_main_container, fragment1, MessageFragment.class.getName().toString());
                }
                currentFragment=fragment1;
                break;
            case 2:
                if (fragment2 != null&&currentFragment!=null)
                    ft.hide(currentFragment).show(fragment2);
                else {
                    fragment2 = new CategoryFragment();
                    ft.add(R.id.ll_main_container, fragment2, CategoryFragment.class.getName().toString());
                }
                currentFragment=fragment2;
                break;
            case 3:
                if (fragment3 != null&&currentFragment!=null) {
                    ft.hide(currentFragment).show(fragment3);
                } else {
                    fragment3 = new HomePageFragment();
                    ft.add(R.id.ll_main_container, fragment3, HomePageFragment.class.getName().toString());
                }
                currentFragment=fragment3;
                break;
            case 4:

                break;
            case 5:
                if (fragment5 != null&&currentFragment!=null) {
                    ft.hide(currentFragment).show(fragment5);
                } else {
                    fragment5 = new MyFragment();
                    ft.add(R.id.ll_main_container, fragment5, MyFragment.class.getName().toString());
                }
                currentFragment=fragment5;
                break;
        }
        ft.commitAllowingStateLoss();
        setSelectPage(page);
    }

    // 当fragment已被实例化，相当于发生过切换，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {
        if (fragment1 != null)
            ft.hide(fragment1);
        if (fragment2 != null)
            ft.hide(fragment2);
        if (fragment3 != null)
            ft.hide(fragment3);
        if (fragment5 != null)
            ft.hide(fragment5);
    }

    public void setSelectPage(int page) {
        if (page == curPage) return;
        iv_message.setImageResource(page == 1 ? R.drawable.cyw : R.drawable.cyd);
        iv_type.setImageResource(page == 2 ? R.drawable.wdfhd : R.drawable.wdfhw);
        iv_home_page.setImageResource(page == 3 ? R.drawable.fhd : R.drawable.fhw);
        iv_shopping_car.setImageResource(page == 4 ? R.drawable.ydd : R.drawable.ydw);
        iv_my.setImageResource(page == 5 ? R.drawable.wdd : R.drawable.wdw);
        tv_message.setTextColor(page == 1 ? getResources().getColor(R.color.color_check1) : getResources().getColor(R.color.color_uncheck));
        tv_type.setTextColor(page == 2 ? getResources().getColor(R.color.color_check1) : getResources().getColor(R.color.color_uncheck));
        tv_home_page.setTextColor(page == 3 ? getResources().getColor(R.color.color_check1) : getResources().getColor(R.color.color_uncheck));
        tv_shopping_car.setTextColor(page == 4 ? getResources().getColor(R.color.color_check1) : getResources().getColor(R.color.color_uncheck));
        tv_my.setTextColor(page == 5 ? getResources().getColor(R.color.color_check1) : getResources().getColor(R.color.color_uncheck));
        curPage = page;
    }

    public void setSelectTab(int selectTab) {
        showFragment(selectTab);
    }

    @Override
    public void getSplashAd(SplashData.SplashModel data) {
        if (data != null) {
            Gson gson = new Gson();
            String json = gson.toJson(data);
            SPUtil.putString(this, Constants.KEY_AD, json);
        } else {
            SPUtil.putString(this, Constants.KEY_AD, "");
        }
    }

    @Override
    public void getUrl(UrlBean data) {
        if (data!=null)
        {
            LocalUrlManage.newInstance().setCount(data);
        }
    }

    public void notifyData() {
        if (fragment1 != null)
            fragment1.initData();
        if (fragment2 != null)
            fragment2.initData();
        if (fragment3 != null)
            fragment3.initData();
        if (fragment5 != null)
            fragment5.initView();
    }

    public void getMyShop() {
       mSelectStoreUtil=new SelectStoreUtil(this,null);
    }

    @Override
    public void onDialogShow(JSONObject jsonObject) {
        if (mContext==null)
        {
            return;
        }
        String pushtype = jsonObject.optString("pushtype");
        ComDialog comDialog = new ComDialog(this);
        comDialog.setTitleView(false).setContent("有新的消息请您查看").setOk("去看看").builder().show();
        comDialog.setDialogClickListener(new ComDialog.DialogClickListener() {
            @Override
            public void cancle() {
                comDialog.cancle();
            }

            @Override
            public void ok() {
                Intent i = null;
                if (TextUtils.equals(pushtype, "1")) {
                    //app跳转活动模块
                    String weburl = jsonObject.optString("weburl");
                    i = new Intent(MainActivity.this, HHActivity.class);
                    i.putExtra(Constants.H5_URL, weburl);
                } else if (TextUtils.equals(pushtype, "2")) {
                    //app跳转商品详情
                    String goodsid = jsonObject.optString("goodsid");
                    i = new Intent(MainActivity.this, ProductDetailActivity.class);
                    i.putExtra(Constants.KEY_ITEM_ID, goodsid);
                } else if (TextUtils.equals(pushtype, "3")) {
                    //app跳转门店认证页
                    String storeid = jsonObject.optString("storeid");
                    i = new Intent(MainActivity.this, MyShopAddActivity.class);
                    i.putExtra("itemid", storeid);
                } else if (TextUtils.equals(pushtype, "4")) {
                    //app携带关键字跳转至搜索页
                    String keyword = jsonObject.optString("keyword");
                    i = new Intent(MainActivity.this, SearchActivity.class);
                    i.putExtra("keyword", keyword);
                } else if (TextUtils.equals(pushtype, "5")) {
                    //跳转到其他专区的活动
                    String activityname = jsonObject.optString("activityname");
                    String levelid = jsonObject.optString("levelid");
                    String type = jsonObject.optString("type");
                    i = new Intent(MainActivity.this, CommendProductActivity.class);
                    i.putExtra("activityname", activityname);
                    i.putExtra("levelid", levelid);
                    i.putExtra("type", type);
                } else if (TextUtils.equals(pushtype, "6")) {
                    //app跳转订单详情
                    String orderid = jsonObject.optString("orderid");
                    i = new Intent(MainActivity.this, OrderDetailActivity.class);
                    i.putExtra("orderid", orderid);
                } else if (TextUtils.equals(pushtype, "7")) {
                    //app跳转售后订单详情
                    String orderid = jsonObject.optString("orderid");
                    i = new Intent(MainActivity.this, AfterOrderDetailActivity.class);
                    i.putExtra("orderbackid", orderid);
                } else if (TextUtils.equals(pushtype, "8")) {
                    //app跳转商品详情
                    String goodsid = jsonObject.optString("goodsid");
                    i = new Intent(MainActivity.this, ProductDetailActivity.class);
                    i.putExtra(Constants.KEY_ITEM_ID, goodsid);
                } else {
                    comDialog.cancle();
                    return;
                }
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}
