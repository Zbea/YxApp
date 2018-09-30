package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.LoginModel;
import com.yx.Pharmacy.presenter.LoginPresenter;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.ILoginView;

import butterknife.BindView;
import butterknife.OnClick;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.activity
 *  @文件名:   LoginActivity
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 12:25
 *  @描述：    TODO
 */

public class LoginActivity
        extends BaseActivity implements ILoginView
{
    private static final int START_LOGIN_REQUEST = 729;
    public static final int START_LOGIN_RESULT = 410;
    @BindView(R.id.iv_cancel)
    ImageView mIvCancel;
    @BindView(R.id.tv_login_type)
    TextView  mTvLoginType;
    @BindView(R.id.tv_login_hint_type)
    TextView  mTvLoginHintType;
    @BindView(R.id.edit_phone_num)
    EditText  mEditPhoneNum;
    @BindView(R.id.iv_code_type)
    ImageView mIvCodeType;
    @BindView(R.id.tv_get_code)
    TextView  mTvGetCode;
    @BindView(R.id.edit_code)
    EditText  mEditCode;
    @BindView(R.id.tv_login)
    TextView  mTvLogin;
    @BindView(R.id.tv_cut)
    TextView  mTvCut;
    @BindView(R.id.tv_register)
    TextView  mTvRegister;
    @BindView(R.id.tv_find)
    TextView  mTvFind;
    private LoginPresenter mPresenter;
    private int mLoginType; // 0 : 验证码登录 1:账号密码登录
    // 验证码倒计时
    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTvGetCode.setClickable(false);
            mTvGetCode.setText("重新发送("+millisUntilFinished/1000+")秒");
        }

        @Override
        public void onFinish() {
            mTvGetCode.setClickable(true);
            mTvGetCode.setText("重新发送");
        }
    };
    private String mPhoneNum;
    private int mStartIn;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Activity context, int startin) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("start_in",startin); //0 返回首页  1哪来回哪去
        context.startActivityForResult(intent,START_LOGIN_REQUEST);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mStartIn = getIntent().getIntExtra("start_in", 0);
        mPresenter = new LoginPresenter(this);
        mLoginType = 0;
        cutLoinType();
    }

    @OnClick({R.id.iv_cancel,
              R.id.tv_get_code,
              R.id.tv_login,
              R.id.tv_cut,
              R.id.tv_register,
              R.id.tv_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                finish();
                break;
            case R.id.tv_get_code:
                sendCheckCode();
                break;
            case R.id.tv_login:
                UserLogin();
                break;
            case R.id.tv_cut:
                cutLoinType();
                break;
            case R.id.tv_register:
                RegisterActivity.startActivity(this);
                break;
            case R.id.tv_find:
                break;
        }
    }

    private void UserLogin() {
        mPhoneNum = mEditPhoneNum.getText().toString();
        String code = mEditCode.getText()
                               .toString()
                               .trim();
        if (mLoginType==0) {
            if(TextUtils.isEmpty(code)||TextUtils.isEmpty(mPhoneNum)){
                getShortToastByString("请输入正确的验证码");
                return;
            }
            mPresenter.loginCode(this,mPhoneNum,code);
        }else {
            if(TextUtils.isEmpty(code)||TextUtils.isEmpty(mPhoneNum)){
                getShortToastByString("请输入正确的密码");
                return;
            }
            mPresenter.loginPwd(this,mPhoneNum,code);
        }
    }

    /**
     * 切换登录方式
     */
    private void cutLoinType() {
        if (mLoginType == 0) {
            mLoginType=1;
            mTvLoginType.setText("账号密码登录");
            mTvLoginHintType.setText("请正确输入账号密码登录");
            mEditCode.setHint("6至16位字母/数字登录密码");
            mEditPhoneNum.setHint("手机号码/账号");
            mIvCodeType.setImageResource(R.drawable.dlmmicon);
            mTvGetCode.setVisibility(View.GONE);

            mTvCut.setText("手机验证码登录");
        }else {
            mLoginType=0;
            mTvLoginType.setText("手机验证码登录");
            mTvLoginHintType.setText("请正确输入手机号验证码登录");
            mEditCode.setHint("验证码");
            mEditPhoneNum.setHint("手机号码");
            mIvCodeType.setImageResource(R.drawable.yzmicon);
            mTvGetCode.setVisibility(View.VISIBLE);

            mTvCut.setText("账号密码登录");
        }
    }

    /**
     * 发送验证码
     */
    private void sendCheckCode() {
        mPhoneNum = mEditPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNum)|| mPhoneNum.length()<11) {
            getShortToastByString("请输入正确的手机号");
            return;
        }

        mPresenter.sendCheckCode(this, mPhoneNum, "login");
        timer.start();
    }

    @Override
    public void loginResult(LoginModel data) {
        /**
         * userid : 23
         * token : 722BE4E91D64F05F653D2679F7F1E505
         * mobile : 13267156156
         * company :
         * credit : 16
         * money : 999952.00
         * qrcode : http://www.baidu.com
         * bank : 招商银行·银联IC普卡
         * branch : 前海支行
         * account : 6214837827380561
         * truename : 何福林
         * havebank : true
         * storecertify : true
         */
        getShortToastByString("登录成功");
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_TOKEN,data.token);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_MOBILE,data.mobile);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_COMPANY,data.company);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_CREDIT,data.credit);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_MONEY,data.money);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_QRCODE,data.codeimg);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_BANK,data.bank);

        SPUtil.putString(UiUtil.getContext(), Constants.KEY_BRANCH,data.branch);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_ACCOUNT,data.account);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_TRUENAME,data.truename);
        SPUtil.putBoolean(UiUtil.getContext(), Constants.KEY_HAVEBANK,data.havebank);
        SPUtil.putBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY,data.storecertify);

//        SPUtil.putString(UiUtil.getContext(), Constants.KEY_BANK_USERNAME,"?");
        if (mStartIn==1) {
            setResult(START_LOGIN_RESULT);
        }else if (mStartIn==2) {
            MainActivity.startActivity(this,2);
        }else {
            MainActivity.startActivity(this);
        }
        finish();

    }
}
