package com.yx.Pharmacy.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.AfterOrderDetailActivity;
import com.yx.Pharmacy.adapter.OrderAfterAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.dialog.TuihuoDialog;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.presenter.AfterOrderListPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.view.IAfterOrderListView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KID on 2018/7/14.
 * 订单(待支付，待发货，待收货，已完成，售后)
 */

public class AfterOrderFragment extends BaseFragment  implements IAfterOrderListView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;

    private AfterOrderListPresenter mPresenter;
    private int page=1;

    private OrderAfterAdapter orderAdapter;
    private List<OrderModel> orderModels=new ArrayList<>();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser)
        {
            if (mPresenter!=null)
            {
                page=1;
                mPresenter.getAfterOrderListData((BaseActivity)mContext,status,page,true);
            }
        }
    }

    private static final String STATUS="status";
    private int status;
    public static AfterOrderFragment newInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt(STATUS, status);
        AfterOrderFragment fragment = new AfterOrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frag_order;
    }

    @Override
    protected void init() {
        status=getArguments().getInt(STATUS,1);
        initView();
        mPresenter = new AfterOrderListPresenter(this);
        mPresenter.getAfterOrderListData((BaseActivity)mContext,status,page,true);
    }

    private void initView() {

        final LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(layoutManager);
        int itemDecoration = DensityUtils.dp2px(mContext, 10);
        recyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));
        orderAdapter=new OrderAfterAdapter(mContext,R.layout.item_my_after_order,orderModels);
        recyclerview.setAdapter(orderAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        //只适用于LinearLayoutManager
//        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
//                    if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {
//                        initNestPage();
//                    }
//                }
//            }
//        });
        orderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initNestPage();
            }
        },recyclerview);
        orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AfterOrderDetailActivity.startActivity(mContext,orderAdapter.getData().get(position).orderbackid);
            }
        });
        orderAdapter.setListener(clickListener);
    }
    //加载下一页
    private void initNestPage() {
        mPresenter.getAfterOrderListData((BaseActivity)mContext,status,page+1,false);
    }
    @Override
    public void getOrderList(List<OrderModel> data) {//获取下一页数据成功,page+1
        if(data.size()>0){
            orderAdapter.addData(data);
            page++;
            orderAdapter.loadMoreComplete();
        }else {
            orderAdapter.loadMoreEnd();
        }
    }
    @Override
    public void refreshOrderList(List<OrderModel> data) {
        L.i("status:"+status+":"+data.size());
        orderAdapter.setNewData(data);
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        if(data.size()<4){//当数据不满一屏时，会出bug，所以加入此部操作
            orderAdapter.loadMoreEnd();
        }
    }
    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getAfterOrderListData((BaseActivity)mContext,status,page,true);
    }
    @Override
    public void noOrderList() {//没有订单
        showNoData();
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

    @Override
    public void cancelOrderBack(int position) {//撤销售后订单申请成功
        orderAdapter.remove(position);
    }

    @Override
    public void onErrorPage() {
        showErrorPage();
    }

    @Override
    public void tuihuoBack() {
        page=1;
        mPresenter.getAfterOrderListData((BaseActivity)mContext,status,page,true);
    }

    //适配器点击事件回调
    private OrderAfterAdapter.ClickListener clickListener=new OrderAfterAdapter.ClickListener() {
        @Override
        public void checkWuliu(String orderid) {
        }

        @Override
        public void cancelOrderBack(String orderbackid, int layoutPosition) {//撤销售后申请
            mPresenter.cancelOrderBack((BaseActivity) mContext,orderbackid,layoutPosition);
        }

        @Override
        public void tuihuo(String orderbackid, int layoutPosition) {
            TuihuoDialog tuihuoDialog=new TuihuoDialog(mContext);
            tuihuoDialog.builder().show();
            tuihuoDialog.setDialogClickListener(new TuihuoDialog.DialogClickListener() {
                @Override
                public void commitTuihuo(String name,String num) {
                    mPresenter.tuihuo((BaseActivity) mContext,orderbackid,name,num);
                }
            });

        }

        @Override
        public void gotoDetails(String orderid, int layoutPosition) {
            AfterOrderDetailActivity.startActivity(mContext,orderid);
        }
    };
    @OnClick({R.id.tv_reload})
    public void click(View v){
        switch (v.getId()){
            case R.id.tv_reload://重新加载
                List<OrderModel>models=new ArrayList<>();
                orderAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getAfterOrderListData((BaseActivity)mContext,status,page,true);
                break;
        }
    }
}
