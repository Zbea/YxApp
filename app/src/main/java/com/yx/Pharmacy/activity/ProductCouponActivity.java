package com.yx.Pharmacy.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ProductCouponAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.CouponModel;
import com.yx.Pharmacy.presenter.ProductCouponPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.view.IProductCouponView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductCouponActivity
        extends BaseActivity
        implements IProductCouponView
{

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.recyclerview)
    RecyclerView       recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout       ll_nodata;

    private ProductCouponPresenter mPresenter;
    private String                 pid;
    private ProductCouponAdapter   mAdapter;
    private List<CouponModel> models = new ArrayList<>();

    public static void startActivity(Context context, String pid) {
        Intent intent = new Intent(context, ProductCouponActivity.class);
        intent.putExtra(Constants.KEY_ITEM_ID, pid);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_coupon;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        pid = getIntent().getStringExtra(Constants.KEY_ITEM_ID);
        initView();
        mPresenter = new ProductCouponPresenter(this);
        mPresenter.getProductCouponList(this, pid);
    }

    private void initView() {
        tv_title.setText("领劵");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter = new ProductCouponAdapter(this, models);
        recyclerview.setAdapter(mAdapter);
        int itemDecoration = DensityUtils.dp2px(this, 10);
        recyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));
        mAdapter.setListener(new ProductCouponAdapter.MyListener() {
            @Override
            public void toSave(int couponid) {//领取优惠劵
                mPresenter.getProductCouponList(ProductCouponActivity.this, String.valueOf(couponid));
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getProductCouponList(ProductCouponActivity.this, pid);
            }
        });
    }

    @Override
    public void getProductCounponResult(List<CouponModel> data) {
        if (swipeRefreshLayout != null) { swipeRefreshLayout.setRefreshing(false); }
        if (data != null && data.size() > 0) {
            mAdapter.setNewData(data);
        } else {
            //TODO 设置无数据页面
            swipeRefreshLayout.setVisibility(View.GONE);
            ll_nodata.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
    }
}
