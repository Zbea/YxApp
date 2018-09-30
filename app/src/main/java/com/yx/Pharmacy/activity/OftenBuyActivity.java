package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;

import butterknife.BindView;

/**
 * 常购商品
 */
public  class OftenBuyActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    @BindView(R.id.iv_more)
    ImageView iv_more;



    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, OftenBuyActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_often_buy;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        initView();
        initData();

    }
    private void initView() {
        tv_title.setText("常购商品");
        rl_more.setVisibility(View.VISIBLE);
        iv_more.setImageResource(R.drawable.dblsyicon);
//        mPresenter = new MyCollectPresenter(this);
    }
    private void initData() {
//        mPresenter.getCollectList(this);
    }



}
