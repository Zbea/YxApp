package com.yx.Pharmacy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.model.LoginModel;
import com.yx.Pharmacy.presenter.LoginPresenter;
import com.yx.Pharmacy.view.ILoginView;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity
        extends BaseActivity implements ILoginView
{
    @BindView(R.id.tv_title)
    TextView  mTvTitle;
    @BindView(R.id.tv_more)
    TextView  mTvMore;
    @BindView(R.id.edit_phone_num)
    EditText  mEditPhoneNum;
    @BindView(R.id.edit_password)
    EditText  mEditPassword;
    @BindView(R.id.tv_get_code)
    TextView  mTvGetCode;
    @BindView(R.id.edit_code)
    EditText  mEditCode;
    @BindView(R.id.edit_tuijianma)
    EditText  mEditTuijianma;
    @BindView(R.id.tv_register)
    TextView  mTvRegister;
    private LoginPresenter mPresenter;
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

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        mPresenter = new LoginPresenter(this);
        mTvTitle.setText("注册账户");
        mTvMore.setText("有账户登录");
    }

    @OnClick({R.id.rl_back,
              R.id.tv_more,
              R.id.tv_get_code,
              R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_more:
                finish();
                break;
            case R.id.tv_get_code:
                sendCheckCode();
                break;
            case R.id.tv_register:
                registerUser();
                break;
        }
    }

    /**
     * 注册
     */
    private void registerUser() {
        String phoneNum = mEditPhoneNum.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String code = mEditCode.getText().toString().trim();
        String tuijainma = mEditTuijianma.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)||phoneNum.length()<11) {
            getShortToastByString("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(password)||password.length()<6||password.length()>16) {
            getShortToastByString("请输入6-16位的数字或字母");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            getShortToastByString("请输验证码");
            return;
        }

        mPresenter.registerUser(this,phoneNum,password,code,tuijainma);
    }

    /**
     * 发送验证码
     */
    private void sendCheckCode() {
        String phoneNum = mEditPhoneNum.getText()
                                       .toString()
                                       .trim();
        if (TextUtils.isEmpty(phoneNum)||phoneNum.length()<11) {
            getShortToastByString("请输入正确的手机号");
            return;
        }

        mPresenter.sendCheckCode(this,phoneNum,"regist");
        timer.start();
    }

    @Override
    public void loginResult(LoginModel data) {

    }
}
