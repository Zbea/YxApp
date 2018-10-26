package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.MyCollectAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.presenter.MyCollectPresenter;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMyCollectView;
import com.yx.Pharmacy.widget.LoadingLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的收藏（接口无分页）
 */
public  class MyCollectActivity extends BaseActivity implements IMyCollectView, SwipeRefreshLayout.OnRefreshListener, LoadingLayout.OnReloadListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.loadinglayout)
    LoadingLayout mLoadingLayout;
    private MyCollectAdapter mAdapter;
    private List<DrugModel>drugModels=new ArrayList<>();



    private MyCollectPresenter mPresenter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyCollectActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        initView();
        initData();

    }
    private void initView() {
        tv_title.setText("我的收藏");
        rl_more.setVisibility(View.GONE);
        iv_more.setImageResource(R.drawable.dblsyicon);
        mPresenter = new MyCollectPresenter(this);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new MyCollectAdapter(this, R.layout.item_my_collect,drugModels);
        recyclerview.setAdapter(mAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        mLoadingLayout.setOnReloadListener(this);
        mAdapter.setListener(new MyCollectAdapter.MyListener() {
            @Override
            public void cancel(int position, DrugModel model) {
                mPresenter.cancleCollect(MyCollectActivity.this,model.getItemid(),position);
            }
        });

    }
    private void initData() {
        mPresenter.getCollectList(this);
    }

    private void setEmptyView()
    {
        mLoadingLayout.setStatus(LoadingLayout.Empty);
        mLoadingLayout.setEmptyImage(R.drawable.zwtjmdwk);
        mLoadingLayout.setEmptyText("暂无收藏商品");
        mLoadingLayout.setEmptyReloadBtnVisible(false);
    }


    @Override
    public void getCollectList(List<DrugModel> data) {
        if(data!=null&&data.size()>0){
            L.i("sssssssssss");
            mLoadingLayout.setStatus(LoadingLayout.Success);
            mAdapter.setNewData(data);
        }else {
            setEmptyView();
            //            mLoadingLayout.setEmptyReloadButtonText("去首页逛逛");
            swipeRefreshLayout.setEnabled(false);
        }
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void cancelSuccess(int position) {
        mAdapter.remove(position);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_COLLECT, ""+mAdapter.getData().size());
        EventBus.getDefault().post(mAdapter.getData().size());
        if (mAdapter.getData().size()==0)
        {
            setEmptyView();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getCollectList(this);
    }

    @Override
    public void onReload(View v) {
        mPresenter.getCollectList(this);
        swipeRefreshLayout.setEnabled(true);
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
