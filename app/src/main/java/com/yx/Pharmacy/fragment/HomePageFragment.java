package com.yx.Pharmacy.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.CaptureActivity;
import com.yx.Pharmacy.activity.CommendMsActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.CommendTjActivity;
import com.yx.Pharmacy.activity.LoginActivity;
import com.yx.Pharmacy.activity.MyShopAddActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.activity.SearchActivity;
import com.yx.Pharmacy.adapter.BannerViewHolder;
import com.yx.Pharmacy.adapter.GridDividerItemDecoration;
import com.yx.Pharmacy.adapter.HomeProductAdapter;
import com.yx.Pharmacy.adapter.HomeProductBottomAdapter;
import com.yx.Pharmacy.adapter.HomeWebAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ChooseStoreDialog;
import com.yx.Pharmacy.dialog.HomeAdDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.manage.LocalUrlManage;
import com.yx.Pharmacy.manage.StoreManage;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.UrlBean;
import com.yx.Pharmacy.presenter.HomeDataPresenter;
import com.yx.Pharmacy.util.ComMethodsUtil;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.SelectStoreUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IHomeView;
import com.yx.Pharmacy.widget.MarqueeView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by KID on 2018/7/14.
 */

public class HomePageFragment
        extends BaseFragment
        implements IHomeView {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    //    @BindView(R.id.viewpager)
    //    ViewPager        mViewpager;
//    @BindView(R.id.bannerView)
//    BannerView       bannerView;
    @BindView(R.id.banner)
    MZBannerView mMZBanner;
    @BindView(R.id.iv_banner_bg)
    ImageView iv_banner_bg;

    @BindView(R.id.rv_webview)
    RecyclerView mRvWebview;
    @BindView(R.id.rv_good)
    RecyclerView mRvGood;
    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.iv_advence)
    ImageView mIvAdvence;
    @BindView(R.id.nsv_home)
    NestedScrollView mNsvHome;
    @BindView(R.id.tv_no_more)
    TextView tv_no_more;
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.ll_store_logout)
    LinearLayout llStoreLogout;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.tv_factory_address)
    TextView tvFactoryAddress;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.iv_top)
    ImageView ivTop;

    private HomeDataPresenter mPresenter;
    private HomeWebAdapter mWebAdapter;
    private HomeProductAdapter mAdapter;

    private int fadingHeight = 500; // 当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private Drawable drawable; // 顶部渐变布局需设置的Drawable
    private static final int START_ALPHA = 0;//scrollview滑动开始位置
    private static final int END_ALPHA = 255;//scrollview滑动结束位置
    private HomeAdvanceModel.GoldBean mAdvenceGold;
    private HomeProductBottomAdapter mBottomAdapter;
    private List<DrugModel> mAddData;
    private boolean mFirstLoadMore = true;
    private boolean mIsShowAdDialog = false;
    private List<HomeDataModel> homeDataModels;
    private HomeAdvanceModel homeAdvanceModel;
    private HomeAdvanceModel messageModel;

    private Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1)
            {
                mAdapter.setNewData(homeDataModels);
            }
            if (msg.what==2)
            {
                if (homeAdvanceModel.guid != null && homeAdvanceModel.guid.size() > 0) {
                    mWebAdapter.setNewData(homeAdvanceModel.guid);
                }

                mAdvenceGold = homeAdvanceModel.gold;
                if (mAdvenceGold != null) {
                    GlideUtil.loadImgFit(UiUtil.getContext(), mAdvenceGold.image_src, mIvAdvence,R.drawable.icon_image_loading_cc);
                }
                //初始化banner数据
                if (homeAdvanceModel.banner != null && homeAdvanceModel.banner.size() > 0) {
                    initBanner(homeAdvanceModel.banner);
                }

                if (homeAdvanceModel.alert != null) {
                    if (!ComMethodsUtil.isSameDay(SPUtil.getLong(mContext, Constants.KEY_LAST_CLICK_SP_AD), System.currentTimeMillis())) {//不是同一天
                        if (!mIsShowAdDialog) {
                            showHomeAdDiaog(homeAdvanceModel.alert);
                            mIsShowAdDialog = true;
                        }
//                SPUtil.putLong(mContext,Constants.KEY_LAST_CLICK_SP_AD,System.currentTimeMillis());
                    }
                }

            }
            if (msg.what==3)
            {
                List<String> info=new ArrayList<>();
                List<HomeAdvanceModel.GoldBean> datas=messageModel.message;
                for (int i = 0; i <datas.size() ; i++) {
                    info.add(datas.get(i).title);
                }
                if (info!=null&&info.size()>0) {
                    marqueeView.setVisibility(View.VISIBLE);
                    List<CharSequence> list = new ArrayList<>();
                    for (String s : info) {
                        if (!TextUtils.isEmpty(s)) {
                            SpannableString ss = new SpannableString(s);
                            list.add(ss);
                        }
                    }
                    marqueeView.startWithList(list);
                    marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, TextView textView) {
                            messageClick(datas.get(position));
                        }
                    });
                }else {
                    marqueeView.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.frag_home_page;
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
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false)
                .addViewSupportTransformColor(mMZBanner)
                .init();
        mPresenter = new HomeDataPresenter(this);
        StoreManage.newInstance().setStoreManageListener(new StoreManage.StoreManageListener() {
            @Override
            public void onRefresh(MyShopModel data) {
//                tv_no_more.setVisibility(View.GONE);
                mFirstLoadMore = true;
                initData();
            }
        });

        // 初始化列表 RecyclerView
        initRecycler();
        initListener();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
//        mPresenter.loadMyShop((BaseActivity) mContext,false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    private void initListener() {
        mLlToolbar.getBackground().mutate().setAlpha(START_ALPHA);
        mNsvHome.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // 上拉刷新实现
                    if (mFirstLoadMore) {
                        mBottomAdapter.notifyLoadMoreToLoading();
                        mPresenter.moreProduct(mContext);
                    } else {
                        if (mAddData != null && mAddData.size() >= Constants.PAGESIZE) {
                            //                        getShortToastByString("已经到最后啦~");
                            mPresenter.moreProduct(mContext);
                        }
                    }

                }
                L.i("scrollY:"+scrollY);
                ivTop.setVisibility(scrollY>=400?View.VISIBLE:View.GONE);
                if (scrollY > fadingHeight) {
                    scrollY = fadingHeight; // 当滑动到指定位置之后设置颜色为纯色，之前的话要渐变---实现下面的公式即可
                } else if (scrollY < 0) {
                    scrollY = 0;
                }
                mLlToolbar.getBackground().mutate().setAlpha(scrollY * END_ALPHA / fadingHeight);
            }
        });
    }

    private void initRecycler() {
        // 广告列表
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 5);
        mRvWebview.setLayoutManager(layoutManager);
        mWebAdapter = new HomeWebAdapter(R.layout.item_home_web);
        mRvWebview.setAdapter(mWebAdapter);

        mWebAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeAdvanceModel.GoldBean goldBean = mWebAdapter.getData().get(position);
                gotoClick(goldBean);
            }
        });

        // 商品列表
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRvGood.setLayoutManager(manager);
        mAdapter = new HomeProductAdapter(R.layout.item_home_product);
        mRvGood.setAdapter(mAdapter);
        mRvGood.setItemViewCacheSize(20);
        mRvGood.setDrawingCacheEnabled(true);
        mRvGood.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        GridLayoutManager productManager = new GridLayoutManager(mContext, 2);
        mRvProduct.setLayoutManager(productManager);

        mBottomAdapter = new HomeProductBottomAdapter(R.layout.item_home_product_special);
        mRvProduct.setAdapter(mBottomAdapter);
        mRvProduct.addItemDecoration(new GridDividerItemDecoration(UiUtil.getContext()));

        mRvGood.setNestedScrollingEnabled(false);
        mRvWebview.setNestedScrollingEnabled(false);
        mRvProduct.setNestedScrollingEnabled(false);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeDataModel homeDataModel = mAdapter.getData()
                        .get(position);
                String type = homeDataModel.type;
                switch (view.getId()) {
                    case R.id.iv_title:
                        if (TextUtils.equals(type, "1")) {
                            // 秒杀
                            CommendMsActivity.startActivity(mContext, homeDataModel.levelid, homeDataModel.activityname);
                        } else if (TextUtils.equals(type, "2")) {
                            // 特价
                            CommendTjActivity.startActivity(mContext, type, homeDataModel.levelid, homeDataModel.activityname);
                        } else if (TextUtils.equals(type, "3")) {
                            // 满减
                            CommendProductActivity.startActivity(mContext, type, homeDataModel.levelid, homeDataModel.activityname);
                        } else if (TextUtils.equals(type, "9")) {
                            // 控销
                            CommendProductActivity.startActivity(mContext, type, homeDataModel.levelid, homeDataModel.activityname);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        mAdapter.setOnProdutcClick(new HomeProductAdapter.onProdutcClick() {
            @Override
            public void onClick(HomeDataModel.GoodlistsBean o) {
                ProductDetailActivity.startActivity(mContext, o.itemid);
            }

            @Override
            public void onEnd() {
                mPresenter.getHomeData((BaseActivity) mContext);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tv_no_more.setVisibility(View.GONE);
                mFirstLoadMore = true;
                initData();
            }
        });
        mBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DrugModel drugModel = mBottomAdapter.getData()
                        .get(position);
                ProductDetailActivity.startActivity(mContext, String.valueOf(drugModel.getItemid()));
            }
        });

    }


    public void initData() {
        if (TextUtils.isEmpty(StoreManage.newInstance().getStore().storeid))
        {
            llStore.setVisibility(View.GONE);
            llStoreLogout.setVisibility(View.VISIBLE);
        }
        else
        {
            llStore.setVisibility(View.VISIBLE);
            llStoreLogout.setVisibility(View.GONE);
            tvShop.setText(StoreManage.newInstance().getStore().storename);
        }
        tvFactoryAddress.setText(StoreManage.newInstance().getStore().company);
        mPresenter.loadProductList((BaseActivity) mContext);
        mPresenter.getAdvanceData((BaseActivity) mContext);
        mPresenter.getMessageData((BaseActivity) mContext);
        mPresenter.getHomeData((BaseActivity) mContext);
    }


    @Override
    public void showHomeData(List<HomeDataModel> data) {
        if (data.size() > 0) {
            homeDataModels=data;
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void showAdvanceData(HomeAdvanceModel data) {

        if (data!=null)
        {
            homeAdvanceModel=data;
            mHandler.sendEmptyMessage(2);
        }

    }

    /**
     * 显示广告弹框
     *
     * @param alert
     */
    private void showHomeAdDiaog(HomeAdvanceModel.GoldBean alert) {
        HomeAdDialog homeAdDialog = new HomeAdDialog(mContext, alert);
        homeAdDialog.builder().show();
        homeAdDialog.setDialogClickListener(new HomeAdDialog.DialogClickListener() {
            @Override
            public void clickAd() {
                gotoClick(alert);
            }
        });
    }


    @Override
    public void showProductListResult(List<DrugModel> data) {
        mBottomAdapter.setNewData(data);
        if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addProductListResult(List<DrugModel> data) {
        mBottomAdapter.loadMoreComplete();
        mFirstLoadMore = false;
        mAddData = data;
        if (mAddData != null && mAddData.size() > 0) {
            mBottomAdapter.addData(data);
            if (mAddData.size() < Constants.PAGESIZE) {
                tv_no_more.setVisibility(View.VISIBLE);
            }
        } else {
            tv_no_more.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessageListResult(HomeAdvanceModel data) {
        if (data!=null)
        {
            messageModel=data;
            mHandler.sendEmptyMessage(3);
        }

    }


    @Override
    public void hideFlash() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @OnClick({R.id.iv_qrcode,
            R.id.ll_search,
            R.id.iv_sign,
            R.id.iv_service,
            R.id.iv_advence,
            R.id.ll_store_logout,
            R.id.ll_store,
            R.id.tv_factory_address,
            R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_qrcode:
                //打开二维码扫描界面
//                if(CommonUtil.isCameraCanUse()){
                CaptureActivity.startActivity(mContext);
//                }else{
//                    Toast.makeText(mContext,"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
//                    ConfirmDialog confirmDialog =new ConfirmDialog(mContext);
//                    confirmDialog.setTitle("请打开此应用的摄像头权限").setContent("立即前往?").setOk("设置").setcancle("取消");
//                    confirmDialog.builder().show();
//                    confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
//                        @Override
//                        public void ok() {
//                            Intent intent = new Intent(
//                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            intent.setData(Uri.parse("package:" + mContext.getPackageName()));
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void cancle() {
//
//                        }
//                    });
//                }
                break;
            case R.id.ll_search:
                SearchActivity.startActivity(mContext, "");
                break;
            case R.id.iv_sign:
                HHActivity.startActivity(mContext, StoreManage.newInstance().getStore().sign);
                break;
            case R.id.iv_service:
                contactService();
                break;
            case R.id.iv_advence:
                if (mAdvenceGold != null) {
                    gotoClick(mAdvenceGold);
                }
                break;
            case R.id.ll_store_logout:
                if (TextUtils.isEmpty(SPUtil.getString(mContext, Constants.KEY_TOKEN)))
                {
                    LoginActivity.startActivity(mContext,2);
                    return;
                }
                if (!SPUtil.getBoolean(mContext, Constants.KEY_STORE_CERTIFY))
                {
                    MyShopAddActivity.startActivity(mContext);
                }
                else
                {
                    SelectStoreUtil selectStoreUtil=new SelectStoreUtil(mContext, new SelectStoreUtil.OnSelectStoreListener() {
                        @Override
                        public void onSelect(MyShopModel myShopModel) {
                            tvShop.setText(myShopModel.storename);
                            tvFactoryAddress.setText(TextUtils.isEmpty(myShopModel.company)?"深圳市源鑫药业有限公司":myShopModel.company);
                        }
                    });
                }
                break;
            case R.id.ll_store:
                SelectStoreUtil selectStoreUtil=new SelectStoreUtil(mContext, new SelectStoreUtil.OnSelectStoreListener() {
                    @Override
                    public void onSelect(MyShopModel myShopModel) {
                        tvShop.setText(myShopModel.storename);
                        tvFactoryAddress.setText(TextUtils.isEmpty(myShopModel.company)?"深圳市源鑫药业有限公司":myShopModel.company);
                    }
                });
                break;
            case R.id.tv_factory_address:

                break;
            case R.id.iv_top:
                mNsvHome.fling(0);
                mNsvHome.smoothScrollTo(0, 0);
                break;
        }
    }

    //--------------------Banner--start--------------------------------------
    List<HomeAdvanceModel.GoldBean> homeBanner = new ArrayList<>();

    private void initBanner(List<HomeAdvanceModel.GoldBean> banner) {
        homeBanner = banner;
        // 设置数据
        mMZBanner.setPages(homeBanner, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.setIndicatorVisible(false);
        mMZBanner.start();
        //点击事件冲突，故在BannerViewHolder里做
        mMZBanner.addPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (homeBanner.size() > 0)
                    GlideUtil.loadImg(mContext, homeBanner.get(position).image_src, iv_banner_bg);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (homeBanner.size() > 0)
            GlideUtil.loadImg(mContext, homeBanner.get(0).image_src, iv_banner_bg);
    }


    //--------------------Banner--end--------------------------------------

}
