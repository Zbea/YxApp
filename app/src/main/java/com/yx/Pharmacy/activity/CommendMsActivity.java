package com.yx.Pharmacy.activity;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   CommendMsActivity
 *  @创建者:   CC
 *  @创建时间:  2018/8/11 6:00
 *  @描述：    TODO
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.VPageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.fragment.MiaoShaFragment;
import com.yx.Pharmacy.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommendMsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.vp_order)
    ViewPager            vp_order;
    @BindView(R.id.topic_viewpager_title)
    PagerSlidingTabStrip topic_viewpager_title;

    private List<Fragment> fragments =new ArrayList<>();
    private List<String>   titles    =new ArrayList<>();
    private VPageAdapter pageAdapter;

    private int curpage;
    private String levelId;
    private String title;
    public static void startActivity(Activity activity, String levelid) {
        Intent intent = new Intent(activity, CommendMsActivity.class);
        intent.putExtra("levelId", levelid);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, String levelid,String titleStr) {
        Intent intent = new Intent(activity, CommendMsActivity.class);
        intent.putExtra("levelId", levelid);
        intent.putExtra("title", titleStr);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_ms;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        levelId = getIntent().getStringExtra("levelId");
        title = getIntent().getStringExtra("title");
        initView();
    }

    private void initView() {
        if (TextUtils.isEmpty(title))
        {
            tv_title.setText("秒杀专区");
        }
        else
        {
            tv_title.setText(title);
        }

        titles.add("正在抢购");
        titles.add("等待开抢");
        Fragment fragment1= MiaoShaFragment.newInstance(1,levelId);
        Fragment fragment2=MiaoShaFragment.newInstance(2,levelId);
        fragments.add(fragment1);
        fragments.add(fragment2);
        pageAdapter=new VPageAdapter(getSupportFragmentManager(),fragments,titles);
        vp_order.setAdapter(pageAdapter);
        topic_viewpager_title.setViewPager(vp_order);
    }

    @OnClick({R.id.rl_back})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
