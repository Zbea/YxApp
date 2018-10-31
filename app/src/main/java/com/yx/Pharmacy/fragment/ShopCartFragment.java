package com.yx.Pharmacy.fragment;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.LoginActivity;
import com.yx.Pharmacy.activity.MyShopActivity;
import com.yx.Pharmacy.adapter.ShopCartAdapter;
import com.yx.Pharmacy.adapter.ShopCartCouponAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.manage.ProductMaxManage;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.ShopCartPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.DoubleMath;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IShopCartView;
import com.yx.Pharmacy.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created time  2018/8/1 0001
 * @author : mcx
 * 类描述 : 
 */

public class ShopCartFragment
        extends BaseFragment implements IShopCartView
{
    @BindView(R.id.tv_title)
    TextView      mTvTitle;
    @BindView(R.id.tv_notice)
    TextView      mTvNotice;
    @BindView(R.id.tv_more)
    TextView      mTvMore;
    @BindView(R.id.ll_checkall)
    LinearLayout  mLlCheckall;
    @BindView(R.id.cb_checkall)
    CheckBox      mCbCheckall;
    @BindView(R.id.tv_buy)
    TextView      mTvBuy;
    @BindView(R.id.tv_pay_price)
    TextView      mTvPayPrice;
    @BindView(R.id.rv_shop_cart)
    RecyclerView  mRvShopCart;
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
    private Double mAllPrice;
    private Double mSubPrice;
    private Double mAllCouponPrice;
    private ShopCartModel                      mResultBean;
    private double                             mYingPrice;
    private double                             mYingOrderPrice;
    private String                             mDiscount;
    private ArrayList<OrderModel.Goods>        mOrderGoods;
    private HashMap<String, Object>            mProducts;
    private String                             mCouponId;
    private int                                mCount;
    private Dialog                             mCouponDialog;
    private ShopCartCouponAdapter              mCouponListAdapter;
    private List<ShopCartModel.CouponListBean> mCouponList;
    private int                                mCouponPosition;
    private String                             mCreditString;
    private ShopCartModel.CouponListBean       mOrderCoupon;
    private int screenHeight;
    private boolean mIsEdit = false;
    private int mNotifyPosition;//操作的position

    @Override
    protected int getLayoutId() {
        return R.layout.frag_shopcart;
    }
    private ImmersionBar mImmersionBar;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }
    @Override
    protected void init() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        mPresenter = new ShopCartPresenter(this);

        mLoadingLayout.setStatus(LoadingLayout.Loading);
        mLoadingLayout.setEmptyText("什么都没有~");
        mLoadingLayout.setErrorText("什么都没有~");
        mLoadingLayout.setEmptyReloadButtonText("去首页看看");
        mLoadingLayout.setEmptyImage(R.drawable.icon_shopcar_empty);
        mLoadingLayout.setErrorImage(R.drawable.icon_shopcar_empty);
        initRecycler();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();

        //软键盘监听
        screenHeight = mContext.getWindow().getDecorView().getHeight();
        mContext.getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //获取View可见区域的bottom
                Rect rect = new Rect();
                mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                if(bottom!=0 && oldBottom!=0 && bottom - rect.bottom <= 0){
//                    getShortToastByString("隐藏");
                    if (mIsEdit) {
                        mIsEdit = false;
                        selectCoupon();
                        addUpPrice();
                    }
                }else {
//                    getShortToastByString("弹出");
                }
            }
        });

    }

    public void initData() {
        mPresenter.loadShopCart((BaseActivity) mContext);
    }

    private void initRecycler() {
        mRvShopCart.setLayoutManager(new LinearLayoutManager(mContext));
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
                    case R.id.ll_single_coupon:// 单品优惠券
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
                            if (isAllSelect){
                                //判断现在是否全选
                                isAllSelect = good.isSelect;
                            }
                        }
                        if (!TextUtils.equals(good.presale, "0")) {
                            //排除失效情况
                            if (isAllSelect){
                                //判断现在是否全选
                                isAllSelect = good.isSelect;
                            }
                        }
                    }
                }

                mCbCheckall.setChecked(isAllSelect);

                mNotifyPosition = position;
                selectCoupon();

                // 计算价格
                addUpPrice();
            }

            @Override
            public void removeShop(BaseQuickAdapter adapter, int cartposition, int position, String activityid) {
                // 删除商品
                ShopCartModel.GoodsBean bean = (ShopCartModel.GoodsBean) adapter.getData().get(position);
                String                  goodsid = bean.goodsid;
                ArrayList<String>       arrayList = new ArrayList<>();
                arrayList.add(goodsid);
                HashMap<String, ArrayList<String> > urlMap = new HashMap<>();
                urlMap.put(activityid,arrayList);
                mPresenter.deleteShopCart((BaseActivity) mContext,urlMap);
            }

            @Override
            public void aumontChange(View view, int amount, String goodsid, String activityid,boolean isEdit,int position,int addnum) {
                // 修改商品数量
                mPresenter.updateShopCart((BaseActivity) mContext,activityid,goodsid,amount);
                mIsEdit = isEdit;
                mNotifyPosition = position;
                if (!mIsEdit) {
                    selectCoupon();
                    addUpPrice();
                }
            }

            @Override
            public void toDetail(BaseQuickAdapter adapter, String goodsid) {
//                ProductDetailActivity.startActivity(mContext,goodsid);
            }

            @Override
            public void singleCoupon(BaseQuickAdapter adapter,
                                     int cartposition,
                                     int position,
                                     String activityid)
            {

            }
        });
    }

    // 自动选择优惠券
    private void selectCoupon(){
        List<ShopCartModel.ShopCartListBean> data = mAdapter.getData();
        for (ShopCartModel.ShopCartListBean datum : data) {
            // 专区商品价格
            Double goodsPrice = 0.0;
            if (datum.goods!=null) {
                for (ShopCartModel.GoodsBean good : datum.goods) {
                    int limitnum = DensityUtils.parseInt(good.limitnum);// 特价数量
                    int cartcount = DensityUtils.parseInt(good.cartcount);//购物车数量
                    double price = DensityUtils.parseDouble(good.usefulprice);// 当前售价
                    double oprice = DensityUtils.parseDouble(good.oprice);// 原价
                    //购物车数量是否大于特价数量
                    if (cartcount>limitnum){
                        double tempprice = (price * limitnum + oprice * (cartcount - limitnum))/cartcount;
                        good.price = DensityUtils.doubleToString(tempprice);
                    }else {
                        good.price = DensityUtils.doubleToString(price);
                    }
                    if (good.isSelect) {
                        goodsPrice = DensityUtils.parseDouble(good.price)*cartcount + goodsPrice;
                    }
                }
                //自动选中优惠券
                if (datum.couponList!=null) {
                    for (ShopCartModel.CouponListBean couponListBean : datum.couponList) {
                        int couponstate = DensityUtils.parseInt(couponListBean.couponstate);
                        if (couponstate!=0) {
                            if (DensityUtils.parseDouble(couponListBean.couponlimit)<=goodsPrice) {
                                couponListBean.couponEnable = true;
                                couponListBean.isSelectCoupon = true;
                            }else {
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
        mYingPrice = 0.0;
        mYingOrderPrice = 0.0;
        // 总优惠价格
        mAllCouponPrice = 0.0;
        List<ShopCartModel.ShopCartListBean> data           = mAdapter.getData();
        if (data.size()>0) {
            for (ShopCartModel.ShopCartListBean datum : data) {
                List<ShopCartModel.CouponListBean> couponList = datum.couponList;
                List<ShopCartModel.GoodsBean> goods = datum.goods;
                // 专区商品价格
                Double goodsPrice = 0.0;
                if (goods!=null) {
                    for (ShopCartModel.GoodsBean good : goods) {
                        int limitnum = DensityUtils.parseInt(good.limitnum);// 特价数量
                        int cartcount = DensityUtils.parseInt(good.cartcount);//购物车数量
                        double price = DensityUtils.parseDouble(good.usefulprice);// 当前售价
                        double oprice = DensityUtils.parseDouble(good.oprice);// 原价
                        //购物车数量是否大于特价数量
                        if (cartcount>limitnum){
                            double tempprice = (price * limitnum + oprice * (cartcount - limitnum))/cartcount;
                            good.price = DensityUtils.doubleToString(tempprice);
                        }else {
                            good.price = DensityUtils.doubleToString(price);
                        }
                        if (good.isSelect) {
                            goodsPrice = DensityUtils.parseDouble(good.price)*cartcount + goodsPrice;
                        }
                    }
                }
                mAllPrice = DoubleMath.add(mAllPrice, goodsPrice);
                //每个商品的优惠券价格
                Double couponPrice = 0.0;

                if (couponList!=null) {
                    for (ShopCartModel.CouponListBean couponListBean : couponList) {
                        if (DensityUtils.parseDouble(couponListBean.couponlimit)<=goodsPrice) {
                            couponListBean.couponEnable = true;
                            if (couponListBean.isSelectCoupon) {
                                int couponmodel = DensityUtils.parseInt(couponListBean.couponmodel);
                                if (couponmodel==2) {
                                    double coupondiscount = DensityUtils.parseDouble(couponListBean.coupondiscount);
                                    couponPrice = DoubleMath.sub(goodsPrice,DoubleMath.mul(goodsPrice,coupondiscount));
                                }else {
                                    couponPrice = DensityUtils.parseDouble(couponListBean.discountprice);
                                }
                            }
                        }else {
                            couponListBean.couponEnable = false;
                            couponListBean.isSelectCoupon = false;
                        }
                    }
                }
                mAllCouponPrice = DoubleMath.add(mAllCouponPrice, couponPrice);
                mSubPrice = DoubleMath.sub(mAllPrice, mAllCouponPrice);

                if (mOrderCoupon!=null) {
                    int couponmodel = DensityUtils.parseInt(mOrderCoupon.couponmodel);
                    double orderCouponPrice = 0.00;
                    if (couponmodel==2) {
                        double coupondiscount = DensityUtils.parseDouble(mOrderCoupon.coupondiscount);
                        orderCouponPrice = DoubleMath.sub(mSubPrice,DoubleMath.mul(mSubPrice,coupondiscount));
                    }else {
                        orderCouponPrice = DensityUtils.parseDouble(mOrderCoupon.discountprice);
                    }
                    mAllCouponPrice = DoubleMath.add(mAllCouponPrice, orderCouponPrice);
                    mSubPrice = DoubleMath.sub(mSubPrice, orderCouponPrice);
                }
                mYingOrderPrice = mSubPrice;

                if(!TextUtils.equals(datum.type,"9")){
                    mYingPrice = DoubleMath.sub(DoubleMath.add(mYingPrice, goodsPrice), couponPrice);
                }
            }
            mDiscount = "";
            // 全场优惠
            List<ShopCartModel.DiscountListBean> discountList = mResultBean.discountList;
            if (discountList!=null) {
                for (int i = discountList.size() - 1; i >= 0; i--) {
                    ShopCartModel.DiscountListBean bean = discountList.get(i);
                    double limit = DensityUtils.parseDouble(bean.limit);
                    if (mYingPrice>=limit) {
                        mDiscount = bean.discount;
                        mTvOverallCoupon.setText("满"+bean.limit+"元,可享受"+bean.discount+"折优惠(不含控销商品)");
                        // 计算没有控销的商品打完折后，加上控销商品的价格
                        Double kxPrice = DoubleMath.sub(mSubPrice, mYingPrice);
                        double discount = DensityUtils.parseDouble(mDiscount)/10;

                        mYingOrderPrice = DoubleMath.mul(mYingPrice,discount);
                        mYingOrderPrice = DoubleMath.add(mYingOrderPrice, kxPrice);
                        break;
                    }else {
                        mTvOverallCoupon.setText("订单金额低于全场优惠价最低金额");
                    }
                }
            }

            double i = DensityUtils.parseDouble(mResultBean.orderlimit);
            if(mYingOrderPrice>=i){
                mTvBuy.setText("去结算");
                mTvBuy.setEnabled(true);
            }else {
                mTvBuy.setText("满"+DensityUtils.doubleTrans2(i)+"起购");
                mTvBuy.setEnabled(false);
            }
        }

        // 优惠金额
        mTvCouponPrice.setText("-"+ DensityUtils.doubleToString(mAllCouponPrice));
        // 总金额
        mTvPrice.setText(DensityUtils.doubleToString(mAllPrice));
        // 实付金额
        mTvOrderPrice.setText(DensityUtils.doubleToString(mSubPrice));
        mTvMoney.setText(DensityUtils.doubleToString(mSubPrice));
        mYingOrderPrice = DensityUtils.round(mYingOrderPrice,2);
        // 应付总额
        mTvPayPrice.setText(DensityUtils.doubleToString(mYingOrderPrice));
    }

    private void initView() {

        View bottomView = LayoutInflater.from(mContext).inflate(R.layout.include_shop_cart_bottom,null);
        mAdapter.addFooterView(bottomView);
        mTvPrice = bottomView.findViewById(R.id.tv_price);
        mTvCouponPrice = bottomView.findViewById(R.id.tv_coupon_price);
        mTvOrderPrice = bottomView.findViewById(R.id.tv_order_price);
        mLlSelectCoupon = bottomView.findViewById(R.id.ll_select_coupon);
        mTvCouponSize = bottomView.findViewById(R.id.tv_coupon_size);
        mTvMoney = bottomView.findViewById(R.id.tv_money);
        mTvOverallCoupon = bottomView.findViewById(R.id.tv_overall_coupon);

        mLoadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                if (mLoadingLayout.getStatus()== LoadingLayout.Empty) {
                    ((MainActivity)mContext).setSelectTab(3);
                }else {
                    if (TextUtils.isEmpty(NetUtil.getToken())) {
                        LoginActivity.startActivity(mContext);
                    }else {
                        if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY)) {
                            MainActivity context = (MainActivity) mContext;
                            context.getMyShop();
                        }else {
                            MyShopActivity.startActivity(mContext);
                        }
                    }
                }
            }
        });

        mLlSelectCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mResultBean!=null) {
                    mCouponList = mResultBean.couponList;
                    for (ShopCartModel.CouponListBean couponListBean : mCouponList) {
                        double parseDouble = DensityUtils.parseDouble(couponListBean.couponlimit);
                        if (mYingOrderPrice>=parseDouble) {
                            couponListBean.couponEnable = true;
                        }
                    }
                    showCouponDialog(2);
                }
            }
        });

        mTvNotice.setSelected(true);
    }

    @OnClick({R.id.tv_more,
              R.id.ll_checkall,
              R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_more:
                if (mResultBean!=null) {
                    HashMap<String, ArrayList<String> > urlMap = new HashMap<>();
                    for (ShopCartModel.ShopCartListBean shopCartListBean : mResultBean.shopCartList) {
                        ArrayList<String>       arrayList = new ArrayList<>();
                        for (ShopCartModel.GoodsBean good : shopCartListBean.goods) {
                            String                  goodsid = good.goodsid;
                            arrayList.add(goodsid);
                        }
                        if (arrayList.size()>0){
                            urlMap.put(shopCartListBean.activityid,arrayList);
                        }
                    }
                    mPresenter.deleteShopCart((BaseActivity) mContext,urlMap);
                }
                break;
            case R.id.ll_checkall:
                mCbCheckall.setChecked(!mCbCheckall.isChecked());
                mAdapter.setCheckAll(mCbCheckall.isChecked());
                selectCoupon();
                addUpPrice();
                break;
            case R.id.tv_buy:
                checkMoney();
                break;
        }
    }

    private void checkMoney() {
        mCount = 0;
        List<ShopCartModel.ShopCartListBean> data  = mAdapter.getData();
        mProducts = new HashMap<>();
        mOrderGoods = new ArrayList<>();
        for (ShopCartModel.ShopCartListBean datum : data) {
            HashMap<String, Object> goodsMap = new HashMap<>();
            List<ShopCartModel.GoodsBean> goods = datum.goods;
            ArrayList<HashMap<String, Object>>       goodList = new ArrayList<>();
            if (goods!=null) {
                for (ShopCartModel.GoodsBean good : goods) {
                    if (good.isSelect) {
                        HashMap<String, Object> detailMap = new HashMap<>();
                        detailMap.put("pid",good.goodsid);
                        detailMap.put("price",good.price);
                        detailMap.put("couponid","");
                        detailMap.put("count",good.cartcount);
                        mCount = mCount+DensityUtils.parseInt(good.cartcount);
                        goodList.add(detailMap);
                        OrderModel.Goods            ordergood  = new OrderModel.Goods();
                        ordergood.count = DensityUtils.parseInt(good.cartcount);
                        ordergood.thumb = good.thumb;
                        ordergood.title = good.title;
                        ordergood.scqy = good.scqy;
                        ordergood.gg = good.gg;
                        mOrderGoods.add(ordergood);
                    }

                }
            }
            if (goodList.size()!=0) {
                goodsMap.put("goodsList",goodList);
                goodsMap.put("couponid","");
                if (datum.couponList!=null) {
                    for (ShopCartModel.CouponListBean couponListBean : datum.couponList) {
                        if (couponListBean.isSelectCoupon) {
                            goodsMap.put("couponid",datum.activityid);
                        }
                    }
                }
                goodsMap.put("activityid",datum.activityid);

                mProducts.put(datum.activityid, goodsMap);
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("couponid",NetUtil.isStringNull(mCouponId));
        // 全场优惠券
        if (mResultBean!=null) {
            if (mResultBean.couponList!=null) {
                for (ShopCartModel.CouponListBean couponListBean : mResultBean.couponList) {
                    if (couponListBean.isSelectCoupon) {
                        mCouponId = couponListBean.couponid;
                        map.put("couponid",NetUtil.isStringNull(mCouponId));
                    }
                }
            }
        }
        map.put("needpay",mYingOrderPrice);
        map.put("discount", NetUtil.isStringNull(mDiscount));
        map.put("product", mProducts);
        if (mCount!=0) {
            mPresenter.checkMoney((BaseActivity)mContext,new Gson().toJson(map));
        }else {
            getShortToastByString("请选择结算的商品");
        }
    }

    @Override
    public void showShopCartList(ShopCartModel data) {
        mResultBean = data;
        mTvNotice.setText(mResultBean.notice);
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
        initData();
        addUpPrice();
    }

    @Override
    public void showCheckResult(BasisBean<String> response) {
        if (TextUtils.equals(response.getCode(), "200")) {
//            OrderCreateActivity.startActivity(mContext, mOrderGoods, new CreateOrderIntentModel(mProducts,NetUtil.isStringNull(mCouponId), DensityUtils.doubleToString(mYingOrderPrice),String.valueOf(mCount),NetUtil.isStringNull(mDiscount)),
//                                              mAllPrice,mAllCouponPrice);
        }
    }

    @Override
    public void showFailView() {
        if (TextUtils.isEmpty(NetUtil.getToken())) {
            mLoadingLayout.setErrorReloadButtonText("去登录");
        }else {
            if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY,false)) {
                mLoadingLayout.setErrorReloadButtonText("选择门店");
            }else {
                mLoadingLayout.setErrorReloadButtonText("去添加门店");
            }
        }
        mLoadingLayout.setStatus(LoadingLayout.Error);
    }

    @Override
    public void showSaveResult(String couponid) {
        for (ShopCartModel.CouponListBean listBean : mCouponList) {
            if (TextUtils.equals(couponid, listBean.couponid)) {
                listBean.couponstate = "1";
            }
            if (mCouponListAdapter!=null) {
                mCouponListAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void updateResult() {
//        for (ShopCartModel.ShopCartListBean datum : mResultBean.shopCartList) {
//            for (ShopCartModel.GoodsBean good : datum.goods) {
//                if (TextUtils.equals(good.goodsid, mGoodsid)) {
//                    ProductMaxManage.newInstance().refresh((int) Math.abs(mAmountEdit-Double.parseDouble(good.cartcount)));
//                }
//            }
//        }
    }

    /**
     * 优惠券弹窗
     * @param i
     */
    public void showCouponDialog(int i) {
        if(getActivity()!=null){
            if(mCouponDialog==null){
                mCouponDialog = new Dialog(mContext,R.style.SrcbDialog);
                View view  = getLayoutInflater().inflate(R.layout.dialog_shopcart_coupon, null);
                View.OnClickListener onClickListener = v -> {
                    switch (v.getId()){
                        case R.id.iv_cancel:
                            mCouponDialog.dismiss();
                            break;
                        default:
                            break;
                    }
                };
                view.findViewById(R.id.iv_cancel).setOnClickListener(onClickListener);
                RecyclerView dialogCoupon = view.findViewById(R.id.rv_coupon);
                dialogCoupon.setLayoutManager(new LinearLayoutManager(mContext));
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
                        if(couponstate==0){//未领取
                            mCreditString = couponListBean.credit;
                            mPresenter.saveCoupon((BaseActivity)mContext,couponListBean.couponid);
                        }else {//有的优惠券
                            if (i==1){
                                if (couponListBean.couponEnable) {
                                    mCouponList.get(position).isSelectCoupon = true;
                                    mCouponDialog.dismiss();
                                    addUpPrice();
                                    mAdapter.notifyDataSetChanged();
                                }
                            }else {
                                mTvCouponSize.setText(couponListBean.couponcontent);
                                mOrderCoupon = couponListBean;
                                addUpPrice();
                                mAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                });
                Window win = mCouponDialog.getWindow();
                win.setWindowAnimations(R.style.mystyle);
                win.getDecorView().setPadding(0, 0, 0, 0);
                WindowManager.LayoutParams lp = win.getAttributes();
                // 设置弹出框的宽高
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = DensityUtils.dp2px(mContext,400);
                // 设置弹出框的位置
                win.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                win.setAttributes(lp);
                mCouponDialog.setContentView(view);
                mCouponDialog.show();
            }else {
                mCouponDialog.show();
                if (mCouponListAdapter!=null) {
                    mCouponListAdapter.setNewData(mCouponList);
                }
            }
        }
    }

}
