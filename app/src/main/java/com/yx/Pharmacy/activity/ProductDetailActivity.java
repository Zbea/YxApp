package com.yx.Pharmacy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.ProductCommendAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.AddCartDialog;
import com.yx.Pharmacy.dialog.ChooseStoreDialog;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.loader.GlideImageLoader;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.manage.StoreManage;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.ProductDetailModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.ProductDetailPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IProductDetailView;
import com.yx.Pharmacy.widget.AmountView;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;
import com.yx.Pharmacy.widget.LoadingLayout;

import org.apache.cordova.engine.SystemWebView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class ProductDetailActivity
        extends BaseActivity
        implements IProductDetailView {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rl_collect)
    RelativeLayout mRlCollect;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_product_company)
    TextView mTvProductCompany;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_oldprice)
    TextView mTvOldprice;
    @BindView(R.id.tv_unit)
    TextView mTvUnit;
    @BindView(R.id.tv_has_sale)
    TextView mTvHasSale;
    @BindView(R.id.iv_jiantou_gery)
    ImageView mIvJiantouGery;
    @BindView(R.id.ll_manjian)
    LinearLayout mLlManjian;
    @BindView(R.id.rl_manjian)
    RelativeLayout mRlManjian;
    @BindView(R.id.ll_coupon)
    LinearLayout ll_coupon;
    @BindView(R.id.tv_coupon_info)
    TextView tv_coupon_info;
    @BindView(R.id.tv_sale_record)
    TextView mTvSaleRecord;
    @BindView(R.id.ll_sale_record)
    LinearLayout mLlSaleRecord;
    @BindView(R.id.ll_more)
    LinearLayout mLlMore;
    @BindView(R.id.rv_commend_product)
    RecyclerView mRvCommendProduct;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.iv_home)
    ImageView mIvHome;
    @BindView(R.id.iv_cart)
    ImageView mIvCart;
    @BindView(R.id.tv_add_cart)
    TextView mTvAddCart;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_presale)
    TextView mTvPresale;
    @BindView(R.id.iv_is_collect)
    ImageView iv_is_collect;
    @BindView(R.id.iv_coupon_icon)
    ImageView mIvCouponIcon;
    @BindView(R.id.tv_tongyong)
    TextView mTvTongyong;
    @BindView(R.id.tv_pizhun)
    TextView mTvPizhun;
    @BindView(R.id.tv_guige)
    TextView mTvGuige;
    @BindView(R.id.tv_changjia)
    TextView mTvChangjia;
    @BindView(R.id.tv_riqi)
    TextView mTvRiqi;
    @BindView(R.id.tv_youxiao)
    TextView mTvYouxiao;
    @BindView(R.id.iv_product_type)
    ImageView mIvProductType;
    @BindView(R.id.tv_top_price)
    TextView mTvTopPrice;
    @BindView(R.id.tv_top_old_price)
    TextView mTvTopOldPrice;
    @BindView(R.id.tv_time_state)
    TextView mTvTimeState;
    @BindView(R.id.cv_countdownView)
    CountdownView mCvCountdownView;
    @BindView(R.id.cv_countdownView_day)
    CountdownView mCvCountdownViewDay;

    @BindView(R.id.tv_top_unit)
    TextView mTvTopUnit;
    @BindView(R.id.tv_levelnote)
    TextView mTvLevelnote;
    @BindView(R.id.ll_levelnote)
    LinearLayout ll_levelnote;
    @BindView(R.id.rl_info)
    RelativeLayout mRlInfo;
    @BindView(R.id.ll_countdown)
    LinearLayout mRlCountDown;
    @BindView(R.id.ll_price)
    LinearLayout ll_price;
    @BindView(R.id.rl_gg)
    RelativeLayout rl_gg;
    @BindView(R.id.loadinglayout)
    LoadingLayout loadinglayout;
    @BindView(R.id.tv_sales)
    TextView tv_sales;
    @BindView(R.id.tv_product_progress)
    TextView tv_product_progress;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.ll_maizeng)
    LinearLayout llMaizeng;
    private ProductDetailPresenter mPresenter;
    private ProductCommendAdapter mCommendAdapter;
    private String mItemId;
    private ProductDetailModel mResultBean;
    private boolean mQuehuo;
    private String mGiftId;
    private boolean mIsStart = true;
    private ChooseStoreDialog mChooseStoreDialog;
    private int cartCount = 0;
    private AddCartDialog addCartDialog=null;
    private int type=0;//判断是原价还是特价购买

    public static void startActivity(Context context, String itemid) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(Constants.KEY_ITEM_ID, itemid);
        context.startActivity(intent);
    }

    private StoreManage.StoreManageListener storeManageListener=new StoreManage.StoreManageListener() {
        @Override
        public void onRefresh(MyShopModel data) {
            initData();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mItemId = getIntent().getStringExtra(Constants.KEY_ITEM_ID);
        mPresenter = new ProductDetailPresenter(this);
        loadinglayout.setStatus(LoadingLayout.Loading);

        StoreManage.newInstance().setStoreManageListener(storeManageListener);

        CartCountManage.newInstance().setCartCountManageListener(new CartCountManage.CartCountManageListener() {
            @Override
            public void onRefresh(int max) {
                setCartNum(max);
            }
        });

        loadinglayout.setEmptyImage(R.drawable.wdddwk);
        loadinglayout.setEmptyText("商品已下架");
        loadinglayout.setEmptyReloadButtonText("返回首页");
        loadinglayout.setErrorImage(R.drawable.wdddwk);
        loadinglayout.setErrorText("加载数据失败啦");
        loadinglayout.setErrorReloadButtonText("重新加载");
        initRecycler();
        initListener();
        initBanner();
//        initData();
    }

    private void initBanner() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setViewPagerIsScroll(true);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(false);
    }

    private void initData() {
        type=0;
        mPresenter.loadProductDetail(ProductDetailActivity.this, mItemId);
        mPresenter.getShopcarNum(ProductDetailActivity.this);
    }

    private void initListener() {
        mCommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductDetailActivity.startActivity(ProductDetailActivity.this,
                        mCommendAdapter.getData()
                                .get(position).itemid);
            }
        });
        loadinglayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {
                if (loadinglayout.getStatus() == LoadingLayout.Empty) {
                    finish();
                } else {
                    mPresenter.loadProductDetail(ProductDetailActivity.this, mItemId);
                }
            }
        });
    }

    public void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvCommendProduct.setLayoutManager(layoutManager);
        mCommendAdapter = new ProductCommendAdapter(R.layout.item_product_detail_commend);
        mRvCommendProduct.setAdapter(mCommendAdapter);
        mRvCommendProduct.setNestedScrollingEnabled(false);
    }

    private void setStoreJudge()
    {
        if (TextUtils.isEmpty(NetUtil.getToken())) {
            LoginActivity.startActivity(this, 1);
            return;
        }
        if (TextUtils.isEmpty(NetUtil.getStoreid())) {
            SelectStoreUtil selectStoreUtil=new SelectStoreUtil(mContext,null);
            return;
        }
    }


    @OnClick({R.id.rl_back,
            R.id.rl_collect,
            R.id.ll_sale_record,
            R.id.iv_home,
            R.id.iv_cart,
            R.id.tv_add_cart,
            R.id.iv_service,
            R.id.rl_manjian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_collect://收藏
                setStoreJudge();
                if (mResultBean.issave) {
                    mPresenter.cancleCollect(ProductDetailActivity.this, mItemId);
                } else {
                    mPresenter.collectProduct(ProductDetailActivity.this, mItemId);
                }
                break;
            case R.id.ll_sale_record:
                SaleRecordActivity.startActivity(ProductDetailActivity.this, mItemId);
                break;
            case R.id.iv_home:
                MainActivity.startActivity(this);
                break;
            case R.id.iv_cart:
                ProductCartActivity.startActivity(this);
                break;
            case R.id.tv_add_cart:
                // 加入购物车
                setStoreJudge();

                if (mResultBean != null) {
                    if (mQuehuo) {
                        mPresenter.productArrive(this, mItemId);
                    } else {
//                        if (TextUtils.equals(mResultBean.type, "1")||TextUtils.equals(mResultBean.type, "2")) {
//                            if (mIsStart) {
//                                // 抢购未开始
//                                showComfirmDialog1();
//                                return;
//                            }
//                        }
                        if (TextUtils.equals(mResultBean.type, "1")||TextUtils.equals(mResultBean.type, "2")) { //特价商品特殊处理
                            showComfirmDialog1();
                        } else {
                            if (!mResultBean.productLimit) {
                                showAddDialog(0);
                            }
//                        mPresenter.addCartProduct(this, mItemId);
                        }
                    }

                }

                break;
            case R.id.rl_manjian://优惠劵
                setStoreJudge();
                ProductCouponActivity.startActivity(this, mItemId);
                break;
//            case R.id.tv_look_gift:// 查看赠品
//                if (!TextUtils.isEmpty(mGiftId)) {
//                    ProductDetailActivity.startActivity(this, mGiftId);
//                }
//                break;
            case R.id.iv_service:// 客服
                contactService();
                break;
        }
    }

    private void showAddDialog(int type) {
        if (mResultBean == null) return;

            addCartDialog = new AddCartDialog(this, mResultBean, type);
            addCartDialog.setDialogClickListener(new AddCartDialog.DialogClickListener() {
                @Override
                public void ok() {
                    if (cartCount <= 0) {
                        getShortToastByString("起购量必须大于0");
                        return;
                    }
                    if (mResultBean != null && (TextUtils.equals(mResultBean.type, "1")||TextUtils.equals(mResultBean.type, "2")) && type == 1) { //特价商品特殊处理
                        mPresenter.miaoshaBuy(ProductDetailActivity.this, mResultBean.itemid, cartCount);
                    } else {
                        mPresenter.addCartProduct(ProductDetailActivity.this, mItemId, cartCount);
                    }
                }

                @Override
                public void onAumountChangeListener(View view, int amount, boolean isEdit) {
                    L.i("amount:" + amount);
                    cartCount = amount;
                    double i = (double) amount / Double.parseDouble(mResultBean.addmum);
                    if (i % 1 != 0) {
                        NetUtil.getShortToastByString("输入商品的数量必须是起购量的倍数");
                        cartCount = (int) (DensityUtils.parseInt(mResultBean.addmum) * (int) i);
                        ((AmountView) view).setAmount(cartCount);
                    }
                }
            });
            addCartDialog.builder().show();


    }

    private long millDay = 24 * 60 * 60 * 1000;

    @Override
    public void showProductDetail(ProductDetailModel data) {
        mResultBean = data;
        if (mResultBean == null) {
            loadinglayout.setStatus(LoadingLayout.Empty);
            mRlCollect.setVisibility(View.GONE);
        } else {
            loadinglayout.setStatus(LoadingLayout.Success);
            mRlCollect.setVisibility(View.VISIBLE);
        }
        if (mResultBean != null) {
            if (data.pic != null) {
                mBanner.setImages(data.pic);
                mBanner.start();
            }
            cartCount=Integer.parseInt(mResultBean.minimum);
            mGiftId = data.giftId;
            mItemId = data.itemid;
            mCommendAdapter.setNewData(data.product);

            String type = data.type;

            if (TextUtils.equals(type, "1") || TextUtils.equals(type, "2") || TextUtils.equals(type, "3") || TextUtils.equals(type, "9")) {
                Bitmap b = null;
                if (TextUtils.equals(type, "1")) {
                    long endtime = Long.parseLong(data.endtime) * 1000;
                    long starttime = Long.parseLong(data.starttime) * 1000;
                    if (starttime > endtime) {
                        b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs_n);
                    } else {
                        b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
                    }
                }
                else if (TextUtils.equals(type, "2")) {
                    b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
                }
                else if (TextUtils.equals(type, "3")) {
                    b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
                } else if (TextUtils.equals(type, "9")) {
                    b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_kx);
                }

                CenterAlignImageSpan imgSpan = new CenterAlignImageSpan(UiUtil.getContext(), b);
                SpannableString spanString = new SpannableString("icon ");
                spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
                mTvProductName.setText(spanString);
                mTvProductName.append(data.title);
            } else {
                mTvProductName.setText(data.title);
            }
            mTvProductCompany.setText(data.scqy);
            mTvPrice.setText(data.price);
            mTvTopPrice.setText(data.price);
            if (!TextUtils.isEmpty(NetUtil.getStoreid())) {
                mTvOldprice.setText(TextUtils.equals(data.type, "9")?"":"折后约"+data.disprice);
            } else {
                mTvOldprice.setVisibility(View.GONE);
            }

            if (TextUtils.equals(data.type, "1") || TextUtils.equals(data.type, "3")) {
                mTvTopUnit.setText(data.gg);
                mTvTopOldPrice.setText(TextUtils.equals(data.type, "1")?"":"折后约"+data.disprice);
                if (TextUtils.isEmpty(NetUtil.getStoreid()))
                {
                    mTvTopOldPrice.setVisibility(View.GONE);
                }

            }



            mTvUnit.setText(data.gg);
            mTvHasSale.setText("已售" + data.sales + "件");
            tv_sales.setText("已售" + data.sales + "件");
            mTvSaleRecord.setText(data.orders);
            String couponinfo = data.couponinfo;
            if (TextUtils.isEmpty(couponinfo)) {//无优惠劵
                mRlManjian.setVisibility(View.GONE);
                ll_coupon.setVisibility(View.GONE);
            } else {
                mRlManjian.setVisibility(View.VISIBLE);
                ll_coupon.setVisibility(View.VISIBLE);
                tv_coupon_info.setText(couponinfo);
            }
            mWebview.loadDataWithBaseURL(null, data.content, "text/html", "utf-8", null);

            mQuehuo = mResultBean.quehuo;
            if (mQuehuo) {
                mTvAddCart.setText("售罄（调货通知）");
                mTvAddCart.setSelected(true);
            } else {
                if (mResultBean.productLimit) {
                    mTvAddCart.setText("已达限购");
                    mTvAddCart.setSelected(false);
                    mTvAddCart.setEnabled(false);
                }
            }

            mTvPresale.setVisibility(TextUtils.equals(data.presale, "1") ? View.VISIBLE : View.GONE);
            mTvPresale.setText(data.presalenote);
            iv_is_collect.setImageResource(data.issave ? R.drawable.scantmbj : R.drawable.xqyscan);

            mTvTongyong.setText(data.title);
            mTvPizhun.setText(data.pzwh);
            mTvGuige.setText(data.gg);
            mTvChangjia.setText(data.scqy);

            mTvRiqi.setText(DensityUtils.getDayMothDate(DensityUtils.parseLong(data.birthtime) * 1000));
            if (TextUtils.isEmpty(data.validtime))
            {
                mTvYouxiao.setVisibility(View.GONE);
            }
            else
            {
                mTvYouxiao.setText(DensityUtils.getDayMothDate(DensityUtils.parseLong(data.validtime) * 1000));
            }


            if (TextUtils.equals(data.type, "1")) {

                if (mQuehuo) {
                    mTvAddCart.setText("售罄（调货通知）");
                    mTvAddCart.setSelected(true);
                } else {
                    if (mResultBean.productLimit&&mResultBean.flashLimit) {
                        mTvAddCart.setEnabled(false);
                        mTvAddCart.setSelected(false);
                        mTvAddCart.setText("已达限购");
                    } else {
                        mTvAddCart.setEnabled(true);
                        mTvAddCart.setSelected(false);
                        mTvAddCart.setText("加入购物车");
                    }
                }

                mRlInfo.setVisibility(View.VISIBLE);
                mRlInfo.setBackgroundResource(R.drawable.icon_flash_buy_detail_bg);
                mIvProductType.setBackgroundResource(R.drawable.icon_flash_buy_detail);
                mRlCountDown.setVisibility(View.VISIBLE);
                ll_price.setVisibility(View.GONE);
                rl_gg.setVisibility(View.GONE);
                tv_sales.setVisibility(View.VISIBLE);
                llMaizeng.setVisibility(View.GONE);

                //倒计时
                long endtime = Long.parseLong(data.endtime) * 1000;
                long starttime = Long.parseLong(data.starttime) * 1000;
                long currentTimeMillis = System.currentTimeMillis();
                mIsStart = starttime > currentTimeMillis;
                mTvTimeState.setText(mIsStart?"距开始:":"距结束");
                long countdown = endtime - currentTimeMillis;
                if (countdown >= millDay) {//结束时间减去现在时间大于一天
                    mCvCountdownViewDay.setVisibility(View.VISIBLE);
                    mCvCountdownView.setVisibility(View.GONE);
                    mCvCountdownViewDay.start(countdown);
                } else {
                    mCvCountdownViewDay.setVisibility(View.GONE);
                    mCvCountdownView.setVisibility(View.VISIBLE);
                    mCvCountdownView.start(countdown);
                }

                mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                    @Override
                    public void onEnd(CountdownView cv) {
                        mPresenter.loadProductDetail(ProductDetailActivity.this, mItemId);
                    }
                });
                mCvCountdownViewDay.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                    @Override
                    public void onEnd(CountdownView cv) {
                        mPresenter.loadProductDetail(ProductDetailActivity.this, mItemId);
                    }
                });

                int max = DensityUtils.parseInt(data.salesacti);
                int progress = DensityUtils.parseInt(data.sale);
                progress_bar.setMax(max);
                progress_bar.setProgress(progress);

                int pressent = (int) ((float) progress / max * 100);
                tv_product_progress.setText(pressent + "%");
            }else if (TextUtils.equals(data.type, "2"))
            {
                if (mQuehuo) {
                    mTvAddCart.setText("售罄（调货通知）");
                    mTvAddCart.setSelected(true);
                } else {
                    if (mResultBean.productLimit&&mResultBean.flashLimit) {
                        mTvAddCart.setEnabled(false);
                        mTvAddCart.setSelected(false);
                        mTvAddCart.setText("已达限购");
                    } else {
                        mTvAddCart.setEnabled(true);
                        mTvAddCart.setSelected(false);
                        mTvAddCart.setText("加入购物车");
                    }
                }
                mRlInfo.setVisibility(View.GONE);
                mRlCountDown.setVisibility(View.GONE);
                llMaizeng.setVisibility(View.GONE);
                ll_price.setVisibility(View.VISIBLE);
                rl_gg.setVisibility(View.VISIBLE);
                tv_sales.setVisibility(View.GONE);
                mTvOldprice.setVisibility(View.GONE);
            }
            else if (TextUtils.equals(data.type, "3")) {
                mRlInfo.setVisibility(View.VISIBLE);
                mRlInfo.setBackgroundResource(R.drawable.icon_full_give_detail_bg);
                mIvProductType.setBackgroundResource(R.drawable.icon_full_give_detail);
                mRlCountDown.setVisibility(View.GONE);
                llMaizeng.setVisibility(View.VISIBLE);
                ll_price.setVisibility(View.GONE);
                rl_gg.setVisibility(View.GONE);
                tv_sales.setVisibility(View.VISIBLE);
            }
            else {
                mRlInfo.setVisibility(View.GONE);
                mRlCountDown.setVisibility(View.GONE);
                llMaizeng.setVisibility(View.GONE);
                ll_price.setVisibility(View.VISIBLE);
                rl_gg.setVisibility(View.VISIBLE);
                tv_sales.setVisibility(View.GONE);
            }

            if (TextUtils.isEmpty(data.levelnote)) {
                ll_levelnote.setVisibility(View.GONE);
                mTvLevelnote.setVisibility(View.GONE);
            } else {
                ll_levelnote.setVisibility(View.VISIBLE);
                mTvLevelnote.setVisibility(View.VISIBLE);
                mTvLevelnote.setText("");
                String levelnote = data.levelnote;
                String[] split = levelnote.split("●");
                for (String s : split) {
                    if (!TextUtils.isEmpty(s)) {
//                        SpannableStringBuilder builder = new SpannableStringBuilder("●");
//                        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#ff8f00"));
//                        builder.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                        mTvLevelnote.append(builder);
                        mTvLevelnote.append(s);//● 满1000元一天可享受10件特价商品
                    }
                }
            }
        }
    }

    /**
     * 设置购物车数量显示
     *
     * @param count
     */
    public void setCartNum(int count) {
        // 添加成功
        CartCountManage.newInstance().setCount(count);
        mTvNum.setText(count > 99 ? "99+" : "" + count);
        mTvNum.setVisibility(count == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showAddResult(AddShopCartModel data) {
        setCartNum(Integer.parseInt(data.count));
        setRefreshMax();
    }

    /**
     * 添加成功后刷新商品数量变化
     */
    private void setRefreshMax()
    {
        if (type==1)
        {
            mResultBean.flashmax =(DensityUtils.parseDouble(mResultBean.flashmax)  - cartCount)+"";
            if ((DensityUtils.parseDouble(mResultBean.flashmax)<=1))
            {
                mResultBean.flashLimit=true;
            }
        }
        else
        {
            mResultBean.max = mResultBean.max - cartCount;
            if ((mResultBean.max<=1))
            {
                mResultBean.productLimit=true;
            }
        }

        if (TextUtils.equals(mResultBean.type,"1")|TextUtils.equals(mResultBean.type,"2"))
        {
            if (mResultBean.max <= 0&&DensityUtils.parseDouble(mResultBean.flashmax)<=1) {
                mTvAddCart.setEnabled(false);
                mTvAddCart.setSelected(false);
                mTvAddCart.setText("已达限购");
            }
        }
        else
        {
            if (mResultBean.max <= 0) {
                mTvAddCart.setEnabled(false);
                mTvAddCart.setSelected(false);
                mTvAddCart.setText("已达限购");
            }
        }

    }

    @Override
    public void getShopCarNum(String count) {
        setCartNum(Integer.parseInt(count));
    }

    @Override
    public void showCollect() {
        mResultBean.issave = true;
        iv_is_collect.setImageResource(R.drawable.scantmbj);
    }

    @Override
    public void showDisCollect() {
        mResultBean.issave = false;
        iv_is_collect.setImageResource(R.drawable.xqyscan);
    }


    @Override
    public void ifFuGai() {
        showComfirmDialog2();
    }

    @Override
    public void compelete() {
        getShortToastByString("添加成功");
        mPresenter.getShopcarNum(this);
        setRefreshMax();
    }

    @Override
    public void errorView() {
        loadinglayout.setStatus(LoadingLayout.Error);
        mRlCollect.setVisibility(View.GONE);
    }

    @Override
    public void errorNetView() {
        loadinglayout.setStatus(LoadingLayout.Error);
        mRlCollect.setVisibility(View.GONE);
    }






    private void showComfirmDialog1() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this);
        confirmDialog.setTitle("温馨提示").setContent("当前商品为限时抢购商品").setcancle("原价购买").setOk(TextUtils.equals(mResultBean.type, "1")?"秒杀购买":"特价购买").builder().show();
        ;
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//第一次特价购买，不需要传是否覆盖
                confirmDialog.cancle();
                if (!mResultBean.flashLimit) {
                    type=1;
                    showAddDialog(1);
                } else {
                    getShortToastByString("购买已达上限");
                }
            }

            @Override
            public void cancle() {//原价购买(加入购物车)
                confirmDialog.cancle();
                if (!mResultBean.productLimit) {
                    type=0;
                    showAddDialog(0);
                } else {
                    getShortToastByString("购买已达上限");
                }
            }
        });
    }

    //询问是否覆盖
    private void showComfirmDialog2() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this);
        confirmDialog.setTitle("温馨提示").setContent("购物车中已有秒杀商品，是否覆盖").setcancle("否").setOk("是").builder().show();
        confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
            @Override
            public void ok() {//覆盖
                confirmDialog.cancle();
                mPresenter.miaoshaBuy(ProductDetailActivity.this, mResultBean.itemid, "1", String.valueOf(cartCount));
            }

            @Override
            public void cancle() {//不覆盖
                confirmDialog.cancle();
//                mPresenter.miaoshaBuy(ProductDetailActivity.this,mResultBean.itemid,"0",cartCount);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == LoginActivity.START_LOGIN_RESULT) {
            if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                SelectStoreUtil selectStoreUtil=new SelectStoreUtil(mContext,null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StoreManage.newInstance().clearStoreManageListener(storeManageListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
