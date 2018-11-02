package com.yx.Pharmacy.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.MyShopAddActivity;
import com.yx.Pharmacy.activity.AfterOrderDetailActivity;
import com.yx.Pharmacy.activity.CommendMsActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.CommendTjActivity;
import com.yx.Pharmacy.activity.MessageDetailActivity;
import com.yx.Pharmacy.activity.OrderDetailActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.adapter.MessageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.manage.MessageIsReadNumManage;
import com.yx.Pharmacy.model.MessageData;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.MessagePresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.view.IMessageView;
import com.yx.Pharmacy.widget.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.yx.Pharmacy.R.id.recyclerview;


/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.fragment
 *  @文件名:   MessageFragment
 *  @创建者:   CC
 *  @创建时间:  2018/8/7 22:32
 *  @描述：    TODO
 */

public class MessageFragment
        extends BaseFragment implements IMessageView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,MessageIsReadNumManage.MessageIsReadNumManageListener {
    @BindView(R.id.tv_title)
    TextView           mTvTitle;
    @BindView(recyclerview)
    RecyclerView       mRecyclerview;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_nodata)
    LinearLayout       ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;
    @BindView(R.id.tv_reload)
    TextView tv_reload;
    private MessagePresenter mPresenter;

    //通知数量的小红点
    private TextView tv_wuliu_num;
    private TextView tv_system_notice_num;
    private TextView tv_youhui_num;
    private TextView tv_arrive_notice_num;

    private MessageAdapter mAdapter;
    private List<MessageData.MessageModel>messageModels=new ArrayList<>();
    private int page=1;
    private MessageIsReadNumManage mMessageIsReadManage;
    private boolean isHidded;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_message;
    }

    private ImmersionBar mImmersionBar;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidded=hidden;
//        if (!hidden) {
//            onRefresh();
//        }
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }
    @Override
    protected void init() {
        mMessageIsReadManage=MessageIsReadNumManage.newInstance();
        mMessageIsReadManage.setMessageIsReadNumManageListener(this);
        EventBus.getDefault().register(this);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        initView();
        mPresenter = new MessagePresenter(this);
        initData();
    }

    public void initData() {
        page=1;
        mPresenter.getMessageData((BaseActivity) mContext,page);
    }


    private void initView() {
        final LinearLayoutManager layoutManager=new LinearLayoutManager(mContext);
        mRecyclerview.setLayoutManager(layoutManager);
        mAdapter=new MessageAdapter(mContext,R.layout.item_message,messageModels);
        mRecyclerview.setAdapter(mAdapter);
        int itemDecoration = DensityUtils.dp2px(mContext, 10);
        mRecyclerview.addItemDecoration(new SpacesItemDecoration(0, itemDecoration));

        tv_reload.setOnClickListener(this);
        //添加头部
        View headView =mContext.getLayoutInflater().inflate(R.layout.head_message,null);
        headView.findViewById(R.id.rl_wuliu).setOnClickListener(this);
        headView.findViewById(R.id.rl_system_notice).setOnClickListener(this);
        headView.findViewById(R.id.rl_youhui).setOnClickListener(this);
        headView.findViewById(R.id.rl_arrive_notice).setOnClickListener(this);
        headView.findViewById(R.id.tv_all_read).setOnClickListener(this);
        tv_wuliu_num=headView.findViewById(R.id.tv_wuliu_num);
        tv_system_notice_num=headView.findViewById(R.id.tv_system_notice_num);
        tv_youhui_num=headView.findViewById(R.id.tv_youhui_num);
        tv_arrive_notice_num=headView.findViewById(R.id.tv_arrive_notice_num);
        mAdapter.setHeaderView(headView);

        //只适用于LinearLayoutManager
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
//        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                initNestPage();
//            }
//        },mRecyclerview);
        swipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setListener(listener);

    }
    private void initNestPage() {
        mPresenter.getMessageData((BaseActivity) mContext,page+1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_wuliu://交易物流
                MessageDetailActivity.startActivity(mContext,1);
                break;
            case R.id.rl_system_notice://系统通知
                MessageDetailActivity.startActivity(mContext,2);
                break;
            case R.id.rl_youhui://优惠促销
                MessageDetailActivity.startActivity(mContext,3);
                break;
            case R.id.rl_arrive_notice://到货提醒
                MessageDetailActivity.startActivity(mContext,4);
                break;
            case R.id.tv_all_read://全部已读
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    mAdapter.getData().get(i).isread=1;
                    mAdapter.notifyDataSetChanged();
                }
                mPresenter.sendReadAllMessage((BaseActivity) mContext);
                mMessageIsReadManage.allRead();
                break;
            case R.id.tv_reload:
                List<MessageData.MessageModel>models=new ArrayList<>();
                mAdapter.setNewData(models);
                showNornaml();
                page=1;
                mPresenter.getMessageData((BaseActivity) mContext,page);
                break;
        }
    }

    @Override
    public void getMessageData(MessageData data) {
        mMessageIsReadManage.setInitNum(1,data.transaction);
        mMessageIsReadManage.setInitNum(2,data.system);
        mMessageIsReadManage.setInitNum(3,data.activity);
        mMessageIsReadManage.setInitNum(4,data.notification);
        mMessageIsReadManage.print();
        if(data.transaction>0){
            tv_wuliu_num.setVisibility(View.VISIBLE);
            tv_wuliu_num.setText(data.transaction+"");
        }else {
            tv_wuliu_num.setVisibility(View.GONE);
        }
        if(data.system>0){
            tv_system_notice_num.setVisibility(View.VISIBLE);
            tv_system_notice_num.setText(data.system+"");
        }else {
            tv_system_notice_num.setVisibility(View.GONE);
        }
        if(data.activity>0){
            tv_youhui_num.setVisibility(View.VISIBLE);
            tv_youhui_num.setText(data.activity+"");
        }else {
            tv_youhui_num.setVisibility(View.GONE);
        }
        if(data.notification>0){
            tv_arrive_notice_num.setVisibility(View.VISIBLE);
            tv_arrive_notice_num.setText(data.notification+"");
        }else {
            tv_arrive_notice_num.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRefreshReadNum(int type, int num) {
        L.i("111111111111111111");
        mMessageIsReadManage.print();
        if (type==1)
        {
            if(num>0){
                tv_wuliu_num.setVisibility(View.VISIBLE);
                tv_wuliu_num.setText(num+"");
            }else {
                tv_wuliu_num.setVisibility(View.GONE);
            }
        }
        if (type==2)
        {
            if(num>0){
                tv_system_notice_num.setVisibility(View.VISIBLE);
                tv_system_notice_num.setText(num+"");
            }else {
                tv_system_notice_num.setVisibility(View.GONE);
            }
        }
        if (type==3)
        {
            if(num>0){
                tv_youhui_num.setVisibility(View.VISIBLE);
                tv_youhui_num.setText(num+"");
            }else {
                tv_youhui_num.setVisibility(View.GONE);
            }
        }
        if (type==4)
        {
            if(num>0){
                tv_arrive_notice_num.setVisibility(View.VISIBLE);
                tv_arrive_notice_num.setText(num+"");
            }else {
                tv_arrive_notice_num.setVisibility(View.GONE);
            }
        }

        onRefresh();

    }

    @Override
    public void refreshMessageList(List<MessageData.MessageModel> data) {
        if(data!=null&&data.size()>0){
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            ll_nodata.setVisibility(View.GONE);
            ll_error.setVisibility(View.GONE);
            mAdapter.setNewData(data);
        }else {
            swipeRefreshLayout.setVisibility(View.GONE);
            ll_error.setVisibility(View.GONE);
            ll_nodata.setVisibility(View.VISIBLE);
        }
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void addMessage(List<MessageData.MessageModel> data) {
        if(data.size()>0){
            mAdapter.addData(data);
            page++;
            mAdapter.loadMoreComplete();
        }else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void noMessageData() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorPage() {
        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
    }
    private void showNornaml(){
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onRefresh() {
        page=1;
        mPresenter.getMessageData((BaseActivity) mContext,page);
    }
    private MessageAdapter.MessageClickListener listener=new MessageAdapter.MessageClickListener() {
        @Override
        public void goWebActivity(String weburl) {//跳转网页
            HHActivity.startActivity(mContext,weburl);
        }

        @Override
        public void goProductDetail(String pid) {//跳转商品详情
            ProductDetailActivity.startActivity(mContext,pid);
        }

        @Override
        public void goOrderDetail(String orderid) {
            OrderDetailActivity.startActivity(mContext,orderid);
        }

        @Override
        public void goBackOrderDetail(String orderbackid) {
            AfterOrderDetailActivity.startActivity(mContext,orderbackid);
        }

        @Override
        public void goMenDianInfo(String itemid) {//跳转门店信息
            MyShopAddActivity.startActivity(mContext,itemid);
        }

        @Override
        public void goZoneActivity(int type,int levelid) {
            CommendProductActivity.startActivity(mContext,""+type,""+levelid);
        }

        @Override
        public void godata7Activity(String levelid, String type) {
            if (TextUtils.equals(type, "1")) {
                // 秒杀
                CommendMsActivity.startActivity(mContext,levelid);
            } else if (TextUtils.equals(type, "2")) {
                // 特价
                CommendTjActivity.startActivity(mContext, type,levelid);
            } else if (TextUtils.equals(type, "3")) {
                // 满减
                CommendProductActivity.startActivity(mContext, type,levelid);
            } else if (TextUtils.equals(type, "9")) {
                // 控销
                CommendProductActivity.startActivity(mContext, type,levelid);
            }
        }

        @Override
        public void readMessage(String itemid, int position) {//已读
            if (mAdapter.getData().size()>0)
            {
                mAdapter.getData().get(position-1).isread=1;
                mAdapter.notifyDataSetChanged();
                mPresenter.sendReadMessage((BaseActivity) mContext,itemid);
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(int t)
    {
        onRefresh();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(NetUtil.getToken())) {
            swipeRefreshLayout.setVisibility(View.GONE);
            ll_error.setVisibility(View.GONE);
            ll_nodata.setVisibility(View.VISIBLE);
        }
    }


}
