package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.WalletListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.model.WalletData;
import com.yx.Pharmacy.presenter.MyWalletListPresenter;
import com.yx.Pharmacy.view.IMyWalletListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WalletDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IMyWalletListView {
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;

    private MyWalletListPresenter mPresenter;
    private WalletListAdapter mAdapter;
    private List<WalletData.WallatModel> wallatModels=new ArrayList<>();
    private int page=1;
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, WalletDetailActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet_detail;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        initView();
        mPresenter = new MyWalletListPresenter(this);
        mPresenter.getMyWalletListData(this,page,true);
    }

    private void initView() {
        tv_title.setText("资金明细");

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new WalletListAdapter(R.layout.item_my_integral,wallatModels);
        recyclerview.setAdapter(mAdapter);


        //只适用于LinearLayoutManager
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
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initNestPage() {
        mPresenter.getMyWalletListData(this,page+1,true);
    }

    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getMyWalletListData(this,page,true);
    }

    @Override
    public void getWalletList(List<WalletData.WallatModel> data) {
        if(data.size()>0){
            mAdapter.addData(data);
            page++;
            mAdapter.loadMoreComplete();
        }else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void refreshWalletList(List<WalletData.WallatModel> data) {
        mAdapter.setNewData(data);
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void noWallList() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
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
