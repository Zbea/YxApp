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
import com.yx.Pharmacy.adapter.IntegralListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.model.CreditData;
import com.yx.Pharmacy.presenter.MyIntegralListPresenter;
import com.yx.Pharmacy.view.IMyIntegralListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 积分明细
 */
public class IntegralDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IMyIntegralListView {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;

    private MyIntegralListPresenter mPresenter;
    private IntegralListAdapter mAdapter;
    private List<CreditData.CreditModel> wallatModels=new ArrayList<>();
    private int page=1;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, IntegralDetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_detail;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        initView();
        mPresenter = new MyIntegralListPresenter(this);
        mPresenter.getMyIntegralListData(this,page,true);
    }

    private void initView() {
        tv_title.setText("积分明细");

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new IntegralListAdapter(R.layout.item_my_integral,wallatModels);
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
        mPresenter.getMyIntegralListData(this,page+1,true);
    }

    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getMyIntegralListData(this,page,true);
    }

    @Override
    public void getIntegralList(List<CreditData.CreditModel> data) {
        if(data.size()>0){
            mAdapter.addData(data);
            page++;
            mAdapter.loadMoreComplete();
        }else {
            mAdapter.loadMoreEnd();
        }
    }
    @Override
    public void refreshIntegralList(List<CreditData.CreditModel> data) {
        mAdapter.setNewData(data);
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void noIntegralList() {
        showNoData();
    }
    @Override
    public void onErrorPage() {
        showErrorPage();
    }
    private void showNoData() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
    }
    private void showErrorPage() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
    }
    private void showNornaml(){
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.rl_back,R.id.tv_reload})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_reload://重新加载
                List<CreditData.CreditModel>models=new ArrayList<>();
                mAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getMyIntegralListData(this,page,true);
                break;
        }
    }
}
