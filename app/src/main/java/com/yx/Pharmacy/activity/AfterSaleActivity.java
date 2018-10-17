package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.VPageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.fragment.AfterOrderFragment;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AfterSaleActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.vp_order)
    ViewPager vp_order;
    @BindView(R.id.topic_viewpager_title)
    PagerSlidingTabStrip topic_viewpager_title;

    private List<Fragment> fragments=new ArrayList<>();
    private List<String>titles=new ArrayList<>();
    private VPageAdapter pageAdapter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AfterSaleActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void init() {
        initView();
    }
    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);

        titles.add("审核中");
        titles.add("退款中");
        titles.add("已完成");
        titles.add("审核失败");
        titles.add("已撤销");
        //status：售后订单的状态  0已申请、1审核中，6.已撤销 7通过处理中，8不通过，9已完成
        Fragment fragment1= AfterOrderFragment.newInstance(0);
        Fragment fragment2=AfterOrderFragment.newInstance(7);
        Fragment fragment3=AfterOrderFragment.newInstance(9);
        Fragment fragment4=AfterOrderFragment.newInstance(8);
        Fragment fragment5=AfterOrderFragment.newInstance(6);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        pageAdapter=new VPageAdapter(getSupportFragmentManager(),fragments,titles);
        vp_order.setAdapter(pageAdapter);
        topic_viewpager_title.setViewPager(vp_order);
        vp_order.setOffscreenPageLimit(5);

    }
    @OnClick({R.id.rl_back,R.id.iv_service})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_service:
                contactService();
                break;
        }
    }
}
