package com.yx.Pharmacy.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ShopCartAdapter;
import com.yx.Pharmacy.adapter.ShopCartCouponAdapter;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ChooseStoreDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.manage.ProductMaxManage;
import com.yx.Pharmacy.model.CreateOrderIntentModel;
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
    private Double mSubPrice;
    private Double mAllCouponPrice;
    private ShopCartModel mResultBean;
    private double mYingPrice;
    private double mYingOrderPrice;
    private double mMoney;
    private double mDiscountMoney;
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
    private double mYingLayoutPrice;
    private HashMap<String, ArrayList<String>> mDeleteMap;
    private int mDeleteCartPosition;
    private int mDeletePosition;
    private ShopCartModel.CouponListBean mUserSelectCoupon;
    private int mAmountEdit;
    private int mAmountDelete;
    private String mActivityid;
    private double mSelectAllCouponPrice;
    private int type = 0;//选择优惠券还是折扣


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ProductCartActivity.class);
        context.startActivity(intent);
    }

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
        mLoadingLayout.setErrorText("什么都没有~");
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
        mPresenter.loadShopCart(this);
    }


    private void initRecycler() {
        mRvShopCart.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopCartAdapter(R.layout.item_shop_cart);
        ((DefaultItemAnimator) mRvShopCart.getItemAnimator()).setSupportsChangeAnimations(false);
        mRvShopCart.setAdapter(mAdapter);
        mRvShopCart.setNestedScrollingEnabled(false);

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
                        if (!TextUtils.equals(good.quehuo, "true")) {
                            //排除缺货情况
                            if (isAllSelect) {
                                //判断现在是否全选
                                isAllSelect = good.isSelect;
                            }
                        }
                        if (!TextUtils.equals(good.presale, "0")) {
                            //排除失效情况
                            if (isAllSelect) {
                                //判断现在是否全选
                                isAllSelect = good.isSelect;
                            }
                        }
                    }
                }
                if (!isAllSelect) {
                    setCbView();
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
                        if (good.couponList != null) {
                            for (ShopCartModel.CouponListBean couponListBean : good.couponList) {
                                int couponstate = DensityUtils.parseInt(couponListBean.couponstate);
                                if (couponstate != 0) {
                                    if (good.isSelect) {
                                        if (DensityUtils.parseDouble(couponListBean.couponlimit) <= price * cartcount) {
                                            couponListBean.couponEnable = true;
                                            couponListBean.isSelectCoupon = true;
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
                    for (ShopCartModel.CouponListBean couponListBean : datum.couponList) {
                        int couponstate = DensityUtils.parseInt(couponListBean.couponstate);
                        if (couponstate != 0) {
                            if (DensityUtils.parseDouble(couponListBean.couponlimit) <= goodsPrice) {
                                couponListBean.couponEnable = true;
                                couponListBean.isSelectCoupon = true;
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


    // 计算价格
    private void addUpPrice() {
        // 总价格
        mAllPrice = 0.0;
        // 优惠后实付价格
        mSubPrice = 0.0;
        // 应付价格
        mYingLayoutPrice = 0.0;
        //计算全场优惠价格
        mYingPrice = 0.0;
        // 支付价格
        mYingOrderPrice = 0.0;
        // 总优惠价格
        mAllCouponPrice = 0.0;
        //支付显示金额
        mMoney = 0.0;
        //折扣后金额
        mDiscountMoney = 0.0;

        if (!cbCoupon.isChecked()&&!cbDiscount.isChecked())
        {
            type=0;
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
                        int cartcount = DensityUtils.parseInt(good.cartcount);//购物车数量
                        double price = DensityUtils.parseDouble(good.usefulprice);// 当前售价
                        double oprice = DensityUtils.parseDouble(good.oprice);// 原价

                        /*
                        //购物车数量是否大于特价数量
                        if (cartcount>limitnum){
                            double tempprice = (price * limitnum + oprice * (cartcount - limitnum))/cartcount;
                            good.price = DensityUtils.doubleToString(tempprice);
                        }else {
                        }*/
                        if (good.isSelect) {
                            goodsPrice = price * cartcount + goodsPrice;
                            if (good.giftList != null) {
                                for (ShopCartModel.GoodsBean.GiftListBean giftListBean : good.giftList) {
                                    int carcount = DensityUtils.parseInt(good.cartcount);
                                    int limit = DensityUtils.parseInt(giftListBean.limit);
                                    int give = DensityUtils.parseInt(giftListBean.give);
                                    if (carcount >= limit) {
                                        int div = carcount / limit;
                                        int count = div * give;
                                        if (count > 0) {
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
                                        mAllCouponPrice = DoubleMath.add(mAllCouponPrice, couponPrice);
                                    }
                                }
                            }
                        }
                    }
                }
                mAllPrice = DoubleMath.add(mAllPrice, goodsPrice);
                //不含控销和秒杀的商品价格
                if (!TextUtils.equals(datum.type, "9") && !TextUtils.equals(datum.type, "1")) {
                    mYingPrice = DoubleMath.add(mYingPrice, goodsPrice);
                }
                //每个商品的优惠券价格
                Double couponPrice = 0.0;

                //其他专区
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
                mAllCouponPrice = DoubleMath.add(mAllCouponPrice, couponPrice);
                // 出去优惠价格（含控销）
                mSubPrice = DoubleMath.sub(mAllPrice, mAllCouponPrice);
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
                        if (mOrderCoupon == null) {
                            Double sub = DoubleMath.sub(mYingPrice, mAllCouponPrice);
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
                            Double sub = DoubleMath.sub(mYingPrice, mAllCouponPrice);
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
                        }
                    } else {
                        mOrderCoupon = mUserSelectCoupon;
                    }
                }


                // 全场优惠券价格计算
                mYingOrderPrice = mSubPrice;
                mYingLayoutPrice = mSubPrice;
                if (type == 1) {
                    mYingPrice = DoubleMath.sub(mYingPrice, mAllCouponPrice);
                    mSelectAllCouponPrice = mYingPrice;
                    if (mOrderCoupon != null) {
                        int couponmodel = DensityUtils.parseInt(mOrderCoupon.couponmodel);
                        double orderCouponPrice = 0.00;
                        if (couponmodel == 2) {
                            double coupondiscount = DensityUtils.parseDouble(mOrderCoupon.coupondiscount);
                            orderCouponPrice = DoubleMath.sub(mYingPrice, DoubleMath.mul(mYingPrice, coupondiscount));
                        } else {
                            orderCouponPrice = DensityUtils.parseDouble(mOrderCoupon.discountprice);
                        }
                        mYingOrderPrice = DoubleMath.sub(mYingOrderPrice, orderCouponPrice);
                        mYingPrice = DoubleMath.sub(mYingPrice, orderCouponPrice);
                        mYingLayoutPrice = mYingOrderPrice;
                    }
                }
            }
            else
            {
                cbCoupon.setClickable(false);
                cbCoupon.setChecked(false);
            }

            mDiscount = "";

            // 全场优惠
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
                    if (mAllPrice >= limit) {
                        cbDiscount.setClickable(true);
                        mDiscount = bean.discount;
                        mTvOverallCoupon.setText("满" + bean.limit + "元,可享受" + bean.discount + "折优惠(不含控销商品)");
                        if (type == 2) {
                            // 计算没有控销的商品打完折后，加上控销商品的价格
                            Double kxPrice = DoubleMath.sub(mYingOrderPrice, mYingPrice);
                            double discount = DensityUtils.parseDouble(mDiscount) / 10;
                            mDiscountMoney = DoubleMath.mul(mYingPrice, discount);
                            mDiscountMoney = DoubleMath.add(mDiscountMoney, kxPrice);
                        }
                        break;
                    } else {
                        cbDiscount.setClickable(false);
                        mDiscountMoney = mAllPrice;
                        mTvOverallCoupon.setText("订单金额低于全场优惠价最低金额");
                    }
                }
            }
            else
            {
                cbDiscount.setClickable(false);
                cbDiscount.setChecked(false);
            }

        }

        // 优惠金额
        mTvCouponPrice.setText("-" + DensityUtils.doubleToString(mAllCouponPrice));
        // 原总金额
        mTvPrice.setText(DensityUtils.doubleToString(mAllPrice));
        // 实付总金额
        mTvOrderPrice.setText(DensityUtils.doubleToString(mSubPrice));
        // 应付总额
        mTvMoney.setText(DensityUtils.doubleToString(mYingLayoutPrice));
//        mYingOrderPrice = DensityUtils.round(mYingOrderPrice, 2);
//        mTvPayPrice.setText(DensityUtils.doubleToString(mYingOrderPrice));
        // 支付总额
        if (type == 1) {
            mMoney = mYingLayoutPrice;
        } else if (type == 2) {
            mMoney = mDiscountMoney;
        } else {
            mMoney = mAllPrice;
        }
        mTvPayPrice.setText(DensityUtils.doubleToString(mMoney));

        //-----------全场优惠-------
        if (mResultBean!=null&&mResultBean.orderlimit!=null)
        {
            double i = DensityUtils.parseDouble(mResultBean.orderlimit);
            if (mMoney >= i) {
                mTvBuy.setText("去结算");
                mTvBuy.setEnabled(true);

            } else {
                mTvBuy.setText("满" + DensityUtils.doubleTrans2(i) + "起购");
                mTvBuy.setEnabled(false);
                setCbView();
            }
        }

        checkAllCoupon();
    }

    private void setCbView() {
        cbCoupon.setChecked(false);
        cbCoupon.setClickable(false);
        cbDiscount.setChecked(false);
        cbDiscount.setClickable(false);
        type = 0;
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

    private void initView() {
        View bottomView = LayoutInflater.from(this).inflate(R.layout.include_shop_cart_bottom, null);
        mAdapter.addFooterView(bottomView);
        mTvPrice = bottomView.findViewById(R.id.tv_price);
        mTvCouponPrice = bottomView.findViewById(R.id.tv_coupon_price);
        mTvOrderPrice = bottomView.findViewById(R.id.tv_order_price);
        mLlSelectCoupon = bottomView.findViewById(R.id.ll_select_coupon);
        mTvCouponSize = bottomView.findViewById(R.id.tv_coupon_size);
        mTvMoney = bottomView.findViewById(R.id.tv_money);
        cbCoupon = bottomView.findViewById(R.id.cb_coupon);
        cbCoupon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type = 1;
                    cbDiscount.setChecked(false);
                }
                addUpPrice();
            }
        });
        cbDiscount = bottomView.findViewById(R.id.cb_discount);
        cbDiscount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type = 2;
                    cbCoupon.setChecked(false);
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
                    } else if (TextUtils.isEmpty(NetUtil.getToken())) {

                    } else {
                        if (mSelectStoreUtil != null) {
                            mSelectStoreUtil.loadMyShop(ProductCartActivity.this, true);
                        } else {
                            mSelectStoreUtil = new SelectStoreUtil(ProductCartActivity.this, new SelectStoreUtil.OnSelectStoreListener() {
                                @Override
                                public void onSelect() {
                                    initData();
                                }
                            });
                        }
                    }
                }
            }
        });

        mLlSelectCoupon.setOnClickListener(new View.OnClickListener() {
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
                                if (mYingOrderPrice >= parseDouble) {
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
                        detailMap.put("couponid", "");
                        if (good.couponList != null) {
                            for (ShopCartModel.CouponListBean couponListBean : good.couponList) {
                                if (couponListBean.isSelectCoupon) {
                                    detailMap.put("couponid", couponListBean.couponid);
                                }
                            }
                        }
                        detailMap.put("count", good.cartcount);
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
                        if (good.giftInfo != null) {
                            OrderModel.Goods gift = new OrderModel.Goods();
                            gift.title = good.giftInfo.goodsname;
                            gift.gg = good.giftInfo.goodsgg;
                            gift.thumb = good.giftInfo.goodsthumb;
                            //TODO 计算gift.count
                            int carcount = DensityUtils.parseInt(good.cartcount);
                            int limit = DensityUtils.parseInt(good.giftList.get(0).limit);
                            int give = DensityUtils.parseInt(good.giftList.get(0).give);
                            if (carcount >= limit) {
                                int div = carcount / limit;
                                int count = div * give;
                                gift.count = count;
                            }
                            gifts.add(gift);
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
        if (type == 1) {
            map.put("discount", "");
        } else if (type == 2) {
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
        if (TextUtils.isEmpty(mResultBean.notice)) {
            mTvNotice.setVisibility(View.GONE);
        } else {
            mTvNotice.setVisibility(View.VISIBLE);
            mTvNotice.setText(mResultBean.notice);
        }
        mCbCheckall.setChecked(false);
        List<ShopCartModel.ShopCartListBean> shopCartList = mResultBean.shopCartList;
        if (shopCartList != null && shopCartList.size() > 0) {
            mLoadingLayout.setStatus(LoadingLayout.Success);
        } else {
            mLoadingLayout.setStatus(LoadingLayout.Empty);
        }
        mAdapter.setNewData(shopCartList);
        addUpPrice();
    }

    @Override
    public void deleteSuccess() {
        if (mDeleteMap != null && mDeleteMap.size() > 0) {
            initData();
            mLoadingLayout.setStatus(LoadingLayout.Empty);
        } else {
            ShopCartModel.ShopCartListBean shopCartListBean = mAdapter.getData().get(mDeleteCartPosition);
            if (shopCartListBean.goods.size() == 1) {
                mAdapter.getData().remove(mDeleteCartPosition);
                mAdapter.notifyDataSetChanged();
            } else {
                shopCartListBean.goods.remove(mDeletePosition);
                mAdapter.notifyItemChanged(mDeleteCartPosition);
            }
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
            if (type == 1) {
                data = new CreateOrderIntentModel(mProducts, NetUtil.isStringNull(mCouponId), DensityUtils.doubleToString(mMoney), String.valueOf(mCount), "");
            } else if (type == 2) {
                data = new CreateOrderIntentModel(mProducts, "", DensityUtils.doubleToString(mMoney), String.valueOf(mCount), NetUtil.isStringNull(mDiscount));
            } else {
                data = new CreateOrderIntentModel(mProducts, "",DensityUtils.doubleToString(mMoney), String.valueOf(mCount), "");
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
            mSelectStoreUtil = new SelectStoreUtil(this, () -> initData());
        }
    }

}
