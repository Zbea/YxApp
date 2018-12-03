package com.yx.Pharmacy.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.AboutActivity;
import com.yx.Pharmacy.activity.AccountSecurityActivity;
import com.yx.Pharmacy.activity.AfterSaleActivity;
import com.yx.Pharmacy.activity.HaveNeedActivity;
import com.yx.Pharmacy.activity.LoginActivity;
import com.yx.Pharmacy.activity.MyCollectActivity;
import com.yx.Pharmacy.activity.MyCouponActivity;
import com.yx.Pharmacy.activity.MyIntegralActivity;
import com.yx.Pharmacy.activity.MyOrderListActivity;
import com.yx.Pharmacy.activity.MyQrCodeActivity;
import com.yx.Pharmacy.activity.MyShopActivity;
import com.yx.Pharmacy.activity.MyWalletActivity;
import com.yx.Pharmacy.activity.OrderActivity;
import com.yx.Pharmacy.activity.QiyeZizhiActivity;
import com.yx.Pharmacy.activity.SettingActivity;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BaseFragment;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.manage.LocalUrlManage;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.model.UrlBean;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.presenter.MyPresenter;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMyOrderNumView;
import com.yx.Pharmacy.widget.RoundImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by KID on 2018/7/14.
 */

public class MyFragment extends BaseFragment implements IMyOrderNumView {

    @BindView(R.id.tv_collect_num)
    TextView tv_collect_num;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_my_integral)
    TextView tv_my_integral;
    @BindView(R.id.rl_user_head)
    RoundImageView rl_user_head;
    @BindView(R.id.tv_pay_num)
    TextView tvPayNum;
    @BindView(R.id.tv_send_num)
    TextView tvSendNum;
    @BindView(R.id.tv_recieve_num)
    TextView tvRecieveNum;
    @BindView(R.id.tv_done_num)
    TextView tvDoneNum;
    @BindView(R.id.tv_after_num)
    TextView tvAfterNum;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.tv_my_coupon)
    TextView tvMyCoupon;
    Unbinder unbinder;
    private String mAvatar;
    private boolean mIsFirstLoad = true;
    private String mStoreid;
    private MyPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_my;
    }

    private ImmersionBar mImmersionBar;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
        {
            mImmersionBar.init();
            initView();
        }
    }

    @Override
    protected void init() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        initView();
    }

    @OnClick({R.id.rl_setting, R.id.ll_feedback, R.id.rl_signin, R.id.rl_my_order, R.id.rl_user_head, R.id.ll_my_store,
            R.id.ll_my_integral, R.id.ll_my_wallet, R.id.ll_my_collect, R.id.ll_my_erweima, R.id.ll_qiyezizhi,
            R.id.ll_my_coupons, R.id.ll_waitto_pay, R.id.ll_waitto_send, R.id.ll_waitto_receive, R.id.ll_youjiang_task, R.id.tv_user_name,
            R.id.ll_completed, R.id.ll_after_sales, R.id.ll_have_need, R.id.ll_about, R.id.ll_accout_security})
    public void onclick(View v) {

        UrlBean urlBean=LocalUrlManage.newInstance().getUrlBean();

        switch (v.getId()) {
            case R.id.rl_setting://设置
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                SettingActivity.startActivity(mContext);
                break;
            case R.id.ll_feedback:// 退换政策
                if (urlBean!=null)
                {
                    HHActivity.startActivity(mContext, Constants.BASE_URL+LocalUrlManage.newInstance().getUrlBean().exchange,1);
                }
//                else
//                {
//                    HHActivity.startActivity(mContext, Constants.WEB_EXCHANGE, 1);
//                }

                break;
            case R.id.ll_youjiang_task:// 有奖任务
                if (urlBean!=null)
                {
                    HHActivity.startActivity(mContext, Constants.BASE_URL+LocalUrlManage.newInstance().getUrlBean().prizetask);
                }
//                else
//                {
//                    HHActivity.startActivity(mContext, Constants.WEB_PRIZETASK);
//                }
                break;
            case R.id.rl_signin://签到---->改成消息按钮
                ((MainActivity) mContext).showFragment(1);
                break;
            case R.id.rl_my_order://我的订单
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                MyOrderListActivity.startActivity(mContext);
                break;
            case R.id.rl_user_head:// 我的门店
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity((MainActivity) mContext, 2);
                    return;
                }
                MyShopActivity.startActivity(mContext);
                break;
            case R.id.tv_user_name:// name
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity((MainActivity) mContext, 2);
                    return;
                }
                MyShopActivity.startActivity(mContext);
                break;
            case R.id.ll_my_store:// 我的门店
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity((MainActivity) mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MyShopActivity.startActivity(mContext);
                    return;
                }
                MyShopActivity.startActivity(mContext);
                break;
            case R.id.ll_my_integral://我的积分
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MyShopActivity.startActivity(mContext);
                    return;
                }
                MyIntegralActivity.startActivity(mContext);
                break;
            case R.id.ll_my_wallet://我的钱包
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                MyWalletActivity.startActivity(mContext);
                break;
            case R.id.ll_my_collect://我的收藏
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                MyCollectActivity.startActivity(mContext);
                break;
            case R.id.ll_my_erweima://我的二维码
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                MyQrCodeActivity.startActivity(mContext);
                break;
            case R.id.ll_have_need://我有需求
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                HaveNeedActivity.startActivity(mContext);
                break;
            case R.id.ll_accout_security://账户安全
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                AccountSecurityActivity.startActivity(mContext);
                break;
            case R.id.ll_qiyezizhi://企业资质
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                QiyeZizhiActivity.startActivity(mContext);
                break;
            case R.id.ll_about:
                AboutActivity.startActivity(mContext);
                break;
            case R.id.ll_my_coupons://优惠劵
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                MyCouponActivity.startActivity(mContext);
                break;
            case R.id.ll_waitto_pay://待支付
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                goOrder(0);
                break;
            case R.id.ll_waitto_send://待发货
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                goOrder(1);
                break;
            case R.id.ll_waitto_receive://待收货
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                goOrder(2);
                break;
            case R.id.ll_completed://已完成
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                goOrder(3);
                break;
            case R.id.ll_after_sales://售后
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext, 2);
                    return;
                }
                if (TextUtils.isEmpty(NetUtil.getStoreid())) {
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMyShop();
                    return;
                }
                AfterSaleActivity.startActivity(mContext);
                break;
        }
    }

    private void goOrder(int curpage) {
        OrderActivity.startActivity(mContext, curpage);
    }


    public void initView() {
        presenter=new MyPresenter(this);
        if (!TextUtils.isEmpty(NetUtil.getToken())&!TextUtils.isEmpty(NetUtil.getStoreid()))
        {
            presenter.getOrderNum((BaseActivity) mContext);
        }
        else
        {
            setLogoutView();
        }

        if (TextUtils.isEmpty(NetUtil.getToken())) {
            tv_user_name.setText("登录/注册");
        } else {
            String truename = SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
            tv_user_name.setText(TextUtils.isEmpty(truename) ? SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE) : truename);
        }

        if (TextUtils.isEmpty(mAvatar)) {
            mAvatar = SPUtil.getString(UiUtil.getContext(), Constants.KEY_AVATAR);
            if (TextUtils.isEmpty(mAvatar)) {
                rl_user_head.setImageResource(R.drawable.icon_logo);
            } else {
                GlideUtil.loadRoundImg(mContext, mAvatar, rl_user_head, R.drawable.icon_logo);
            }
        } else {
            if (!TextUtils.equals(mAvatar, SPUtil.getString(UiUtil.getContext(), Constants.KEY_AVATAR))) {
                mAvatar = SPUtil.getString(UiUtil.getContext(), Constants.KEY_AVATAR);
                if (TextUtils.isEmpty(mAvatar)) {
                    rl_user_head.setImageResource(R.drawable.icon_logo);
                } else {
                    GlideUtil.loadRoundImg(mContext, mAvatar, rl_user_head, R.drawable.icon_logo);
                }
            }
        }

        if (mIsFirstLoad) {
            mIsFirstLoad = false;
            mStoreid = NetUtil.getStoreid();
        } else {
            if (!TextUtils.equals(mStoreid, NetUtil.getStoreid())) {// 切换门店账号  刷新五个tab数据
                mStoreid = NetUtil.getStoreid();
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.notifyData();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void setLogoutView()
    {
        setOrderNum(tvPayNum,0);
        setOrderNum(tvSendNum,0);
        setOrderNum(tvRecieveNum,0);
        setOrderNum(tvDoneNum,0);
        setOrderNum(tvAfterNum,0);
        tv_collect_num.setText(""+0);
        tv_my_integral.setText(""+0);
        tvMyCoupon.setText(""+0);
        tvMyMoney.setText(""+0);
    }

    @Override
    public void resultCartNum(MyOrderNumModel data) {
        if (data!=null)
        {
            setOrderNum(tvPayNum,data.noPay);
            setOrderNum(tvSendNum,data.noSend);
            setOrderNum(tvRecieveNum,data.waitRecieve);
            setOrderNum(tvDoneNum,data.done);
            setOrderNum(tvAfterNum,data.orderBack);
            tv_collect_num.setText(""+data.collectCount);
            tv_my_integral.setText(""+data.score);
            tvMyCoupon.setText(""+data.coupon);
            tvMyMoney.setText(""+data.money);
            SPUtil.putInt(mContext,Constants.KEY_TRANSFER_MONEY,data.isPublic);
        }
    }

    private void setOrderNum(TextView tv,int num)
    {
        tv.setText(""+num);
        tv.setVisibility(num>0?View.VISIBLE:View.GONE);
    }

}
