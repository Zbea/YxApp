package com.yx.Pharmacy.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.barteksc.pdfviewer.util.MathUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ShopCartAdapter;
import com.yx.Pharmacy.adapter.ShopCartCouponAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ChooseStoreDialog;
import com.yx.Pharmacy.manage.StoreManage;
import com.yx.Pharmacy.model.CreateOrderIntentModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.ShopCartPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.DoubleMath;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.util.StackManager;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IShopCartView;
import com.yx.Pharmacy.widget.LoadingLayout;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yx.Pharmacy.activity.LoginActivity.START_LOGIN_RESULT;


/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   ProductCartActivity
 *  @创建者:   CC
 *  @创建时间:  2018/8/16 0:58
 *  @描述：    TODO
 */

public class ProductCartActivity
        extends BaseActivity implements IShopCartView {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_notice)
    TextView mTvNotice;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.ll_checkall)
    LinearLayout mLlCheckall;
    @BindView(R.id.cb_checkall)
    CheckBox mCbCheckall;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    @BindView(R.id.rv_shop_cart)
    RecyclerView mRvShopCart;
    @BindView(R.id.loadinglayout)
    LoadingLayout mLoadingLayout;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestingScrollview;
    private ShopCartPresenter mPresenter;
    private ShopCartAdapter mAdapter;
    private TextView mTvPrice;
    private TextView mTvCouponPrice;
    private TextView mTvOrderPrice;
    private LinearLayout mLlSelectCoupon;
    private TextView mTvCouponSize;
    private TextView mTvMoney;
    private TextView mTvOverallCoupon;
    private CheckBox cbCoupon;
    private CheckBox cbDiscount;
    private Double mAllPrice;
    private Double mSubCouponPrice;
    private Double mAllCouponPrice;
    private ShopCartModel mResultBean;
    private double mSubCouponSpecialPrice;
    private double mAllSubPrice;
    private double mMoney;
    private double mDiscountMoney;
    private double mCouponMoney;
    private String mDiscount;
    private ArrayList<OrderModel.Goods> mOrderGoods;
    private HashMap<String, Object> mProducts;
    private String mCouponId;
    private int mCount;
    private Dialog mCouponDialog;
    private ShopCartCouponAdapter mCouponListAdapter;
    private List<ShopCartModel.CouponListBean> mCouponList;
    private int mCouponPosition;
    private String mCreditString;
    private ShopCartModel.CouponListBean mOrderCoupon;//全场优惠券
    private int screenHeight;
    private boolean mIsEdit = false;
    private String mGoodsid;
    private int mNotifyPosition;//操作的position
    private ChooseStoreDialog mChooseStoreDialog;
    private SelectStoreUtil mSelectStoreUtil;
    private HashMap<String, ArrayList<String>> mDeleteMap;
    private int mDeleteCartPosition;
    private int mDeletePosition;
    private ShopCartModel.CouponListBean mUserSelectCoupon;
    private int mAmountEdit;
    private int mAmountDelete;
    private String mActivityid;
    private double mSelectAllCouponPrice;
    private int checkType = 3;//选择优惠券还是折扣(为3时自动选择最佳方案)
    private List<ShopCartModel.ShopCartListBean> lists=new ArrayList<>();
    private EditText etAmount;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ProductCartActivity.class);
        context.startActivity(intent);
    }

    private Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0)
            {
                checkType = 3;
                etAmount=null;
                if (TextUtils.isEmpty(mResultBean.notice)) {
                    mTvNotice.setVisibility(View.GONE);
                } else {
                    mTvNotice.setVisibility(View.VISIBLE);
                    mTvNotice.setText(mResultBean.notice);
                }
                mCbCheckall.setChecked(false);
                List<ShopCartModel.ShopCartListBean> shopCartList = mResultBean.shopCartList;
                if (shopCartList != null && shopCartList.size() > 0) {
                    Iterator<ShopCartModel.ShopCartListBean> iterators = shopCartList.iterator();
                    while (iterators.hasNext()) {
                        if (iterators.next().goods.size() == 0) {
                            iterators.remove();
                        }
                    }
                    if (shopCartList.size() > 0) {
                        mAdapter.getData().clear();
                        mAdapter.setRefresh();
                        lists=shopCartList;
                        mAdapter.setNewData(lists);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mLoadingLayout.setStatus(LoadingLayout.Success);
                            }
                        },300);
                    } else {
                        mLoadingLayout.setStatus(LoadingLayout.Empty);
                    }
                } else {
                    mLoadingLayout.setStatus(LoadingLayout.Empty);
                }
                if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);

                mTvBuy.setEnabled(true);
                addUpPrice();
            }
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_cart;
    }

    @Override
    protected void init() {
        StackManager.getManagerStack().pushActivity(this);
        mPresenter = new ShopCartPresenter(this);

        mTvTitle.setText("购物车");
        mTvMore.setText("删除");
        mTvMore.setVisibility(View.VISIBLE);
        mLoadingLayout.setStatus(LoadingLayout.Loading);
        mLoadingLayout.setErrorText("加载失败~");
        mLoadingLayout.setEmptyText("什么都没有~");
        mLoadingLayout.setEmptyReloadButtonText("去首页看看");
        mLoadingLayout.setEmptyImage(R.drawable.icon_shopcar_empty);
        mLoadingLayout.setErrorImage(R.drawable.icon_shopcar_empty);
        initRecycler();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StackManager.getManagerStack().popActivity(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        //软键盘监听
        screenHeight = getWindow().getDecorView().getHeight() / 3;
        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //获取View可见区域的bottom
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int boot = bottom - rect.bottom - 200;// 200阔值  兼容双下巴
                if (bottom != 0 && oldBottom != 0 && boot <= 0) {
                    //                    getShortToastByString("隐藏");
                    if (mIsEdit) {
                        mIsEdit = false;
                        List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
                        for (ShopCartModel.ShopCartListBean datum : data) {
                            for (ShopCartModel.GoodsBean good : datum.goods) {
                                if (TextUtils.equals(good.goodsid, mGoodsid)) {
                                    if (mAmountEdit != 0) {
                                        mAmountEdit = mAmountEdit == 0 ? 1 : mAmountEdit;
                                        double i = (double) mAmountEdit / DensityUtils.parseDouble(good.addmum);
                                        if (i % 1 != 0) {
                                            NetUtil.getShortToastByString("输入商品的数量必须是起购量的倍数");
                                            mAmountEdit = (int) (DensityUtils.parseInt(good.addmum) * (int) i);
                                            good.cartcount = String.valueOf(mAmountEdit);
                                        }
                                    } else {
                                        mAmountEdit = mAmountEdit == 0 ? 1 : mAmountEdit;
                                        double i = (double) mAmountEdit / DensityUtils.parseDouble(good.addmum);
                                        mAmountEdit = (int) (DensityUtils.parseInt(good.addmum) * (int) i);
                                        good.cartcount = String.valueOf(mAmountEdit);
                                    }
                                    mPresenter.updateShopCart(ProductCartActivity.this, mActivityid, mGoodsid, mAmountEdit);
                                }
                            }
                        }
                        selectCoupon(mGoodsid);
                        addUpPrice();
                    }
                } else {
                    //                    getShortToastByString("弹出");
                }
            }
        });

    }

    private void initData() {
        checkType = 3;
        setCbView();
        mTvBuy.setEnabled(false);
        mPresenter.loadShopCart(this);
    }


    private void initRecycler() {
        mRvShopCart.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopCartAdapter(R.layout.item_shop_cart);
        ((DefaultItemAnimator) mRvShopCart.getItemAnimator()).setSupportsChangeAnimations(false);
        mRvShopCart.setAdapter(mAdapter);
        mRvShopCart.setNestedScrollingEnabled(false);
        mRvShopCart.setHasFixedSize(false);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCartModel.ShopCartListBean shopCartListBean = mAdapter.getData().get(position);

                mCouponList = shopCartListBean.couponList;
                mCouponPosition = position;

                switch (view.getId()) {
                    case R.id.tv_coupon:// 专区优惠券
                        showCouponDialog(1);
                        break;
                    case R.id.tv_clear:// 清空失效商品
                        if (mResultBean != null) {
                            mDeleteMap = new HashMap<>();

                            ArrayList<String> arrayList = new ArrayList<>();
                            for (ShopCartModel.GoodsBean good : mResultBean.shopCartList.get(position).goods) {
                                String goodsid = good.goodsid;
                                arrayList.add(goodsid);
                            }
                            if (arrayList.size() > 0) {
                                mDeleteMap.put(shopCartListBean.activityid, arrayList);
                            }

                            if (mDeleteMap.size() > 0) {
                                mPresenter.deleteShopCart(ProductCartActivity.this, mDeleteMap);
                            }
                        }
                        break;
                }
            }
        });

        mAdapter.setOnClickShopCartListener(new ShopCartAdapter.OnClickShopCartListener() {
            @Override
            public void modifySelect(int position) {
                // 修改选项
                boolean isAllSelect = true;
                List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
                for (ShopCartModel.ShopCartListBean datum : data) {
                    for (ShopCartModel.GoodsBean good : datum.goods) {
                        //排除缺货和时效
                        if (!TextUtils.equals(good.quehuo, "true") && !TextUtils.equals(good.isvalid, "true")) {
                            if (!good.isSelect) {//判断是否全选
                                isAllSelect = false;
                                break;
                            }
                        }
                    }
                }

                mCbCheckall.setChecked(isAllSelect);
                mNotifyPosition = position;
                selectCoupon("0");
                // 计算价格
                addUpPrice();
            }

            @Override
            public void removeShop(BaseQuickAdapter adapter, int cartposition, int position, String activityid) {
                // 删除商品
                mDeleteCartPosition = cartposition;
                mDeletePosition = position;
                ShopCartModel.GoodsBean bean = (ShopCartModel.GoodsBean) adapter.getData().get(position);
                String goodsid = bean.goodsid;
                mAmountDelete = Integer.parseInt(bean.cartcount);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(goodsid);
                HashMap<String, ArrayList<String>> urlMap = new HashMap<>();
                urlMap.put(activityid, arrayList);
                mPresenter.deleteShopCart(ProductCartActivity.this, urlMap);
            }

            @Override
            public void aumontChange(View view, int amount, String goodsid, String activityid, boolean isEdit, int position, int addnum) {
                etAmount= (EditText) view;
                // 修改商品数量
                mGoodsid = goodsid;
                mIsEdit = isEdit;
                mAmountEdit = amount;
                mNotifyPosition = position;
                mActivityid = activityid;
                if (!mIsEdit) {
                    List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
                    for (ShopCartModel.ShopCartListBean datum : data) {
                        for (ShopCartModel.GoodsBean good : datum.goods) {
                            if (TextUtils.equals(good.goodsid, mGoodsid)) {
                                if (mAmountEdit != 0) {
                                    mAmountEdit = mAmountEdit == 0 ? 1 : mAmountEdit;
                                    double i = (double) mAmountEdit / DensityUtils.parseDouble(good.addmum);
                                    if (i % 1 != 0) {
                                        NetUtil.getShortToastByString("输入商品的数量必须是起购量的倍数");
                                        mAmountEdit = (int) (DensityUtils.parseInt(good.addmum) * (int) i);
                                        good.cartcount = String.valueOf(mAmountEdit);
                                    }
                                } else {
                                    mAmountEdit = mAmountEdit == 0 ? 1 : mAmountEdit;
                                    double i = (double) mAmountEdit / DensityUtils.parseDouble(good.addmum);
                                    mAmountEdit = (int) (DensityUtils.parseInt(good.addmum) * (int) i);
                                    good.cartcount = String.valueOf(mAmountEdit);
                                }
                                mPresenter.updateShopCart(ProductCartActivity.this, mActivityid, mGoodsid, mAmountEdit);
                            }
                        }
                    }
                    selectCoupon(goodsid);
                    addUpPrice();
                }
            }

            @Override
            public void toDetail(BaseQuickAdapter adapter, String goodsid) {
                ProductDetailActivity.startActivity(mContext, goodsid);
            }

            @Override
            public void singleCoupon(BaseQuickAdapter adapter, int cartposition, int position, String activityid) {
                // 单品优惠券
                mCouponList = mAdapter.getData().get(cartposition).goods.get(position).couponList;
                if (mCouponList != null && mCouponList.size() > 0) {
                    showCouponDialog(1);
                } else {
                    getShortToastByString("无可用优惠券");
                }
            }
        });
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                etAmount=null;
                initData();
            }
        });

        View bottomView = LayoutInflater.from(this).inflate(R.layout.include_shop_cart_bottom, null);
        mAdapter.addFooterView(bottomView);
        mTvPrice = bottomView.findViewById(R.id.tv_price);
        mTvCouponPrice = bottomView.findViewById(R.id.tv_coupon_price);
        mTvOrderPrice = bottomView.findViewById(R.id.tv_order_price);
//        mLlSelectCoupon = bottomView.findViewById(R.id.ll_select_coupon);
        mTvCouponSize = bottomView.findViewById(R.id.tv_coupon_size);
        mTvMoney = bottomView.findViewById(R.id.tv_money);
        cbCoupon = bottomView.findViewById(R.id.cb_coupon);
        cbCoupon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkType = 1;
                    cbDiscount.setChecked(false);
                } else {
                    if (!cbDiscount.isChecked())
                        checkType = checkType == 3 ? 3 : 0;
                }
                addUpPrice();
            }
        });
        cbDiscount = bottomView.findViewById(R.id.cb_discount);
        cbDiscount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkType = 2;
                    cbCoupon.setChecked(false);
                } else {
                    if (!cbCoupon.isChecked())
                        checkType = checkType == 3 ? 3 : 0;
                    ;
                }
                addUpPrice();
            }
        });
        mTvOverallCoupon = bottomView.findViewById(R.id.tv_overall_coupon);

        mLoadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                if (mLoadingLayout.getStatus() == LoadingLayout.Empty) {
                    MainActivity.startActivity(ProductCartActivity.this, 1);
                } else {
                    if (TextUtils.isEmpty(NetUtil.getToken())) {
                        LoginActivity.startActivity(ProductCartActivity.this, 1);
                    }
                    else if (StoreManage.newInstance().getStore().storeStutus)
                    {
                        initData();
                    }
                    else {
                        if (mSelectStoreUtil != null) {
                            mSelectStoreUtil.loadMyShop(ProductCartActivity.this, true);
                        } else {
                            mSelectStoreUtil = new SelectStoreUtil(ProductCartActivity.this, new SelectStoreUtil.OnSelectStoreListener() {
                                @Override
                                public void onSelect(MyShopModel myShopModel) {
                                    initData();
                                }
                            });
                        }
                    }
                }
            }
        });


        mTvCouponSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mResultBean != null) {
                    mCouponList = mResultBean.couponList;
                    if (mCouponList != null && mCouponList.size() > 0) {
                        for (ShopCartModel.CouponListBean couponListBean : mCouponList) {
                            double parseDouble = DensityUtils.parseDouble(couponListBean.couponlimit);
                            if (mSelectAllCouponPrice != 0) {
                                if (mSelectAllCouponPrice >= parseDouble) {
                                    couponListBean.couponEnable = true;
                                }
                            } else {
                                if (mSubCouponPrice >= parseDouble) {
                                    couponListBean.couponEnable = true;
                                }
                            }
                        }
                        showCouponDialog(2);
                    } else {
                        getShortToastByString("无可用优惠券");
                    }
                }
            }
        });
        mTvNotice.setSelected(true);
    }

    // 自动选择优惠券
    private void selectCoupon(String goodsid) {
        List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
        for (ShopCartModel.ShopCartListBean datum : data) {
            // 专区商品价格
            Double goodsPrice = 0.0;
            if (datum.goods != null) {
                for (ShopCartModel.GoodsBean good : datum.goods) {
                    int limitnum = DensityUtils.parseInt(good.limitnum);// 特价数量
                    int cartcount = DensityUtils.parseInt(good.cartcount);//购物车数量
                    double price = DensityUtils.parseDouble(good.usefulprice);// 当前售价
                    double oprice = DensityUtils.parseDouble(good.oprice);// 原价
                    if (!TextUtils.equals(goodsid, "0")) {
                        if (TextUtils.equals(good.goodsid, goodsid) &&
                                TextUtils.equals(datum.type, mAdapter.getData().get(mNotifyPosition).type)) {
                            good.isSelect = true;
                        }
                    }
                    /*//购物车数量是否大于特价数量
                    if (cartcount>limitnum){
                        double tempprice = (price * limitnum + oprice * (cartcount - limitnum))/cartcount;
                        good.price = DensityUtils.doubleToString(tempprice);
                    }else {
                        good.price = DensityUtils.doubleToString(price);
                    }*/
                    if (good.isSelect) {
                        goodsPrice = price * cartcount + goodsPrice;
                    }

                    if (TextUtils.equals(datum.type, "4")) {
                        boolean isfrist=true;
                        if (good.couponList != null) {
                            for (ShopCartModel.CouponListBean couponListBean : good.couponList) {
                                int couponstate = DensityUtils.parseInt(couponListBean.couponstate);
                                if (couponstate != 0) {
                                    if (good.isSelect) {
                                        if (DensityUtils.parseDouble(couponListBean.couponlimit) <= price * cartcount) {
                                            couponListBean.couponEnable = true;
                                            couponListBean.isSelectCoupon = isfrist?true:false;
                                            isfrist=false;
                                        } else {
                                            couponListBean.couponEnable = false;
                                            couponListBean.isSelectCoupon = false;
                                        }
                                    } else {
                                        couponListBean.couponEnable = DensityUtils.parseDouble(couponListBean.couponlimit) <= price * cartcount;
                                        couponListBean.isSelectCoupon = false;
                                    }
                                }
                            }
                        }
                    }
                }
                //自动选中优惠券
                if (datum.couponList != null) {
                    boolean isfrist=true;
                    for (ShopCartModel.CouponListBean couponListBean : datum.couponList) {
                        int couponstate = DensityUtils.parseInt(couponListBean.couponstate);
                        if (couponstate != 0) {
                            if (DensityUtils.parseDouble(couponListBean.couponlimit) <= goodsPrice) {
                                couponListBean.couponEnable = true;
                                couponListBean.isSelectCoupon = isfrist?true:false;
                                isfrist=false;
                            } else {
                                couponListBean.couponEnable = false;
                                couponListBean.isSelectCoupon = false;
                            }
                        }
                    }
                }
            }
        }
        mAdapter.notifyItemChanged(mNotifyPosition);
    }


    // 计算价格（优惠券、折扣二选一或者都不选，不能同时选；秒杀控销商品不参与任何优惠）
    private void addUpPrice() {
        // 总价格
        mAllPrice = 0.0;
        // 特殊商品总价格
        double mAllSpecial = 0.0;
        // 优惠价格（含特殊商品）
        mSubCouponPrice = 0.0;
        //不含特殊商品价格
        mAllSubPrice = 0.0;
        //不含特殊商品优惠价格
        mSubCouponSpecialPrice = 0.0;
        // 不参与全场折扣的专区优惠券总优惠金额
        double mSpecialCouponPrice = 0.0;
        // 不参与全场折扣的专区优惠券总优惠金额
        double mSingeCouponPrice = 0.0;
        // 总优惠价格
        mAllCouponPrice = 0.0;
        //支付显示金额
        mMoney = 0.0;
        //折扣后金额
        mDiscountMoney = 0.0;
        //全场优惠后金额
        mCouponMoney = 0.0;

        boolean isCoupon = false;
        boolean isDiscount = false;

        if (!cbCoupon.isChecked() && !cbDiscount.isChecked()) {
            checkType = checkType == 0 ? 0 : 3;
        }
        List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
        if (data.size() > 0) {
            for (ShopCartModel.ShopCartListBean datum : data) {
                List<ShopCartModel.CouponListBean> couponList = datum.couponList;
                List<ShopCartModel.GoodsBean> goods = datum.goods;
                // 专区商品价格
                Double goodsPrice = 0.0;
                if (goods != null) {
                    for (ShopCartModel.GoodsBean good : goods) {
                        int limitnum = DensityUtils.parseInt(good.limitnum);// 特价数量
                        double cartcount = DensityUtils.parseInt(good.cartcount);//购物车数量
                        double price = DensityUtils.parseDouble(good.usefulprice);// 当前售价
                        double oprice = DensityUtils.parseDouble(good.oprice);// 原价

                        if (good.isSelect) {
                            goodsPrice =DoubleMath.mul(price , cartcount)+ goodsPrice;
                            List<ShopCartModel.GoodsBean.GiftListBean> listBeans = new ArrayList<>();
                            List<Integer> limits = new ArrayList<>();
                            int carcount = DensityUtils.parseInt(good.cartcount);
                            if (good.giftList != null) {
                                for (ShopCartModel.GoodsBean.GiftListBean giftListBean : good.giftList) {
                                    if (giftListBean.limit != null) {
                                        int limit = DensityUtils.parseInt(giftListBean.limit);
                                        int give = DensityUtils.parseInt(giftListBean.give);
                                        if (carcount >= limit) {
                                            int div = carcount / limit;
                                            int count = div * give;
                                            if (count > 0 && giftListBean.giftInfo != null) {
                                                limits.add(limit);
                                                listBeans.add(giftListBean);
                                            }
                                        }
                                    }
                                }
                                if (limits != null && limits.size() > 0) {
                                    int limit = Collections.max(limits);
                                    for (ShopCartModel.GoodsBean.GiftListBean giftListBean : listBeans) {
                                        int limit3 = DensityUtils.parseInt(giftListBean.limit);
                                        int give = DensityUtils.parseInt(giftListBean.give);
                                        if (limit == limit3) {
                                            int div = carcount / limit;
                                            int count = div * give;
                                            goodsPrice = goodsPrice + 0.01 * count;
                                        }
                                    }
                                }
                            }

                        }

                        // --------- 单品
                        if (TextUtils.equals(datum.type, "4")) {
                            if (good.couponList != null) {
                                for (ShopCartModel.CouponListBean couponListBean : good.couponList) {
                                    if (couponListBean.isSelectCoupon) {
                                        int couponmodel = DensityUtils.parseInt(couponListBean.couponmodel);
                                        Double couponPrice = 0.0;
                                        if (couponmodel == 2) {
                                            double coupondiscount = DensityUtils.parseDouble(couponListBean.coupondiscount);
                                            couponPrice = DoubleMath.sub(goodsPrice, DoubleMath.mul(goodsPrice, coupondiscount));
                                        } else {
                                            couponPrice = DensityUtils.parseDouble(couponListBean.discountprice);
                                        }

                                        if (!TextUtils.isEmpty(datum.alldiscount) && TextUtils.equals(datum.alldiscount, "0")) {//判断该单品优惠券是否可以参与全场折扣
                                            mSpecialCouponPrice = DoubleMath.add(mSpecialCouponPrice, couponPrice);
                                        }
                                        else
                                        {
                                            mSingeCouponPrice = DoubleMath.add(mSingeCouponPrice, couponPrice);
                                        }
                                        mAllCouponPrice = DoubleMath.add(mAllCouponPrice, couponPrice);
                                    }
                                }
                            }
                        }
                    }
                }
                mAllPrice = DoubleMath.add(mAllPrice, goodsPrice);
                //是否参与全场折扣
                if (!TextUtils.isEmpty(datum.alldiscount) && TextUtils.equals(datum.alldiscount, "0")) {
                    mAllSpecial = DoubleMath.add(mAllSpecial, goodsPrice);
                } else {
                    mAllSubPrice = DoubleMath.add(mAllSubPrice, goodsPrice);
                }

                //每个商品的优惠券价格
                Double couponPrice = 0.0;

                //专区优惠券
                if (couponList != null) {
                    for (ShopCartModel.CouponListBean couponListBean : couponList) {
                        if (DensityUtils.parseDouble(couponListBean.couponlimit) <= goodsPrice) {
                            couponListBean.couponEnable = true;
                            if (couponListBean.isSelectCoupon) {
                                int couponmodel = DensityUtils.parseInt(couponListBean.couponmodel);
                                if (couponmodel == 2) {
                                    double coupondiscount = DensityUtils.parseDouble(couponListBean.coupondiscount);
                                    couponPrice = DoubleMath.sub(goodsPrice, DoubleMath.mul(goodsPrice, coupondiscount));
                                } else {
                                    couponPrice = DensityUtils.parseDouble(couponListBean.discountprice);
                                }
                            }
                        } else {
                            couponListBean.couponEnable = false;
                            couponListBean.isSelectCoupon = false;
                        }
                    }
                }
                if (!TextUtils.isEmpty(datum.alldiscount) && TextUtils.equals(datum.alldiscount, "0")) {//判断该专区优惠券是否可以参与全场折扣
                    mSpecialCouponPrice = DoubleMath.add(mSpecialCouponPrice, couponPrice);
                }
                else
                {
                    mSingeCouponPrice = DoubleMath.add(mSingeCouponPrice, couponPrice);
                }
                mAllCouponPrice = DoubleMath.add(mAllCouponPrice, couponPrice);
                mSubCouponPrice = DoubleMath.sub(mAllPrice, mAllCouponPrice);
            }

            // 全场优惠券
            List<ShopCartModel.CouponListBean> couponListAll = mResultBean.couponList;
            if (couponListAll != null && couponListAll.size() > 0) {
                Collections.sort(couponListAll, new Comparator<ShopCartModel.CouponListBean>() {
                    @Override
                    public int compare(ShopCartModel.CouponListBean o1, ShopCartModel.CouponListBean o2) {
                        int i = (int) (DensityUtils.parseDouble(o1.couponlimit) - DensityUtils.parseDouble(o2.couponlimit));
                        return i;
                    }
                });

                for (int i = couponListAll.size() - 1; i >= 0; i--) {
                    ShopCartModel.CouponListBean bean = couponListAll.get(i);
                    double limit = DensityUtils.parseDouble(bean.couponlimit);
                    if (mUserSelectCoupon == null) {
                        Double sub = DoubleMath.sub(mAllSubPrice, mAllCouponPrice);
                        if (sub >= limit) {
                            bean.isSelectCoupon = true;
                            mOrderCoupon = bean;
                            mTvCouponSize.setText(bean.couponcontent);
                            cbCoupon.setClickable(true);
                            break;
                        } else {
                            bean.isSelectCoupon = false;
                            mOrderCoupon = null;
                            mTvCouponSize.setText(bean.couponcontent);
                            cbCoupon.setClickable(false);
                        }
                    } else {
                        mOrderCoupon = mUserSelectCoupon;
                    }
                }

                double mprice = DoubleMath.sub(mAllCouponPrice, mSpecialCouponPrice);
                // 全场优惠券价格计算
                mSubCouponSpecialPrice = DoubleMath.sub(mAllSubPrice, mprice);
                mSelectAllCouponPrice = mSubCouponSpecialPrice;
                if (mOrderCoupon != null) {
                    isCoupon = true;
                    mCouponMoney = mSelectAllCouponPrice;
                    int couponmodel = DensityUtils.parseInt(mOrderCoupon.couponmodel);
                    double orderCouponPrice = 0.00;
                    if (couponmodel == 2) {
                        double coupondiscount = DensityUtils.parseDouble(mOrderCoupon.coupondiscount);
                        orderCouponPrice = DoubleMath.sub(mSubCouponSpecialPrice, DoubleMath.mul(mSubCouponSpecialPrice, coupondiscount));
                    } else {
                        orderCouponPrice = DensityUtils.parseDouble(mOrderCoupon.discountprice);
                    }
                    mCouponMoney = DoubleMath.sub(mSubCouponSpecialPrice, orderCouponPrice);
                    mCouponMoney = DoubleMath.add(mCouponMoney, mAllSpecial);
                    mCouponMoney = DoubleMath.sub(mCouponMoney, mSpecialCouponPrice);
                } else {
                    isCoupon = false;
                }
            }

                mDiscount = "";
                double mprice = DoubleMath.sub(mAllCouponPrice, mSpecialCouponPrice);
                mSubCouponSpecialPrice = DoubleMath.sub(mAllSubPrice, mprice);
                // 全场折扣
                List<ShopCartModel.DiscountListBean> discountList = mResultBean.discountList;
                if (discountList != null) {
                    Collections.sort(discountList, new Comparator<ShopCartModel.DiscountListBean>() {
                        @Override
                        public int compare(ShopCartModel.DiscountListBean o1, ShopCartModel.DiscountListBean o2) {
                            int i = (int) (DensityUtils.parseDouble(o1.limit) - DensityUtils.parseDouble(o2.limit));
                            return i;
                        }
                    });
                    for (int i = discountList.size() - 1; i >= 0; i--) {
                        ShopCartModel.DiscountListBean bean = discountList.get(i);
                        double limit = DensityUtils.parseDouble(bean.limit);
                        if (mSubCouponSpecialPrice >= limit) {
                            isDiscount = true;
                            cbDiscount.setClickable(true);
                            mDiscount = bean.discount;
                            mTvOverallCoupon.setText("满" + bean.limit + "元,可享受" + bean.discount + "折优惠(不含控销商品)");
                            // 计算没有控销的商品打完折后，加上控销商品的价格
                            double discount = DensityUtils.parseDouble(mDiscount) / 10;
                            mDiscountMoney = DoubleMath.mul(mSubCouponSpecialPrice, discount);
                            mDiscountMoney = DoubleMath.add(mDiscountMoney, mAllSpecial);
                            mDiscountMoney = DoubleMath.sub(mDiscountMoney, mSpecialCouponPrice);
                            break;
                        } else {
                            isDiscount = false;
                            cbDiscount.setClickable(false);
                            mDiscountMoney = 0.00;
                            mTvOverallCoupon.setText("订单金额低于全场优惠价最低金额");
                        }
                    }
                }
        }

        L.i("mAllPrice:" + mAllPrice);
        L.i("mAllSubPrice:" + mAllSubPrice);
        L.i("mAllSpecial:" + mAllSpecial);
        L.i("mAllCouponPrice:" + mAllCouponPrice);
        L.i("mCouponMoney:" + mCouponMoney);
        L.i("mDiscountMoney:" + mDiscountMoney);
        L.i("mSpecialCouponPrice:" + mSpecialCouponPrice);
        L.i("mSingeCouponPrice:" + mSingeCouponPrice);
        L.i("checkType:" + checkType);
        L.i("isCoupon:" + isCoupon);
        L.i("isDiscount:" + isDiscount);
        // 优惠金额
        mTvCouponPrice.setText("-" + DensityUtils.doubleToString(mAllCouponPrice));
        // 原总金额
        mTvPrice.setText(DensityUtils.doubleToString(mAllPrice));
        // 实付总金额
        mTvOrderPrice.setText(DensityUtils.doubleToString(mSubCouponPrice));
        // 应付总额
        mTvMoney.setText(DensityUtils.doubleToString(mSubCouponPrice));


        //-----------全场优惠-------
        if (mResultBean != null && mResultBean.orderlimit != null) {
            double limit = DensityUtils.parseDouble(mResultBean.orderlimit);
            if (checkType == 3) {
//                if (isCoupon && isDiscount) {
//                    if (mCouponMoney >= mDiscountMoney) {
//                        if (mDiscountMoney >= limit) {
//                            cbDiscount.setChecked(true);
//                            checkType = 2;
//                        } else if (mCouponMoney >= limit && mDiscountMoney < limit) {
//                            cbCoupon.setChecked(true);
//                            checkType = 1;
//                        }
//                    } else {
//                        if (mCouponMoney >= limit) {
//                            cbCoupon.setChecked(true);
//                            checkType = 1;
//                        } else if (mDiscountMoney >= limit && mCouponMoney < limit) {
//                            cbDiscount.setChecked(true);
//                            checkType = 2;
//                        }
//                    }
//                } else if (isCoupon && !isDiscount) {
//                    if (mCouponMoney >= limit) {
//                        cbCoupon.setChecked(true);
//                        checkType = 1;
//                    }else {
//                        cbCoupon.setChecked(false);
//                        cbCoupon.setClickable(false);
//                    }
//                } else if (!isCoupon && isDiscount) {
//                    if (mDiscountMoney >= limit) {
//                        cbDiscount.setChecked(true);
//                        checkType = 2;
//                    }else {
//                        cbDiscount.setChecked(false);
//                        cbDiscount.setClickable(false);
//                    }
//                }

                if (isCoupon&&isDiscount)
                {
                    if (mCouponMoney >= mDiscountMoney) {
                        cbDiscount.setChecked(true);
                        checkType = 2;
                    }
                    else
                    {
                        cbCoupon.setChecked(true);
                        checkType = 1;
                    }
                }
                else if (isCoupon && !isDiscount)
                {
                     cbCoupon.setChecked(true);
                     checkType = 1;
                }
                else if (!isCoupon && isDiscount)
                {
                    cbDiscount.setChecked(true);
                    checkType = 2;
                }
                else
                {
                    setCbView();
                }

            }


            if (checkType == 1) {
                mMoney = mCouponMoney;
            } else if (checkType == 2) {
                mMoney = mDiscountMoney;
            } else {
                mMoney = mSubCouponPrice;
            }


            if (mMoney >= limit) {
                mTvBuy.setText("去结算");
                mTvBuy.setEnabled(true);

            } else {
                mTvBuy.setText("满" + DensityUtils.doubleTrans2(limit) + "起购");
                mTvBuy.setEnabled(false);
//                setCbView();
            }
        }
        mTvPayPrice.setText(DensityUtils.doubleToString(mMoney));
        checkAllCoupon();
    }

    private void setCbView() {
        cbCoupon.setChecked(false);
        cbCoupon.setClickable(false);
        cbDiscount.setChecked(false);
        cbDiscount.setClickable(false);
    }

    private void checkAllCoupon() {
        if (mResultBean != null) {
            boolean couponEnable = false;
            mCouponList = mResultBean.couponList;
            if (mCouponList != null && mCouponList.size() > 0) {
                for (ShopCartModel.CouponListBean couponListBean : mCouponList) {
                    if (couponListBean.couponEnable) {
                        couponEnable = true;
                    }
                }
                if (couponEnable) {
                    if (TextUtils.equals(mTvCouponSize.getText().toString().trim(), "无可用优惠券")) {
                        mTvCouponSize.setText("");
                    }
                }
            } else {
                mTvCouponSize.setText("无可用优惠券");
            }
        }
    }


    @OnClick({R.id.tv_more,
            R.id.rl_back,
            R.id.ll_checkall,
            R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_more:
                if (mResultBean != null) {
                    mDeleteMap = new HashMap<>();
                    for (ShopCartModel.ShopCartListBean shopCartListBean : mResultBean.shopCartList) {
                        ArrayList<String> arrayList = new ArrayList<>();
                        for (ShopCartModel.GoodsBean good : shopCartListBean.goods) {
                            String goodsid = good.goodsid;
                            if (good.isSelect) {
                                arrayList.add(goodsid);
                            }
                        }
                        if (arrayList.size() > 0) {
                            mDeleteMap.put(shopCartListBean.activityid, arrayList);
                        }
                    }
                    if (mDeleteMap.size() > 0) {
                        mPresenter.deleteShopCart(this, mDeleteMap);
                    }
                }
                break;
            case R.id.ll_checkall:
                mCbCheckall.setChecked(!mCbCheckall.isChecked());
                mAdapter.setCheckAll(mCbCheckall.isChecked());
                selectCoupon("0");
                if (!mCbCheckall.isChecked()) {
                    mOrderCoupon = null;
                    mUserSelectCoupon = null;
                    mTvCouponSize.setText("");
                    checkType = 3;
                    setCbView();
                }
                addUpPrice();
                break;
            case R.id.tv_buy:
                checkMoney();
                break;
        }
    }

    private ArrayList<OrderModel.Goods> gifts;

    private void checkMoney() {
        mCount = 0;
        List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
        mProducts = new HashMap<>();
        mOrderGoods = new ArrayList<>();
        gifts = new ArrayList<>();
        for (ShopCartModel.ShopCartListBean datum : data) {
            HashMap<String, Object> goodsMap = new HashMap<>();
            List<ShopCartModel.GoodsBean> goods = datum.goods;
            ArrayList<HashMap<String, Object>> goodList = new ArrayList<>();
            if (goods != null) {
                for (ShopCartModel.GoodsBean good : goods) {
                    if (good.isSelect) {
                        HashMap<String, Object> detailMap = new HashMap<>();
                        detailMap.put("pid", good.goodsid);
                        detailMap.put("price", good.usefulprice);
//                        detailMap.put("couponid", "");
                        if (good.couponList != null) {
                            for (ShopCartModel.CouponListBean couponListBean : good.couponList) {
                                if (couponListBean.isSelectCoupon) {
                                    detailMap.put("couponid", couponListBean.couponid);
                                    break;
                                }
                                else
                                {
                                    detailMap.put("couponid", "");
                                }
                            }
                        }
                        detailMap.put("count",good.cartcount);
                        mCount = mCount + DensityUtils.parseInt(good.cartcount);
                        goodList.add(detailMap);
                        OrderModel.Goods ordergood = new OrderModel.Goods();
                        ordergood.count = DensityUtils.parseInt(good.cartcount);
                        ordergood.thumb = good.thumb;
                        ordergood.title = good.title;
                        ordergood.scqy = good.scqy;
                        ordergood.gg = good.gg;
                        ordergood.presale = good.presale;
                        ordergood.endtime = good.endtimes;
                        ordergood.starttime = good.starttime;
                        ordergood.price = good.price;
                        ordergood.oprice = good.oprice;
                        ordergood.type = DensityUtils.parseInt(datum.type);
                        mOrderGoods.add(ordergood);


                        List<ShopCartModel.GoodsBean.GiftListBean> listBeans = new ArrayList<>();
                        List<Integer> limits = new ArrayList<>();
                        int carcount = DensityUtils.parseInt(good.cartcount);
                        if (good.giftList != null) {
                            for (ShopCartModel.GoodsBean.GiftListBean giftListBean : good.giftList) {
                                if (giftListBean.limit != null) {
                                    int limit = DensityUtils.parseInt(giftListBean.limit);
                                    int give = DensityUtils.parseInt(giftListBean.give);
                                    if (carcount >= limit) {
                                        int div = carcount / limit;
                                        int count = div * give;
                                        if (count > 0 && giftListBean.giftInfo != null) {
                                            limits.add(limit);
                                            listBeans.add(giftListBean);
                                        }
                                    }
                                }
                            }
                            if (limits != null && limits.size() > 0) {
                                int limit = Collections.max(limits);
                                for (ShopCartModel.GoodsBean.GiftListBean giftListBean : listBeans) {
                                    int limit3 = DensityUtils.parseInt(giftListBean.limit);
                                    int give = DensityUtils.parseInt(giftListBean.give);
                                    if (limit == limit3) {
                                        OrderModel.Goods gift = new OrderModel.Goods();
                                        gift.title = giftListBean.giftInfo.goodsname;
                                        gift.gg = giftListBean.giftInfo.goodsgg;
                                        gift.thumb = giftListBean.giftInfo.goodsthumb;
                                        int div = carcount / limit;
                                        int count = div * give;
                                        gift.count = count;
                                        gifts.add(gift);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (goodList.size() != 0) {
                goodsMap.put("goodsList", goodList);
                goodsMap.put("couponid", "");
                if (datum.couponList != null) {
                    for (ShopCartModel.CouponListBean couponListBean : datum.couponList) {
                        if (couponListBean.isSelectCoupon) {
                            goodsMap.put("couponid", couponListBean.couponid);
                        }
                    }
                }
                goodsMap.put("activityid", datum.activityid);

                mProducts.put(datum.activityid, goodsMap);
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("couponid", NetUtil.isStringNull(mCouponId));
        // 全场优惠券
        if (mResultBean != null) {
            if (mResultBean.couponList != null) {
                for (ShopCartModel.CouponListBean couponListBean : mResultBean.couponList) {
                    if (couponListBean.isSelectCoupon) {
                        mCouponId = couponListBean.couponid;
                        map.put("couponid", NetUtil.isStringNull(mCouponId));
                    }
                }
            }
        }
        if (checkType == 1) {
            map.put("discount", "");
        } else if (checkType == 2) {
            map.put("couponid", "");
            map.put("discount", NetUtil.isStringNull(mDiscount));
        } else {
            map.put("couponid", "");
            map.put("discount", "");
        }
        map.put("needpay", mMoney);
        map.put("product", mProducts);
        if (mCount != 0) {
            mPresenter.checkMoney((BaseActivity) this, new Gson().toJson(map));
        } else {
            getShortToastByString("请选择结算的商品");
        }
    }

    @Override
    public void showShopCartList(ShopCartModel data) {
        mResultBean = data;
        if (data!=null)
        {
            mHandler.sendEmptyMessage(0);
        }
    }

    @Override
    public void deleteSuccess() {
        if (mDeleteMap != null && mDeleteMap.size() > 0) {
            initData();
//            mLoadingLayout.setStatus(LoadingLayout.Empty);
        } else {
            L.i("mDeleteCartPosition:"+mDeleteCartPosition);
            L.i("mDeletePosition:"+mDeletePosition);
            ShopCartModel.ShopCartListBean shopCartListBean = mAdapter.getData().get(mDeleteCartPosition);
            shopCartListBean.goods.remove(mDeletePosition);
            mAdapter.setDelete(mDeleteCartPosition,mDeletePosition);
//            if (shopCartListBean.goods.size()==0)
//            {
//                mAdapter.getData().remove(mDeleteCartPosition);
//            }
            mAdapter.notifyDataSetChanged();
            if (mAdapter.getData().size() == 0) {
                mLoadingLayout.setStatus(LoadingLayout.Empty);
            }
            addUpPrice();
        }

    }

    @Override
    public void showCheckResult(BasisBean<String> response) {
        if (TextUtils.equals(response.getCode(), "200")) {

            CreateOrderIntentModel data = null;
            if (checkType == 1) {
                data = new CreateOrderIntentModel(mProducts, NetUtil.isStringNull(mCouponId), DensityUtils.doubleToString(mMoney), String.valueOf(mCount), "");
            } else if (checkType == 2) {
                data = new CreateOrderIntentModel(mProducts, "", DensityUtils.doubleToString(mMoney), String.valueOf(mCount), NetUtil.isStringNull(mDiscount));
            } else {
                data = new CreateOrderIntentModel(mProducts, "", DensityUtils.doubleToString(mMoney), String.valueOf(mCount), "");
            }

            OrderCreateActivity.startActivity(this, mOrderGoods, data, mAllPrice, mMoney, gifts);
        }
    }

    @Override
    public void showFailView() {
        if (TextUtils.isEmpty(NetUtil.getToken())) {
            mLoadingLayout.setErrorReloadButtonText("去登录");
        } else {
            if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                mLoadingLayout.setErrorReloadButtonText("选择门店");
            } else {
                mLoadingLayout.setErrorReloadButtonText("去添加门店");
            }
        }
        mLoadingLayout.setStatus(LoadingLayout.Error);
    }

    @Override
    public void showSaveResult(String couponid) {
        Double price = 0.00;//选中商品的价格
        for (ShopCartModel.ShopCartListBean datum : mAdapter.getData()) {
            if (TextUtils.equals(mCouponList.get(0).couponscene, datum.activityname)) {
                for (ShopCartModel.GoodsBean good : datum.goods) {
                    if (good.isSelect) {
                        price = price + DensityUtils.parseDouble(good.usefulprice);
                    }
                }
            }
        }

        for (ShopCartModel.CouponListBean listBean : mCouponList) {
            if (TextUtils.equals(couponid, listBean.couponid)) {
                listBean.couponstate = "1";
            }
            if (price != 0 && price >= DensityUtils.parseDouble(listBean.couponlimit)) {
                listBean.couponEnable = true;
            }
        }

        if (mCouponListAdapter != null) {
            mCouponListAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 优惠券弹窗
     *
     * @param i
     */
    public void showCouponDialog(int i) {
        if (mCouponDialog == null) {
            mCouponDialog = new Dialog(this, R.style.SrcbDialog);
            View view = getLayoutInflater().inflate(R.layout.dialog_shopcart_coupon, null);
            View.OnClickListener onClickListener = v -> {
                switch (v.getId()) {
                    case R.id.iv_cancel:
                        mCouponDialog.dismiss();
                        break;
                    default:
                        break;
                }
            };
            view.findViewById(R.id.iv_cancel).setOnClickListener(onClickListener);
            RecyclerView dialogCoupon = view.findViewById(R.id.rv_coupon);
            dialogCoupon.setLayoutManager(new LinearLayoutManager(this));
            mCouponListAdapter = new ShopCartCouponAdapter(R.layout.item_coupon_cart);
            dialogCoupon.setAdapter(mCouponListAdapter);
            mCouponListAdapter.setNewData(mCouponList);
            mCouponListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    ShopCartModel.CouponListBean couponListBean = mCouponListAdapter.getData()
                            .get(position);
                    for (ShopCartModel.CouponListBean listBean : mCouponList) {
                        listBean.isSelectCoupon = false;
                    }
                    int couponstate = DensityUtils.parseInt(couponListBean.couponstate);
                    if (couponstate == 0) {//未领取
                        mCreditString = couponListBean.credit;
                        mPresenter.saveCoupon(ProductCartActivity.this, couponListBean.couponid);
                    } else {//有的优惠券
                        if (i == 1) {
                            if (couponListBean.couponEnable) {
                                mCouponList.get(position).isSelectCoupon = true;
                                addUpPrice();
                                for (int i = 0; i < mAdapter.getData().size(); i++) {
                                    mAdapter.notifyItemChanged(i);
                                }
                            }
                        } else {
                            mTvCouponSize.setText(couponListBean.couponcontent);
                            mOrderCoupon = couponListBean;
                            mUserSelectCoupon = couponListBean;
                            mCouponList.get(position).isSelectCoupon = true;
                            addUpPrice();
                            for (int i = 0; i < mAdapter.getData().size(); i++) {
                                mAdapter.notifyItemChanged(i);
                            }
                        }
                        mCouponDialog.dismiss();
                    }
                }
            });
            Window win = mCouponDialog.getWindow();
            win.setWindowAnimations(R.style.mystyle);
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            // 设置弹出框的宽高
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = DensityUtils.dp2px(this, 400);
            // 设置弹出框的位置
            win.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            win.setAttributes(lp);
            mCouponDialog.setContentView(view);
            mCouponDialog.show();
        } else {
            mCouponDialog.show();
            if (mCouponListAdapter != null) {
                mCouponListAdapter.setNewData(mCouponList);
            }
        }

    }

    @Override
    public void updateResult() {
        if (etAmount!=null)
            etAmount.clearFocus();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == START_LOGIN_RESULT) {
            if (TextUtils.isEmpty(NetUtil.getToken())) {
                mLoadingLayout.setErrorReloadButtonText("去登录");
            } else {
                if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                    mLoadingLayout.setErrorReloadButtonText("选择门店");
                } else {
                    mLoadingLayout.setErrorReloadButtonText("去添加门店");
                }
            }
            mLoadingLayout.setStatus(LoadingLayout.Error);
            mSelectStoreUtil = new SelectStoreUtil(this, (MyShopModel myShopModel) -> initData());
        }
    }

}
