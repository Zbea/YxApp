package com.yx.Pharmacy.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.adapter.ListDeviderDecoration;
import com.yx.Pharmacy.adapter.MiaoShaAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.presenter.MiaoShaPresenter;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.view.IMiaoShaView;
import com.yx.Pharmacy.widget.LoadingLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by KID on 2018/7/14.
 * 订单(待支付，待发货，待收货，已完成，售后)
 */

public class MiaoShaFragment
        extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener,IMiaoShaView
{


    private static final String STATUS = "status";
    @BindView(R.id.recyclerview)
    RecyclerView       mRecyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.loadinglayout)
    LoadingLayout      mLoadinglayout;
    private int status;
    private MiaoShaPresenter mPresenter;
    private String mLevelId;
    private MiaoShaAdapter mMiaoShaAdapter;

    public static MiaoShaFragment newInstance(int status,String levelId) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS, status);
        bundle.putString("levelId", levelId);
        MiaoShaFragment fragment = new MiaoShaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_miaosha;
    }

    @Override
    protected void init() {
        status = getArguments().getInt(STATUS, 1);
        mLevelId = getArguments().getString("levelId");
        mPresenter = new MiaoShaPresenter(this);
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager =new LinearLayoutManager(mContext);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.addItemDecoration(new ListDeviderDecoration(mContext));
        mMiaoShaAdapter = new MiaoShaAdapter(R.layout.item_home_product_detail);
        mRecyclerview.setAdapter(mMiaoShaAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mLoadinglayout.setStatus(LoadingLayout.Loading);
        mPresenter.loadProduct((BaseActivity) mContext,mLevelId,status);

        mMiaoShaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailActivity.startActivity(mContext,String.valueOf(mMiaoShaAdapter.getData().get(position).getItemid()));
            }
        });

        mMiaoShaAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.moreProduct((BaseActivity) mContext,mLevelId,status);
            }
        },mRecyclerview);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadProduct((BaseActivity) mContext,mLevelId,status);
    }

    @Override
    public void getProductListResult(List<DrugModel> data, String extention) {
        if (!TextUtils.isEmpty(extention)) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT );
            imageView.setLayoutParams(layoutParams);
            GlideUtil.loadImgNoStyle(mContext,extention,imageView,R.drawable.icon_image_loading_cc);
            mMiaoShaAdapter.setHeaderView(imageView);
        }
        mLoadinglayout.setStatus(LoadingLayout.Success);
        if(mSwipeRefreshLayout!=null)mSwipeRefreshLayout.setRefreshing(false);
        if (data!=null&&data.size()>0) {
            mLoadinglayout.setStatus(LoadingLayout.Success);
            mMiaoShaAdapter.setNewData(data);
            if (data.size()< Constants.PAGESIZE){
                mMiaoShaAdapter.loadMoreEnd();
            }
        }else {
            mLoadinglayout.setStatus(LoadingLayout.Empty);
        }
    }

    @Override
    public void moreProductListResult(List<DrugModel> data) {
        mMiaoShaAdapter.addData(data);
        if (data.size()<Constants.PAGESIZE){
            mMiaoShaAdapter.loadMoreEnd();
        }else {
            mMiaoShaAdapter.loadMoreComplete();
        }
    }
}
