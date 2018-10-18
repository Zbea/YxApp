package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.MyShopAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.HomeAdDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.model.AddressModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.StoreDetailModel;
import com.yx.Pharmacy.model.StoreTypeModel;
import com.yx.Pharmacy.model.UploadModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.MyShopPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMyShopView;
import com.yx.Pharmacy.widget.LoadingLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   MyShopActivity
 *  @创建者:   CC
 *  @创建时间:  2018/7/22 16:06
 *  @描述：    TODO
 */

public class MyShopActivity
        extends BaseActivity implements IMyShopView
{

    @BindView(R.id.iv_back)
    ImageView      mIvBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView       mTvTitle;
    @BindView(R.id.rl_scan)
    RelativeLayout mRlScan;
    @BindView(R.id.rv_shop)
    RecyclerView   mRvShop;
    @BindView(R.id.ll_add_shop)
    LinearLayout   mLlAddShop;
    @BindView(R.id.loadinglayout)
    LoadingLayout  mLoadingLayout;
    private MyShopPresenter mPresenter;
    private MyShopAdapter mAdapter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyShopActivity.class);
        activity.startActivity(intent);
    }
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyShopActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_shop;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mTvTitle.setText("我的门店");
        mPresenter = new MyShopPresenter(this);

        EventBus.getDefault().register(this);

        mLoadingLayout.setEmptyImage(R.drawable.zwtjmdwk);
        mLoadingLayout.setEmptyText("暂无添加门店");
        mLoadingLayout.setEmptyReloadBtnVisible(false);

        // 初始化列表 RecyclerView
        initRecycler();
        initData();
    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvShop.setLayoutManager(manager);
        mAdapter = new MyShopAdapter(R.layout.item_my_store);
        mRvShop.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyShopModel myShopModel = mAdapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.tv_cut:
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_ITEM_ID, myShopModel.itemid);
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORE_ID,myShopModel.storeid);
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORENAME,myShopModel.storename);
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_ADDRESS,myShopModel.storeaddress);
                        CartCountManage.newInstance().refresh(Integer.parseInt(myShopModel.carcount));
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_COLLECT,myShopModel.collectcount);
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_AVATAR,myShopModel.avatar);
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_MOBILE,myShopModel.mobile);
                        SPUtil.putString(UiUtil.getContext(), Constants.KEY_TRUENAME,myShopModel.truename);
                        getShortToastByString("切换门店成功");
                        finish();
                        break;
                    case R.id.tv_modifi:
                        MyShopAddActivity.startActivity(MyShopActivity.this,myShopModel.itemid);
                        break;
                }
            }
        });
    }

    private void initData() {
        mPresenter.loadMyShop(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    @OnClick({R.id.rl_back,R.id.rl_scan,
              R.id.ll_add_shop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_add_shop:
                MyShopAddActivity.startActivity(this);
                break;
            case R.id.rl_scan:
                YSFUserInfo userInfo = new YSFUserInfo();
                String title = "";
                if (TextUtils.isEmpty(NetUtil.getToken())){
                    title = "游客"+ DensityUtils.getRandomString(16);
                    userInfo.userId = title;
                }else {
                    if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                        title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE);
                    }else {
                        title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
                    }
                    userInfo.userId = NetUtil.getToken();
                }
                userInfo.data = "[{\"key\":\"real_name\", \"value\":"+title+"}]";
                Unicorn.setUserInfo(userInfo);
                ConsultSource source = new ConsultSource("我的门店", title, "custom information string");
                Unicorn.openServiceActivity(this, "源鑫药业", source);
                break;
        }
    }

    @Override
    public void showShopData(List<MyShopModel> data) {
        if(data!=null&&data.size()>0){
            mAdapter.setNewData(data);
            mAdapter.loadMoreEnd();
            mLoadingLayout.setStatus(LoadingLayout.Success);
        }else {
            mLoadingLayout.setStatus(LoadingLayout.Empty);
        }
    }

    @Override
    public void showStoreType(List<StoreTypeModel> data) {

    }

    @Override
    public void showAddressList(List<AddressModel> data) {

    }

    @Override
    public void showUploadResult(UploadModel data) {

    }

    @Override
    public void showStoreDetail(StoreDetailModel data) {

    }

    @Override
    public void showAdvanceData(HomeAdvanceModel data) {

        if (data!=null)
            showAdDiaog(data.custom);

    }
    /**
     * 显示广告弹框
     * @param alert
     */
    private void showAdDiaog(HomeAdvanceModel.GoldBean alert){
        HomeAdDialog homeAdDialog=new HomeAdDialog(this,alert);
        homeAdDialog.builder().show();
        homeAdDialog.setDialogClickListener(new HomeAdDialog.DialogClickListener() {
            @Override
            public void clickAd() {
                switch (alert.pushtype){
                    case 1://app跳转活动模块，参数weburl
                        HHActivity.startActivity(mContext,alert.weburl);
                        break;
                    case 2://app跳转商品详情，参数goodsid
                        ProductDetailActivity.startActivity(mContext,alert.goodsid);
                        break;
                    case 3://如果当前用户登录并且没有认证门店，跳转到门店认证页，不需要参数
                        if (TextUtils.isEmpty(NetUtil.getToken())) {
                            LoginActivity.startActivity(mContext);
                        }else {
                            if(SPUtil.getBoolean(mContext, Constants.KEY_STORE_CERTIFY)){
                                MyShopActivity.startActivity(mContext);
                            }else {
                                MyShopAddActivity.startActivity(MyShopActivity.this);
                            }
                        }
                        break;
                    case 4://app携带关键字跳转至搜索页，参数keyword(需要查找商品的关键字)
                        SearchActivity.startActivity(mContext,alert.keyword);
                        break;
                    case 5://跳转到其他专区的活动，参数activityname（活动名册）levelid（活动id）type（活动的类型）
                        String type = alert.type;
                        if (TextUtils.equals(type, "1")) {
                            // 秒杀
                            ComendMsActivity.startActivity(mContext,alert.levelid);
                        } else if (TextUtils.equals(type, "2")) {
                            // 特价
                            CommendTjActivity.startActivity(mContext, type, alert.levelid);
                        } else if (TextUtils.equals(type, "3")) {
                            // 满减
                            CommendProductActivity.startActivity(mContext, type, alert.levelid);
                        } else if (TextUtils.equals(type, "9")) {
                            // 控销
                            CommendProductActivity.startActivity(mContext, type, alert.levelid);
                        }
                        break;
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s)
    {
        mPresenter.getAdvanceData(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
