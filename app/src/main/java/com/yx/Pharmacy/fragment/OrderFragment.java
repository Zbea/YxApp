package com.yx.Pharmacy.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.AskForAfterSaleActivity;
import com.yx.Pharmacy.activity.OrderDetailActivity;
import com.yx.Pharmacy.activity.PayActivity;
import com.yx.Pharmacy.activity.WuliuActivity;
import com.yx.Pharmacy.adapter.OrderAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.dialog.ComDialog;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.presenter.MyOrderListPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.view.IMyOrderListView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KID on 2018/7/14.
 * 订单(待支付，待发货，待收货，已完成，售后)
 */

public class OrderFragment extends BaseFragment  implements IMyOrderListView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;

    private MyOrderListPresenter mPresenter;
    private int page=1;

    private OrderAdapter orderAdapter;
    private List<OrderModel> orderModels=new ArrayList<>();

    private static final String STATUS="status";
    private String status;
    public static OrderFragment newInstance(String status) {
        Bundle bundle = new Bundle();
        bundle.putString(STATUS, status);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.frag_order;
    }

    @Override
    protected void init() {
        status=getArguments().getString(STATUS);
        initView();
        mPresenter = new MyOrderListPresenter(this);
        mPresenter.getMyOrderListData((BaseActivity)mContext,status,page,true);
    }

    private void initView() {

        final LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(layoutManager);
        int itemDecoration = DensityUtils.dp2px(mContext, 10);
        recyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));
        orderAdapter=new OrderAdapter(mContext,R.layout.item_my_order,orderModels,false);
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
                OrderDetailActivity.startActivity(mContext,orderAdapter.getData().get(position).orderid);
            }
        });
        orderAdapter.setListener(clickListener);

    }
    //加载下一页
    private void initNestPage() {
        mPresenter.getMyOrderListData((BaseActivity)mContext,status,page+1,true);
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
        orderAdapter.setNewData(data);
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        if(data.size()<4){//当数据不满一屏时，会出bug，所以加入此部操作
            orderAdapter.loadMoreEnd();
        }
    }
    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getMyOrderListData((BaseActivity)mContext,status,page,true);
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
    public void cancelBack(int position) {//取消订单成功后删除适配器item
        orderAdapter.remove(position);
    }
    @Override
    public void comfirmBack(int position) {//确认收货成功后修改item数据
        orderAdapter.getData().get(position).status=7;
        orderAdapter.notifyItemChanged(position);
    }

    @Override
    public void onErrorPage() {
        showErrorPage();
    }

    //适配器点击事件回调
    private OrderAdapter.ClickListener clickListener=new OrderAdapter.ClickListener() {
        @Override
        public void checkWuliu(String orderid) {//查看物流
            WuliuActivity.startActivity(mContext,orderid);
        }
        @Override
        public void cancleOrder(String orderid,int position) {//取消订单
            ComDialog comDialog=new ComDialog(mContext);
            comDialog.setTitle("是否确定取消当前订单？").setContent("取消订单后已支付金额将原路返回至您的账户，请注意查收")
                    .setCancle("否").setOk("是").builder().show();
            comDialog.setDialogClickListener(new ComDialog.DialogClickListener() {
                @Override
                public void cancle() {

                }
                @Override
                public void ok() {
                    mPresenter.cancelOrder((BaseActivity) mContext,orderid,position);
                }
            });
        }

        @Override
        public void goPay(String price, String orderid) {
            PayActivity.startActivity(mContext,price,orderid);
        }


        @Override
        public void bugAgain(String orderid) {//再次采购
            mPresenter.buyAgain((BaseActivity) mContext,orderid);
        }

        @Override
        public void notifySendOrder(String orderid) {//提醒发货
            mPresenter.notifySendOrder((BaseActivity) mContext,orderid);
        }

        @Override
        public void comfirmOrder(String orderid,int position) {//确认收货,暂时没有处理确认后的ui变化
            mPresenter.comfirmReceiveOrder((BaseActivity) mContext,orderid,position);
        }

        @Override
        public void askForAfterSale(String orderid) {//申请售后
            AskForAfterSaleActivity.startActivity((BaseActivity) mContext,orderid);
        }

        @Override
        public void cancelOrderBack(String orderbackid, int layoutPosition) {

        }

        @Override
        public void tuihuo(String orderbackid, int layoutPosition) {

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
                mPresenter.getMyOrderListData((BaseActivity) mContext,"0",page,true);
                break;
        }
    }
}
