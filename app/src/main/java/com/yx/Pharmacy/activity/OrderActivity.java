package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.VPageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.fragment.OrderFragment;
import com.yx.Pharmacy.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;

    @BindView(R.id.vp_order)
    ViewPager vp_order;
    @BindView(R.id.topic_viewpager_title)
    PagerSlidingTabStrip topic_viewpager_title;

    private List<Fragment> fragments=new ArrayList<>();
    private List<String>titles=new ArrayList<>();
    private VPageAdapter pageAdapter;

    private int curpage;
    public static void startActivity(Activity activity,int curpage) {
        Intent intent = new Intent(activity, OrderActivity.class);
        intent.putExtra("curpage",curpage);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        curpage=getIntent().getIntExtra("curpage",0);
        initView();
    }

    private void initView() {
        tv_title.setText("我的订单");
        tv_more.setVisibility(View.VISIBLE);
        tv_more.setText("售后订单");
        findViewById(R.id.iv_service).setVisibility(View.GONE);
        titles.add("待支付");
        titles.add("待发货");
        titles.add("待收货");
        titles.add("已完成");
        titles.add("已取消");
        Fragment fragment1=OrderFragment.newInstance("1");
        Fragment fragment2=OrderFragment.newInstance("2");
        Fragment fragment3=OrderFragment.newInstance("3-7");
        Fragment fragment4=OrderFragment.newInstance("9");
        Fragment fragment5=OrderFragment.newInstance("8");
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        pageAdapter=new VPageAdapter(getSupportFragmentManager(),fragments,titles);
        vp_order.setAdapter(pageAdapter);
        topic_viewpager_title.setViewPager(vp_order);
        vp_order.setOffscreenPageLimit(5);
        vp_order.setCurrentItem(curpage);
    }

    @OnClick({R.id.rl_back,R.id.tv_more})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_more://售后订单
                AfterSaleActivity.startActivity(this);
                break;
        }
    }
}
