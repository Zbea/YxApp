package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.MessageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.manage.MessageIsReadNumManage;
import com.yx.Pharmacy.model.MessageData;
import com.yx.Pharmacy.presenter.MessageDetailPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.view.IMessageDetailView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IMessageDetailView {

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

    private MessageDetailPresenter mPresenter;
    private MessageAdapter mAdapter;
    private List<MessageData.MessageModel> models=new ArrayList<>();
    private int page=1;
    private int pushtype;

    public static void startActivity(Activity activity,int pushtype) {
        Intent intent = new Intent(activity, MessageDetailActivity.class);
        intent.putExtra("pushtype",pushtype);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        pushtype=getIntent().getIntExtra("pushtype",0);
        initView();
        mPresenter = new MessageDetailPresenter(this);
        mPresenter.getMessageList(this,page,pushtype,true);
    }

    private void initView() {
        if(pushtype==3){
            tv_title.setText("优惠促销");
        }else if(pushtype==4){
            tv_title.setText("到货提醒");
        }else if (pushtype==1){
            tv_title.setText("交易物流");
        }else if(pushtype==2){
            tv_title.setText("系统通知");
        }

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new MessageAdapter(this,R.layout.item_message,models);
        recyclerview.setAdapter(mAdapter);
        int itemDecoration = DensityUtils.dp2px(this, 10);
        recyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initNestPage();
            }
        },recyclerview);
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
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setListener(listener);


    }

    private void initNestPage() {
        mPresenter.getMessageList(this,page+1,pushtype,true);
    }

    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getMessageList(this,page,pushtype,true);
    }

    @Override
    public void getMessageList(List<MessageData.MessageModel> data) {
        if(data!=null&&data.size()>0){
            mAdapter.addData(data);
            page++;
            mAdapter.loadMoreComplete();
        }else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void refreshMessageList(List<MessageData.MessageModel> data) {
        if(data!=null&&data.size()>0){
            mAdapter.setNewData(data);
            if(data.size()<10){
                mAdapter.loadMoreEnd();
            }
        }
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void noMessageList() {
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
                List<MessageData.MessageModel>models=new ArrayList<>();
                mAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getMessageList(this,page,pushtype,true);
                break;
        }
    }
    private MessageAdapter.MessageClickListener listener=new MessageAdapter.MessageClickListener() {
        @Override
        public void goWebActivity(String weburl) {//跳转网页
            HHActivity.startActivity(MessageDetailActivity.this,weburl);
        }

        @Override
        public void goProductDetail(String pid) {//跳转商品详情
            ProductDetailActivity.startActivity(MessageDetailActivity.this,pid);
        }

        @Override
        public void goOrderDetail(String orderid) {
            OrderDetailActivity.startActivity(MessageDetailActivity.this,orderid);
        }

        @Override
        public void goBackOrderDetail(String orderbackid) {
            AfterOrderDetailActivity.startActivity(MessageDetailActivity.this,orderbackid);
        }

        @Override
        public void goMenDianInfo(String itemid) {//跳转门店信息
            MyShopAddActivity.startActivity(MessageDetailActivity.this,itemid);
        }

        @Override
        public void goZoneActivity(int type,int levelid) {
            if (TextUtils.equals(String.valueOf(type), "1")) {
                // 秒杀
                CommendMsActivity.startActivity(MessageDetailActivity.this,""+levelid);
            } else if (TextUtils.equals(String.valueOf(type), "2")) {
                // 特价
                CommendTjActivity.startActivity(MessageDetailActivity.this, String.valueOf(type), ""+levelid);
            } else if (TextUtils.equals(String.valueOf(type), "3")) {
                // 满减
                CommendProductActivity.startActivity(MessageDetailActivity.this, String.valueOf(type), ""+levelid);
            } else if (TextUtils.equals(String.valueOf(type), "9")) {
                // 控销
                CommendProductActivity.startActivity(MessageDetailActivity.this, String.valueOf(type), ""+levelid);
            }
        }

        @Override
        public void godata7Activity(String levelid, String type) {
            if (TextUtils.equals(type, "1")) {
                // 秒杀
                CommendMsActivity.startActivity(MessageDetailActivity.this,levelid);
            } else if (TextUtils.equals(type, "2")) {
                // 特价
                CommendTjActivity.startActivity(MessageDetailActivity.this, type, levelid);
            } else if (TextUtils.equals(type, "3")) {
                // 满减
                CommendProductActivity.startActivity(MessageDetailActivity.this, type,levelid);
            } else if (TextUtils.equals(type, "9")) {
                // 控销
                CommendProductActivity.startActivity(MessageDetailActivity.this, type,levelid);
            }
        }
        @Override
        public void readMessage(String itemid, int position) {
            mAdapter.getData().get(position).isread=1;
            mAdapter.notifyDataSetChanged();
            MessageIsReadNumManage.newInstance().refresh();
            mPresenter.sendReadMessage(MessageDetailActivity.this,itemid);
        }
    };

}
