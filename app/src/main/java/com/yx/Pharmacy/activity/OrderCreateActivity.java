package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.CreateOrderGoodsAdapter;
import com.yx.Pharmacy.adapter.GiftListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.FunctionGuidDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.model.CreateOrderIntentModel;
import com.yx.Pharmacy.model.CreateOrderModel;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.CreateOrderPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.DoubleMath;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.StackManager;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.ICreateOrderView;
import com.yx.Pharmacy.widget.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   OrderCreateActivity
 *  @创建者:   CC
 *  @创建时间:  2018/8/7 0:40
 *  @描述：    TODO
 */

public class OrderCreateActivity
        extends BaseActivity implements ICreateOrderView
{
    @BindView(R.id.iv_back)
    ImageView      mIvBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView       mTvTitle;
    @BindView(R.id.tv_more)
    TextView       mTvMore;
    @BindView(R.id.iv_more)
    ImageView      mIvMore;
    @BindView(R.id.rl_more)
    RelativeLayout mRlMore;
    @BindView(R.id.tv_name)
    TextView       mTvName;
    @BindView(R.id.tv_mobile)
    TextView       mTvMobile;
    @BindView(R.id.tv_location)
    TextView       mTvLocation;
    @BindView(R.id.tv_price)
    TextView       mTvPrice;
    @BindView(R.id.tv_coupon_price)
    TextView       mTvCouponPrice;
    @BindView(R.id.tv_yue_price)
    TextView       mTvYuePrice;
    @BindView(R.id.sb_button_yue)
    SwitchButton   mSbButtonYue;
    @BindView(R.id.tv_order_price)
    TextView       mTvOrderPrice;
    @BindView(R.id.tv_company_name)
    TextView     mTvCompanyName;
    @BindView(R.id.sb_button_company)
    SwitchButton mSbButtonCompany;
    @BindView(R.id.tv_product_num)
    TextView     mTvProductNum;
    @BindView(R.id.ll_open)
    LinearLayout mLlOpen;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.recyclerView_gift)
    RecyclerView recyclerView_gift;
    @BindView(R.id.ll_order_list)
    LinearLayout ll_order_list;
    @BindView(R.id.view_gift_line)
    View view_gift_line;


    @BindView(R.id.ll_close)
    LinearLayout mLlClose;
    @BindView(R.id.tv_pay_price)
    TextView     mTvPayPrice;
    @BindView(R.id.tv_create_order)
    TextView     mTvCreateOrder;
    @BindView(R.id.edt_note)
    EditText     mEdtNote;
    private CreateOrderGoodsAdapter mAdapter;
    private ArrayList<OrderModel.Goods> mGoods;
    private ArrayList<OrderModel.Goods> mGifts;
    private GiftListAdapter giftListAdapter;
    private List<OrderModel.Goods> gifts=new ArrayList<>();

    private CreateOrderIntentModel mDatas;
    private double mAllPrice;
    private double mCouponPrice;
    private String mStoreAddress;
    private String mStoreMobile;
    private String mStoreTurename;
    private CreateOrderPresenter mPresenter;
    private double mStoreMoney;
    private double mNeedpay;

    public static void startActivity(Activity activity, ArrayList<OrderModel.Goods> goods,CreateOrderIntentModel model,double allprice,double couponprice,ArrayList<OrderModel.Goods> gifts) {
        Intent intent = new Intent(activity, OrderCreateActivity.class);
        intent.putExtra("products",goods);
        intent.putExtra("data",model);
        intent.putExtra("allprice",allprice);
        intent.putExtra("couponprice",couponprice);
        intent.putExtra("gifts",gifts);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void init() {
        StackManager.getManagerStack().pushActivity(this);
        ImmersionBarUtil.setBarColor(R.color.white, this, true);

        mPresenter = new CreateOrderPresenter(this);
        mPresenter.getOrderNum(this);

        mGoods = (ArrayList<OrderModel.Goods>) getIntent().getSerializableExtra("products");
        mGifts = (ArrayList<OrderModel.Goods>) getIntent().getSerializableExtra("gifts");
        mDatas = (CreateOrderIntentModel) getIntent().getSerializableExtra("data");
        mAllPrice = getIntent().getDoubleExtra("allprice", 0.0);
        mCouponPrice = getIntent().getDoubleExtra("couponprice", 0.0);
        initView();
    }


    private void initView() {
        mTvTitle.setText("确认订单");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CreateOrderGoodsAdapter(R.layout.item_goods_list_corder);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter.setNewData(mGoods);

        giftListAdapter=new GiftListAdapter(R.layout.item_gifts_list,gifts);
        recyclerView_gift.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_gift.setAdapter(giftListAdapter);
        recyclerView_gift.setNestedScrollingEnabled(false);
        giftListAdapter.setNewData(mGifts);


        //门店对应姓名
        mStoreTurename = SPUtil.getString(UiUtil.getContext(), Constants.KEY_TRUENAME);
        mTvName.setText(mStoreTurename);
        // 电话
        mStoreMobile = SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE);
        mTvMobile.setText(mStoreMobile);
        // 地址
        mStoreAddress = SPUtil.getString(UiUtil.getContext(), Constants.KEY_ADDRESS);
        mTvLocation.setText(mStoreAddress);

        mTvCompanyName.setText(SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME));
        mTvCouponPrice.setText("-"+DensityUtils.doubleToString(mCouponPrice));
        mTvPrice.setText(DensityUtils.doubleToString(mAllPrice));

        if (mDatas!=null) {
            double v = mAllPrice - DensityUtils.parseDouble(mDatas.needpay);
            mTvCouponPrice.setText("-"+DensityUtils.doubleToString(v));
            mTvOrderPrice.setText(mDatas.needpay);
            mTvPayPrice.setText(mDatas.needpay);
            mTvProductNum.setText("数量"+mDatas.count);
        }

        mSbButtonYue.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    if (mStoreMoney> mNeedpay){
                        mTvOrderPrice.setText("0.00");
                        mTvPayPrice.setText("0.00");
                    }else {
                        double price = DoubleMath.sub(mNeedpay,mStoreMoney);
                        mTvOrderPrice.setText(DensityUtils.doubleToString(price));
                        mTvPayPrice.setText(DensityUtils.doubleToString(price));
                    }
                }else {
                    mTvOrderPrice.setText(mDatas.needpay);
                    mTvPayPrice.setText(mDatas.needpay);
                }
            }
        });

        if (SPUtil.getBoolean(mContext,"orderFrist",true))
        {
            new FunctionGuidDialog(mContext,3).builder();
            SPUtil.putBoolean(mContext,"orderFrist",false);
        }


    }

    @OnClick({R.id.rl_back,
              R.id.tv_create_order,
              R.id.ll_open,
              R.id.ll_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_open:
//                mLlOpen.setVisibility(View.GONE);
//                mLlClose.setVisibility(View.VISIBLE);
//                mRecyclerView.setVisibility(View.VISIBLE);
                mLlOpen.setVisibility(View.GONE);
                mLlClose.setVisibility(View.VISIBLE);
                ll_order_list.setVisibility(View.VISIBLE);
                if(giftListAdapter.getData().size()>0){
                    recyclerView_gift.setVisibility(View.VISIBLE);
                    view_gift_line.setVisibility(View.VISIBLE);
                }else {
                    recyclerView_gift.setVisibility(View.GONE);
                    view_gift_line.setVisibility(View.GONE);
                }

                break;
            case R.id.ll_close:
//                mLlClose.setVisibility(View.GONE);
//                mLlOpen.setVisibility(View.VISIBLE);
//                mRecyclerView.setVisibility(View.GONE);
                mLlClose.setVisibility(View.GONE);
                mLlOpen.setVisibility(View.VISIBLE);
                ll_order_list.setVisibility(View.GONE);
                recyclerView_gift.setVisibility(View.GONE);
                view_gift_line.setVisibility(View.GONE);
                break;
            case R.id.tv_create_order:
                createOrder();
                break;
        }
    }

    private void createOrder() {
        HashMap<String, Object> address = new HashMap<>();
        address.put("address",mStoreAddress);
        address.put("mobile",mStoreMobile);
        address.put("truename",mStoreTurename);
        if (mDatas!=null)
        {
            String note = mEdtNote.getText()
                                  .toString()
                                  .trim();
            mDatas.address = address;
            mDatas.note = NetUtil.isStringNull(note);
            double price = DensityUtils.parseDouble(mTvPayPrice.getText().toString().trim());
            double needpay = DensityUtils.parseDouble(mDatas.needpay);
            if (price>=needpay) {
                mDatas.usemoney = "0";
            }else {
                mDatas.usemoney = "1";
            }
            mPresenter.createOrder(this,new Gson().toJson(mDatas));
        }
    }

    @Override
    public void showCreateResult(CreateOrderModel data) {
        if (data!=null) {
            CartCountManage.newInstance().refresh(Integer.parseInt(data.cartcount));
            double price = DensityUtils.parseDouble(mTvPayPrice.getText().toString().trim());
            double needpay = DensityUtils.parseDouble(mDatas.needpay);
            //使用了余额，重新保存下
            if (price<needpay) {
                //使用
                double usemoney = needpay-price;
                double money = DoubleMath.sub(mStoreMoney,usemoney);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_MONEY,String.valueOf(money));
            }
            if (price==0) {
                StackManager.getManagerStack().popAllActivityExceptOne();
                OrderDetailActivity.startActivity(this,1,data.ordernum);
            }else {
                StackManager.getManagerStack().popAllActivityExceptOne();
                PayActivity.startActivity(this,mDatas.needpay,data.ordernum);
            }
        }
    }

    @Override
    public void resultCartNum(MyOrderNumModel data) {
        if (data!=null)
        {
            // 余额
            mStoreMoney = data.money;
        }
        else
        {
            mStoreMoney = 0;
        }
        mNeedpay = DensityUtils.parseDouble(mDatas.needpay);
        if (mStoreMoney> mNeedpay){
            mTvYuePrice.setText("-"+ mNeedpay);
        }else {
            mTvYuePrice.setText("-"+mStoreMoney);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StackManager.getManagerStack().popActivity(this);
    }
}
