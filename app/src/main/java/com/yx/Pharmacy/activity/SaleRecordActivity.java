package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.SaleRecordAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.SaleRecordModel;
import com.yx.Pharmacy.presenter.SaleRecordPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.view.ISaleRecordView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SaleRecordActivity extends BaseActivity implements ISaleRecordView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    @BindView(R.id.iv_more)
    ImageView iv_more;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;

    @BindView(R.id.tv_num)
    TextView       mTvNum;

    private SaleRecordPresenter mPresenter;
    private SaleRecordAdapter mAdapter;
    private List<SaleRecordModel> models=new ArrayList<>();
    private int page=1;
    private String pid;

    public static void startActivity(Activity activity, String pid) {
        Intent intent = new Intent(activity, SaleRecordActivity.class);
        intent.putExtra("pid",pid);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_record;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        pid=getIntent().getStringExtra("pid");
        initView();
        mPresenter = new SaleRecordPresenter(this);
        mPresenter.getShopcarNum(this);
        mPresenter.getsaleRecordList(this,pid,page,true);
    }

    private void initView() {
        tv_title.setText("销售记录");
//        rl_more.setVisibility(View.VISIBLE);
//        iv_more.setImageResource(R.drawable.bbjacan);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new SaleRecordAdapter(R.layout.item_sale_record,models);
        recyclerview.setAdapter(mAdapter);

        //只适用于LinearLayoutManager
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initNestPage();
            }
        },recyclerview);
        swipeRefreshLayout.setOnRefreshListener(this);


    }

    private void initNestPage() {
        mPresenter.getsaleRecordList(this,pid,page+1,true);
    }

    @OnClick({R.id.rl_back, R.id.rl_more, R.id.iv_home, R.id.iv_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_more://收藏
                mPresenter.collectProduct(this,pid);
                break;
            case R.id.iv_home:
                MainActivity.startActivity(this);
                break;
            case R.id.iv_cart:
                MainActivity.startActivity(this);
                break;
            case R.id.tv_add_cart:
                // 加入购物车
                mPresenter.addCartProduct(this,pid);
                break;

        }
    }

    @Override
    public void getSaleRecordList(List<SaleRecordModel> data) {
        if(data.size()>0){
            mAdapter.addData(data);
            page++;
            mAdapter.loadMoreComplete();
            if(data.size()< Constants.PAGESIZE){
                mAdapter.loadMoreEnd();
            }
        }else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void refreshSaleRecordList(List<SaleRecordModel> data) {
        mAdapter.setNewData(data);
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        if(data.size()< Constants.PAGESIZE){
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void noSaleRecordList() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddResult(AddShopCartModel data) {
        // 添加成功
        mTvNum.setText(DensityUtils.parseInt(data.count)>99 ? "99+" : data.count);
        mTvNum.setVisibility(DensityUtils.parseInt(data.count)==0?View.GONE:View.VISIBLE);
    }
    @Override
    public void getShopCarNum(String count) {
        mTvNum.setText(DensityUtils.parseInt(count)>99 ? "99+" : count);
        mTvNum.setVisibility(DensityUtils.parseInt(count)==0?View.GONE:View.VISIBLE);
    }
    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getsaleRecordList(this,pid,page,true);
    }
}
