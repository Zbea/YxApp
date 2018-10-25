package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.GiftListAdapter;
import com.yx.Pharmacy.adapter.GoodsListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.HomeAdDialog;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.OrderDetailPresenter;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.view.IOrderDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements IOrderDetailView {
    @BindView(R.id.ll_error)
    LinearLayout ll_error;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.tv_detail_todo)
    TextView tv_detail_todo;
    @BindView(R.id.rl_invoice)
    RelativeLayout rlInvoice;

    private HomeAdDialog homeAdDialog;
    private void showError() {
        nestedScrollView.setVisibility(View.GONE);
        tv_detail_todo.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
    }

    private void showNornaml() {
        ll_error.setVisibility(View.GONE);
        tv_detail_todo.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.VISIBLE);
    }


    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_order_state)
    TextView tv_order_state;
    @BindView(R.id.tv_order_state_desc)
    TextView tv_order_state_desc;

    @BindView(R.id.tv_user_info)
    TextView tv_user_info;
    @BindView(R.id.tv_user_address)
    TextView tv_user_address;
    @BindView(R.id.ll_note)
    LinearLayout ll_note;
    @BindView(R.id.tv_note)
    TextView tv_note;//备注
    @BindView(R.id.tv_price)
    TextView tv_price;//合计
    @BindView(R.id.tv_discount)
    TextView tv_discount;
    @BindView(R.id.tv_dikou)
    TextView tv_dikou;
    @BindView(R.id.tv_amount)
    TextView tv_amount;//原总金额
    @BindView(R.id.tv_storename)
    TextView tv_storename;//开具发票的名字
    @BindView(R.id.tv_order_time)
    TextView tv_order_time;//下单时间
    @BindView(R.id.tv_price_payed)
    TextView tv_price_payed;//实付金额

    @BindView(R.id.rl_pay_time)
    RelativeLayout rl_pay_time;
    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;

    @BindView(R.id.tv_order_id)
    TextView tv_order_id;
    @BindView(R.id.tv_product_num)
    TextView tv_product_num;
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

    @BindView(R.id.rl_paytype)
    RelativeLayout rl_paytype;
    @BindView(R.id.tv_paytype)
    TextView tv_paytype;

    private OrderDetailPresenter mPresenter;
    private String orderid;
    private int type = 0;//0未支付1支付成功后查看是否有首单活动
    private String invoiceUrl;
    private OrderModel model;
    private GoodsListAdapter goodsListAdapter;
    private List<OrderModel.Goods> goods = new ArrayList<>();
    private GiftListAdapter giftListAdapter;
    private List<OrderModel.Goods> gifts = new ArrayList<>();
    private Activity mContext;

    public static void startActivity(Activity activity, String orderid) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        intent.putExtra("orderid", orderid);
        activity.startActivity(intent);

    }

    public static void startActivity(Activity activity, int type, String orderid) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("orderid", orderid);
        activity.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void init() {
        mContext = OrderDetailActivity.this;
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        orderid = getIntent().getStringExtra("orderid");
        type = getIntent().getIntExtra("type", 0);
        Log.e("kid", "orderid====" + orderid);
        initView();
        mPresenter = new OrderDetailPresenter(this);
        mPresenter.getOrderDetailData(this, orderid);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.getOrderDetailData(this, orderid);
    }

    private void initView() {
        tv_title.setText("订单详情");

        goodsListAdapter = new GoodsListAdapter(R.layout.item_goods_list, goods, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(goodsListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        giftListAdapter = new GiftListAdapter(R.layout.item_gifts_list, gifts);
        recyclerView_gift.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_gift.setAdapter(giftListAdapter);
        recyclerView_gift.setNestedScrollingEnabled(false);
    }

    @Override
    public void comfirmBack() {//确认收货
        //修改订单状态(重新获取订单数据)
        mPresenter.getOrderDetailData(this, orderid);
    }

    @Override
    public void onErrorPage() {
        showError();
    }

    @Override
    public void getOrderDetailData(OrderModel data) {
        if (type == 1) {
            mPresenter.getAdvanceData(this);
        }
        showNornaml();
        if (data != null) {
            model = data;
            //1未支付，2已支付，3拣货中，，7.已发货，8已取消，9订单完成
            if (model.status == 1) {
                tv_order_state.setText("待付款");
                tv_detail_todo.setText("去支付");
                tv_order_state_desc.setText("下单后" + model.limittime + "内未完成支付订单自动关闭");
                rl_pay_time.setVisibility(View.GONE);
                rl_paytype.setVisibility(View.GONE);
            } else if (model.status == 2) {
                tv_order_state.setText("待发货");
                tv_detail_todo.setText("提醒发货");
                tv_order_state_desc.setText("付款成功，平台将尽快与你联系确认订单");
                rl_pay_time.setVisibility(View.VISIBLE);
                tv_pay_time.setText(DateUtil.formatyyyyMMddHHmmss(Long.valueOf(model.updatetime + "000")));
                rl_paytype.setVisibility(View.VISIBLE);
                tv_paytype.setText(data.payment_type);
            } else if (model.status == 3) {
                tv_order_state.setText("待收货");
                tv_detail_todo.setText("提醒发货");
                tv_order_state_desc.setText("订单确认完毕，仓库出货中");
                rl_pay_time.setVisibility(View.VISIBLE);
                tv_pay_time.setText(DateUtil.formatyyyyMMddHHmmss(Long.valueOf(model.updatetime + "000")));
                rl_paytype.setVisibility(View.VISIBLE);
                tv_paytype.setText(data.payment_type);
            } else if (model.status == 9) {//已完成
                tv_order_state.setText("已完成");
                tv_detail_todo.setText("再次采购");
                tv_order_state_desc.setText("订单已完成,如有需要可再次采购");
                rl_pay_time.setVisibility(View.VISIBLE);
                tv_pay_time.setText(DateUtil.formatyyyyMMddHHmmss(Long.valueOf(model.updatetime + "000")));
                rl_paytype.setVisibility(View.VISIBLE);
                tv_paytype.setText(data.payment_type);
            } else if (model.status == 8) {
                tv_order_state.setText("已取消");
                tv_detail_todo.setText("再次采购");
                tv_order_state_desc.setText("订单已取消,如有需要可再次采购");
                rl_pay_time.setVisibility(View.GONE);
                rl_paytype.setVisibility(View.VISIBLE);
                tv_paytype.setText(data.payment_type);
            } else if (model.status == 7) {
                tv_order_state.setText("待收货");
                tv_detail_todo.setText("确认收货");
                tv_order_state_desc.setText("订单已经发货出库,请保持联系");
                rl_pay_time.setVisibility(View.VISIBLE);
                tv_pay_time.setText(DateUtil.formatyyyyMMddHHmmss(Long.valueOf(model.updatetime + "000")));
                rl_paytype.setVisibility(View.VISIBLE);
                tv_paytype.setText(data.payment_type);
            }
            tv_user_info.setText(model.buyer_name + "  " + model.buyer_mobile);
            tv_user_address.setText(model.buyer_address);
            ll_note.setVisibility(!TextUtils.isEmpty(model.note) ? View.VISIBLE : View.GONE);
            tv_note.setText(!TextUtils.isEmpty(model.note) ? model.note : "");
            tv_price.setText("￥" + model.price);
            tv_discount.setText("￥" + model.disprice);
            tv_dikou.setText("抵扣率" + model.discount);
            tv_amount.setText("￥" + model.amount);
            tv_price_payed.setText("￥" + model.price);//实付金额
            invoiceUrl=model.invoice_url;
            if (!TextUtils.isEmpty(invoiceUrl))
            {
                tv_storename.setText(model.storename);
            }
            else
            {
                tv_storename.setText("未开发票");
            }
            tv_order_time.setText(DateUtil.formatyyyyMMddHHmmss(Long.valueOf(model.add_time + "000")));
            tv_order_id.setText(model.orderid);
            tv_product_num.setText("数量" + String.valueOf(model.number));
            goodsListAdapter.setNewData(model.goodsList);
            giftListAdapter.setNewData(model.gift);
        }
    }

    @Override
    public void showAdvanceData(HomeAdvanceModel data) {
        if (data != null)
            showAdDiaog(data.custom);
    }

    @OnClick({R.id.rl_back, R.id.ll_open, R.id.ll_close, R.id.tv_detail_todo, R.id.tv_reload,R.id.rl_invoice})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
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
            case R.id.tv_detail_todo:
                if (model.status == 1) {//去支付
                    PayActivity.startActivity(this);
                } else if (model.status == 2 || model.status == 3) {//提醒发货
                    mPresenter.notifySendOrder(this, orderid);
                } else if (model.status == 9 || model.status == 8) {//再次采购
                    mPresenter.buyAgain(this, orderid);
                } else if (model.status == 7) {//确认收货
                    mPresenter.comfirmReceiveOrder(this, orderid);
                }
                break;
            case R.id.rl_invoice://点击发票
                if (!TextUtils.isEmpty(invoiceUrl))
                    PdfActivity.startActivity(this,invoiceUrl,orderid);
                break;
            case R.id.tv_reload://重新加载
                mPresenter = new OrderDetailPresenter(this);
                mPresenter.getOrderDetailData(this, orderid);
                break;
        }
    }

    /**
     * 显示广告弹框
     *
     * @param alert
     */
    private void showAdDiaog(HomeAdvanceModel.GoldBean alert) {
        if (homeAdDialog==null)
        {
            homeAdDialog = new HomeAdDialog(this, alert).builder();
            homeAdDialog.setDialogClickListener(new HomeAdDialog.DialogClickListener() {
                @Override
                public void clickAd() {
                    homeAdDialog.cancle();
                    gotoClick(alert);
                }
            });
        }
    }


}
