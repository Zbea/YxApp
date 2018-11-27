package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.VPageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.fragment.CouponFragment;
import com.yx.Pharmacy.manage.LocalUrlManage;
import com.yx.Pharmacy.model.CouponData;
import com.yx.Pharmacy.model.CouponModel;
import com.yx.Pharmacy.model.UrlBean;
import com.yx.Pharmacy.presenter.UserCouponPresenter;
import com.yx.Pharmacy.view.IUserCouponView;
import com.yx.Pharmacy.widget.PagerSlidingTabStrip;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCouponActivity extends BaseActivity implements IUserCouponView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.vp_coupon)
    ViewPager vp_coupon;

    @BindView(R.id.topic_viewpager_title)
    PagerSlidingTabStrip topic_viewpager_title;
    @BindView(R.id.iv_goto)
    ImageView ivGoto;


    //可使用
    private List<CouponModel> useful = new ArrayList<>();
    //已使用
    private List<CouponModel> used = new ArrayList<>();
    //已过期
    private List<CouponModel> outtime = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private CouponFragment usefulFragment;
    private CouponFragment usedFragment;
    private CouponFragment outtimeFragment;
    private VPageAdapter pageAdapter;

    private UserCouponPresenter mPresenter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyCouponActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("优惠劵");
        tv_more.setVisibility(View.GONE);
        tv_more.setText("兑券");

        mPresenter = new UserCouponPresenter(this);
        mPresenter.getCouponList(this);
    }

    @Override
    public void getCouponList(CouponData data) {
        if (data != null) {
            used = data.getUsed();
            useful = data.getUseful();
            outtime = data.getOuttime();
        }
        initFragments();
    }

    @Override
    public void errorData() {//后台数据错乱，此回调来保证至少能出现3个title，在数据正常后还能下拉刷新出正确页面
        if (usedFragment == null && usefulFragment == null && outtimeFragment == null) {
            initFragments();
        }
    }

    private void initFragments() {
        List<String> titles = new ArrayList<>();
        titles.add("已使用");
        titles.add("可用券");
        titles.add("已过期");

        usedFragment = CouponFragment.newInstance(Constants.type_used, used);
        usefulFragment = CouponFragment.newInstance(Constants.type_useful, useful);
        outtimeFragment = CouponFragment.newInstance(Constants.type_outtime, outtime);
        fragments.add(usedFragment);
        fragments.add(usefulFragment);
        fragments.add(outtimeFragment);
        pageAdapter = new VPageAdapter(getSupportFragmentManager(), fragments, titles);
        vp_coupon.setAdapter(pageAdapter);
        topic_viewpager_title.setViewPager(vp_coupon);


        vp_coupon.setCurrentItem(1);
    }

    @OnClick({R.id.rl_back, R.id.rl_more,R.id.iv_goto})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_more:
                HHActivity.startActivity(this, Constants.WEB_COUPON);
                break;
            case R.id.iv_goto:
                UrlBean urlBean= LocalUrlManage.newInstance().getUrlBean();
                if (urlBean!=null)
                {
                    HHActivity.startActivity(mContext, Constants.BASE_URL+urlBean.coupon);
                }
                else
                {
                    HHActivity.startActivity(mContext, Constants.WEB_COUPON);
                }
                break;
        }
    }


}
