package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.IntegralListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.CreditData;
import com.yx.Pharmacy.presenter.MyIntegralPresenter;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMyIntegralView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的积分
 */
public class MyIntegralActivity extends BaseActivity implements IMyIntegralView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.tv_intergral)
    TextView tv_intergral;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    private IntegralListAdapter mAdapter;
    private MyIntegralPresenter mPresenter;
    private List<CreditData.CreditModel>creditModels=new ArrayList<>();

    private int page=1;


    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyIntegralActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_integral;
    }

    @Override
    protected void init() {
//        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        ImmersionBar mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();

        initView();


        mPresenter=new MyIntegralPresenter(this);
        mPresenter.getMyIntegralData(this,page);
    }

    private void initView() {
        tv_title.setText("我的积分");
        tv_more.setVisibility(View.VISIBLE);
        tv_more.setText("积分明细");

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new IntegralListAdapter(R.layout.item_my_integral,creditModels);
        recyclerview.setAdapter(mAdapter);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {
                        initNestPage();
                    }
                }
            }
        });

    }
    private void initNestPage() {
        mPresenter.getMyIntegralData(this,page);
    }
    @Override
    public void getIntegralData(CreditData data) {
        page++;
        tv_intergral.setText(data.credit);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_CREDIT,data.credit);
        if(data.creditlist!=null&&data.creditlist.size()>0){
            mAdapter.addData(data.creditlist);
        }
    }
    @OnClick({R.id.rl_back,R.id.tv_more,R.id.rl_liwu})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_more:
                IntegralDetailActivity.startActivity(this);
                break;
            case R.id.rl_liwu:
                HHActivity.startActivity(this, Constants.WEB_COUPON);
                break;
        }
    }
}
