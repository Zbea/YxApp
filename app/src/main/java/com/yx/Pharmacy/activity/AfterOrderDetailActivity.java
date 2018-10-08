package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.GiftListAdapter;
import com.yx.Pharmacy.adapter.GoodsListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.BackOrderDetailPresenter;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IBackOrderDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AfterOrderDetailActivity extends BaseActivity implements IBackOrderDetailView {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_order_state)
    TextView tv_order_state;
    @BindView(R.id.tv_order_state_desc)
    TextView tv_order_state_desc;
    @BindView(R.id.tv_backorderid)
    TextView tv_backorderid;
    @BindView(R.id.tv_applyfor_time)
    TextView tv_applyfor_time;
    @BindView(R.id.tv_refund_amount)
    TextView tv_refund_amount;
    @BindView(R.id.tv_order_id)
    TextView tv_order_id;
    @BindView(R.id.tv_order_num)
    TextView tv_order_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView_gift)
    RecyclerView recyclerView_gift;
    @BindView(R.id.ll_order_list)
    LinearLayout ll_order_list;
    @BindView(R.id.view_gift_line)
    View view_gift_line;
    @BindView(R.id.ll_open)
    LinearLayout ll_open;
    @BindView(R.id.ll_close)
    LinearLayout ll_close;
    @BindView(R.id.rl_wuliu_info)
    RelativeLayout rl_wuliu_info;
    @BindView(R.id.tv_wuliu_info)
    TextView tv_wuliu_info;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_audit_info)
    TextView tvAuditInfo;
    @BindView(R.id.ll_audit_info)
    LinearLayout llAuditInfo;
    @BindView(R.id.tv_receive_info)
    TextView tvReceiveInfo;
    @BindView(R.id.ll_receive_info)
    LinearLayout llReceiveInfo;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.btn_ok)
    Button btnOk;


    private GoodsListAdapter goodsListAdapter;
    private BackOrderDetailPresenter mPresenter;
    private String orderbackid;
    private OrderModel model;
    private List<OrderModel.Goods> goods = new ArrayList<>();

    private GiftListAdapter giftListAdapter;
    private List<OrderModel.Goods> gifts = new ArrayList<>();

    public static void startActivity(Activity activity, String orderbackid) {
        Intent intent = new Intent(activity, AfterOrderDetailActivity.class);
        intent.putExtra("orderbackid", orderbackid);
        activity.startActivity(intent);
    }

    private int fromAskAfter;

    public static void startActivity(Activity activity, String orderbackid, int fromAskAfter) {
        Intent intent = new Intent(activity, AfterOrderDetailActivity.class);
        intent.putExtra("orderbackid", orderbackid);
        intent.putExtra("fromAskAfter", 1);//从售后订单跳过来的，点击返回要跳首页
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_order_detail;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        orderbackid = getIntent().getStringExtra("orderbackid");
        fromAskAfter = getIntent().getIntExtra("fromAskAfter", -1);
        Log.e("kid", "orderbackid====" + orderbackid);
        initView();
        mPresenter = new BackOrderDetailPresenter(this);
        mPresenter.getOrderDetailData(this, orderbackid);
    }

    private void initView() {
        tv_title.setText("售后订单详情");

        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list, goods, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(goodsListAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        giftListAdapter = new GiftListAdapter(R.layout.item_gifts_list, gifts);
        recyclerView_gift.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_gift.setAdapter(giftListAdapter);
        recyclerView_gift.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.rl_back, R.id.ll_open, R.id.ll_close, R.id.iv_service,R.id.btn_ok})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                if (fromAskAfter == 1) {
                    finish();
                    MainActivity.startActivity(this);
                } else {
                    finish();
                }
                break;
            case R.id.ll_open:
                ll_open.setVisibility(View.GONE);
                ll_close.setVisibility(View.VISIBLE);
                ll_order_list.setVisibility(View.VISIBLE);
                if (giftListAdapter.getData().size() > 0) {
                    recyclerView_gift.setVisibility(View.VISIBLE);
                    view_gift_line.setVisibility(View.VISIBLE);
                } else {
                    recyclerView_gift.setVisibility(View.GONE);
                    view_gift_line.setVisibility(View.GONE);
                }

                break;
            case R.id.ll_close:
                ll_close.setVisibility(View.GONE);
                ll_open.setVisibility(View.VISIBLE);
                ll_order_list.setVisibility(View.GONE);
                recyclerView_gift.setVisibility(View.GONE);
                view_gift_line.setVisibility(View.GONE);
                break;
            case R.id.iv_service:
                YSFUserInfo userInfo = new YSFUserInfo();
                String title = "";
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    title = "游客" + DensityUtils.getRandomString(16);
                    userInfo.userId = title;
                } else {
                    if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                        title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE);
                    } else {
                        title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
                    }
                    userInfo.userId = NetUtil.getToken();
                }
                userInfo.data = "[{\"key\":\"real_name\", \"value\":" + title + "}]";
                Unicorn.setUserInfo(userInfo);
                ConsultSource source = new ConsultSource("我的门店", title, "custom information string");
                Unicorn.openServiceActivity(mContext, "源鑫药业", source);
                break;
            case R.id.btn_ok:
                mPresenter.cancelOrderBack((BaseActivity) mContext,orderbackid);
                break;
        }
    }

    @Override
    public void getOrderDetailData(OrderModel data) {
        if (data != null) {
            model = data;
            //status：售后订单的状态  0已申请、1审核中，6.已撤销 7通过处理中，8不通过，9已完成
            if (model.status == 0 || model.status == 1) {
                tv_order_state.setText("审核中");
                tvState.setText("审核中");
                tv_order_state_desc.setText("请保持电话畅通，客服将尽快与您联系");
                btnOk.setVisibility(View.VISIBLE);
            } else if (model.status == 6) {
                tv_order_state.setText("已撤销");
                tvState.setText("已撤销");
                tv_order_state_desc.setText("如有需要可在对应订单中再次申请");
            } else if (model.status == 7) {
                tv_order_state.setText("退款中");
                tvState.setText("退款中");
                tv_order_state_desc.setText("货款已退回到您的账户，请注意查收");
            } else if (model.status == 8) {
                tv_order_state.setText("审核不通过");
                tvState.setText("不同意");
                tvState.setTextColor(getResources().getColor(R.color.red));
                tv_order_state_desc.setText("非常抱歉您的退款申请不通过");
                ivBg.setImageResource(R.drawable.icon_sale_afer_details_bg_error);
            } else if (model.status == 9) {
                tv_order_state.setText("已完成");
                tvState.setText("已完成");
                tv_order_state_desc.setText("货款已退回到您的账户");
            }

            tv_order_id.setText(model.orderid);
            tv_backorderid.setText(model.orderbackid);
            tv_applyfor_time.setText(DateUtil.formatyyyyMMddHHmmss(Long.valueOf(model.addtime + "000")));
            tv_refund_amount.setText(model.price);
            tv_order_num.setText("数量" + model.count);
            tvDiscount.setText("整单折扣率"+model.discount);
            tvReason.setText(model.reason);
            tvType.setText(model.type==1?"退货退款":"仅退款");
            if (!TextUtils.isEmpty(model.recordnote))
            {
                tvAuditInfo.setText(model.recordnote);
            }
            if (!TextUtils.isEmpty(model.checknote))
            {
                tvReceiveInfo.setText(model.checknote);
            }

            goodsListAdapter.setNewData(model.goodsList);
            giftListAdapter.setNewData(model.gift);
            //退货物流
            if (!TextUtils.isEmpty(data.send_no) && !TextUtils.isEmpty(data.send_type)) {
                rl_wuliu_info.setVisibility(View.VISIBLE);
                tv_wuliu_info.setText(data.send_no + data.send_type);
            } else {
                rl_wuliu_info.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void cancelOrderBack() {
        btnOk.setVisibility(View.GONE);
        tv_order_state.setText("已撤销");
        tvState.setText("已撤销");
        tv_order_state_desc.setText("如有需要可在对应订单中再次申请");
    }

    @Override
    public void onBackPressed() {
        if (fromAskAfter == 1) {
            finish();
            MainActivity.startActivity(this);
        } else {
            finish();
        }
    }

}
