package com.yx.Pharmacy.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.PayOrderModel;
import com.yx.Pharmacy.model.PayResult;
import com.yx.Pharmacy.presenter.ChargeMoneyPresenter;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.StackManager;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IChargeMoneyView;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yx.Pharmacy.base.YxApp.mWxApi;

/**
 * 充值
 */
public class ChargeMoneyActivity extends BaseActivity implements IChargeMoneyView {

    @BindView(R.id.tv_title)
    TextView  tv_title;
    @BindView(R.id.iv_select_wechat_pay)
    ImageView iv_select_wechat_pay;
    @BindView(R.id.iv_select_alipay)
    ImageView iv_select_alipay;
    @BindView(R.id.edit_search)
    EditText  edit_search;
    private int curPay=1;
    private static  final int PAY_WECHAT=1;
    private static  final int PAY_ALIPAY=2;
    private ChargeMoneyPresenter mPresenter;
    private String mAliPayLink;
    private String mMoney;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ChargeMoneyActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_charge_money;
    }

    @Override
    protected void init() {
        StackManager.getManagerStack().pushActivity(this);
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("充值");
        mPresenter = new ChargeMoneyPresenter(this);
        initView();
    }

    private void initView() {
        curPay=PAY_WECHAT;
        iv_select_wechat_pay.setVisibility(View.VISIBLE);
        iv_select_alipay.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        StackManager.getManagerStack().popActivity(this);
    }

    @OnClick({R.id.rl_back,R.id.tv_to_chongzhi,R.id.rl_wechat_pay,R.id.rl_alipay})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_wechat_pay:
                curPay=PAY_WECHAT;
                iv_select_wechat_pay.setVisibility(View.VISIBLE);
                iv_select_alipay.setVisibility(View.GONE);
                break;
            case R.id.rl_alipay:
                curPay=PAY_ALIPAY;
                iv_select_wechat_pay.setVisibility(View.GONE);
                iv_select_alipay.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_to_chongzhi://去充值
                chongzhi();
                break;
        }
    }

    private void chongzhi() {
        mMoney = edit_search.getText().toString().trim();
        if (TextUtils.isEmpty(mMoney)) {
            getShortToastByString("请输入充值金额");
            return;
        }

        if (Double.parseDouble(mMoney)<100)
        {
            getShortToastByString("充值金额需大于100");
            return;
        }

        mPresenter.chargeWallte(this, mMoney);
    }

    @Override
    public void showPay(PayOrderModel data, String alertmsg) {
        if(curPay==PAY_WECHAT){//微信充值
            payWechat(data);
        }else if(curPay==PAY_ALIPAY){//支付宝充值
            payAliPay(data.pay_code);
        }
    }

    @Override
    public void showCreate(Boolean data, String extention, String alertmsg) {
        if (data) {
            if(curPay==PAY_WECHAT){//微信充值
                mPresenter.payOrder(this,"wechat",extention,mMoney);
            }else if(curPay==PAY_ALIPAY){//支付宝充值
                mPresenter.payOrder(this,"alipay",extention,mMoney);
            }
        }else {
            getShortToastByString(alertmsg);
        }
    }


    private void payAliPay(String pay_code) {
        mAliPayLink = pay_code;
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    Runnable payRunnable = new Runnable() {
        @Override
        public void run() {
            PayTask             alipay          = new PayTask(ChargeMoneyActivity.this);
            Map<String, String> stringStringMap = alipay.payV2(mAliPayLink, true);
            LogUtils.e("ALIPAY_LINK: "+mAliPayLink);
            Message msg = new Message();
            msg.what = SDK_PAY_FLAG;
            msg.obj = stringStringMap;
            mHandler.sendMessage(msg);
        }
    };

    private void payWechat(PayOrderModel data) {
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_ORDER_NUMBER, "");
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

    private static final int SDK_PAY_FLAG = 65;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtils.e( "ALIPAY_STATUS: "+resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        getShortToastByString("支付成功");
                        finish();
                    }else{
                        getShortToastByString("支付失败");
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
