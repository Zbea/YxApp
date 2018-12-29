package com.yx.Pharmacy.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.model.PayOrderModel;
import com.yx.Pharmacy.model.PayResult;
import com.yx.Pharmacy.model.PayWayModel;
import com.yx.Pharmacy.presenter.PayPresenter;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.StackManager;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IPayView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
//import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.yx.Pharmacy.base.YxApp.mWxApi;

public class PayActivity extends BaseActivity implements IPayView {
    private static final int SDK_PAY_FLAG = 65;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_select_wechat_pay)
    ImageView iv_select_wechat_pay;
    @BindView(R.id.iv_select_alipay)
    ImageView iv_select_alipay;
    @BindView(R.id.iv_select_public_pay)
    ImageView iv_select_public_pay;
    @BindView(R.id.iv_select_another_pay)
    ImageView iv_select_another_pay;
    @BindView(R.id.rl_public_pay)
    RelativeLayout rlPublicPay;
    @BindView(R.id.rl_another_pay)
    RelativeLayout rlAnotherPay;
    private int curPay = 1;
    private static final int PAY_WECHAT = 1;
    private static final int PAY_ALIPAY = 2;
    private static final int PAY_PUBLIC = 3;
    private static final int PAY_ANOTHER = 4;
    private String mAliPayLink;
    private String mNeedpay;
    private String mOrdernum;
    private PayPresenter mPresenter;
    private PayWayModel payWayModel;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, PayActivity.class);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, String needpay, String ordernum) {
        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra("needpay", needpay);
        intent.putExtra("ordernum", ordernum);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void init() {
        StackManager.getManagerStack().pushActivity(this);
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mPresenter = new PayPresenter(this);
        mPresenter.getPay(this);
        tv_title.setText("选择支付方式");
        iv_select_wechat_pay.setVisibility(View.VISIBLE);

        mNeedpay = getIntent().getStringExtra("needpay");
        mOrdernum = getIntent().getStringExtra("ordernum");
    }

    @OnClick({R.id.rl_back, R.id.tv_to_pay, R.id.rl_wechat_pay, R.id.rl_alipay, R.id.rl_public_pay, R.id.rl_another_pay})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                StackManager.getManagerStack().popAllActivityExceptOne();
                finish();
                break;
            case R.id.rl_wechat_pay:
                curPay = PAY_WECHAT;
                iv_select_wechat_pay.setVisibility(View.VISIBLE);
                iv_select_alipay.setVisibility(View.GONE);
                iv_select_public_pay.setVisibility(View.GONE);
                iv_select_another_pay.setVisibility(View.GONE);
                break;
            case R.id.rl_alipay:
                curPay = PAY_ALIPAY;
                iv_select_wechat_pay.setVisibility(View.GONE);
                iv_select_alipay.setVisibility(View.VISIBLE);
                iv_select_public_pay.setVisibility(View.GONE);
                iv_select_another_pay.setVisibility(View.GONE);
                break;
            case R.id.rl_public_pay:
                curPay = PAY_PUBLIC;
                iv_select_wechat_pay.setVisibility(View.GONE);
                iv_select_alipay.setVisibility(View.GONE);
                iv_select_public_pay.setVisibility(View.VISIBLE);
                iv_select_another_pay.setVisibility(View.GONE);
                break;
            case R.id.rl_another_pay:
                curPay = PAY_ANOTHER;
                iv_select_wechat_pay.setVisibility(View.GONE);
                iv_select_alipay.setVisibility(View.GONE);
                iv_select_public_pay.setVisibility(View.GONE);
                iv_select_another_pay.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_to_pay://去付款
                if (curPay == PAY_ANOTHER) {
                    showPay();
                    return;
                }
                mPresenter.payOrder(this, curPay == PAY_WECHAT ? "wechat" : (curPay == PAY_ALIPAY ? "alipay" : "transfer"), mOrdernum, mNeedpay);
                break;
        }
    }

    private void showPay() {
//        if (payWayModel != null) {
//            OnekeyShare oks = new OnekeyShare();
//            //关闭sso授权
//            oks.disableSSOWhenAuthorize();
//            // title标题，微信、QQ和QQ空间等平台使用
//            oks.setTitle(payWayModel.shareTitle);
//            // titleUrl QQ和QQ空间跳转链接
//            oks.setTitleUrl(payWayModel.otherPayUrl);
//            // text是分享文本，所有平台都需要这个字段
//            oks.setText(payWayModel.shareContent);
//            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//            oks.setImagePath(getResourcesUri(R.drawable.icon_logo));//确保SDcard下面存在此张图片
//            // url在微信、微博，Facebook等平台中使用
//            oks.setUrl(payWayModel.otherPayUrl);
//            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//            oks.setComment("我是测试评论文本");
//            // site是分享此内容的网站名称，仅在QQ空间使用
//            oks.setSite(mContext.getString(R.string.app_name));
//            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//            oks.setSiteUrl(payWayModel.otherPayUrl);
//            // 启动分享GUI
//            oks.show(this);
//
//
//        }

//
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
//        String path = getResourcesUri(R.drawable.icon_logo);
//        intent.setType("image/jpg");
//        Uri u = Uri.parse(path);
//        intent.putExtra(Intent.EXTRA_STREAM, u);
        intent.putExtra(Intent.EXTRA_SUBJECT, payWayModel==null?"他人代付":payWayModel.shareTitle);
        intent.putExtra(Intent.EXTRA_TEXT, payWayModel==null?"他人代付":payWayModel.shareTitle+":"+payWayModel.otherPayUrl+"."+payWayModel.shareContent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent,payWayModel==null?"他人代付":payWayModel.shareTitle));
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }


    private void payBank() {
        OrderDetailActivity.startActivity(PayActivity.this, mOrdernum);
        finish();
    }

    private void payAliPay(String pay_code) {
        mAliPayLink = pay_code;
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    Runnable payRunnable = new Runnable() {
        @Override
        public void run() {
            PayTask alipay = new PayTask(PayActivity.this);
            Map<String, String> stringStringMap = alipay.payV2(mAliPayLink, true);
            LogUtils.e("ALIPAY_LINK: " + mAliPayLink);
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = stringStringMap;
            mHandler.sendMessage(msg);
        }
    };

    private void payWechat(PayOrderModel data) {
        if (data != null) {
            SPUtil.putString(UiUtil.getContext(), Constants.KEY_ORDER_NUMBER, mOrdernum);
            PayReq request = new PayReq();
            request.appId = data.appid;
            request.partnerId = data.partnerid;
            request.prepayId = data.prepayid;
            request.packageValue = data.packageX;
            request.nonceStr = data.noncestr;
            request.timeStamp = data.timestamp;
            request.sign = data.sign;
            mWxApi.sendReq(request);
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtils.e("ALIPAY_STATUS: " + resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        getShortToastByString("支付成功");
                        OrderDetailActivity.startActivity(PayActivity.this, 1, mOrdernum);
                        StackManager.getManagerStack().popAllActivityExceptOne();
                    } else {
                        getShortToastByString("支付失败");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void showPay(PayOrderModel data, String alertmsg) {
        if (curPay == PAY_WECHAT) {//微信支付
            payWechat(data);
        } else if (curPay == PAY_ALIPAY) {//支付宝
            payAliPay(data.pay_code);
        } else {//对公转账
            getShortToastByString(alertmsg);
            payBank();
        }
    }

    @Override
    public void resultCartNum(MyOrderNumModel data) {
        if (data != null) {
            if (data.isPublic == 1) {
                rlPublicPay.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void resultPayWay(PayWayModel data) {
        if (data != null) {
            payWayModel = data;
            if (data.isPublic == 1) {
                rlPublicPay.setVisibility(View.VISIBLE);
            }
            if (data.otherPay == 1) {
                rlAnotherPay.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        StackManager.getManagerStack().popActivity(this);
    }


}
