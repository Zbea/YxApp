package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ConfirmDialog;
import com.yx.Pharmacy.presenter.ChangePwdPresenter;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.view.IChangePwdView;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity implements IChangePwdView {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.edit_old_password)
    EditText editOldPassword;
    @BindView(R.id.edit_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_phone_num)
    EditText editPhoneNum;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;


    private ChangePwdPresenter mPresenter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ChangePasswordActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void init() {
        initView();
        mPresenter=new ChangePwdPresenter(this);
    }

    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("修改登录密码");
    }

    @OnClick({R.id.rl_back,R.id.tv_commit,R.id.tv_get_code})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                timer.cancel();
                finish();
                break;
            case R.id.tv_get_code://获取验证码
                sendCheckCode();
                break;
            case R.id.tv_commit://提交
                ConfirmDialog confirmDialog=new ConfirmDialog(this);
                confirmDialog.setTitle("修改操作确认!").setContent("是否修改登录密码?").setOk("确认").setcancle("取消");
                confirmDialog.builder().show();
                confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
                    @Override
                    public void ok() {
                        confirmDialog.cancle();
                        commitChangePwd();
                    }

                    @Override
                    public void cancle() {

                    }
                });
                break;

        }
    }
    /**
     * 发送验证码
     */
    private void sendCheckCode() {
        String mPhoneNum = editPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNum)|| mPhoneNum.length()<11) {
            getShortToastByString("请输入正确的手机号");
            return;
        }
        mPresenter.sendCheckCode(this, mPhoneNum, "updatepsw");
        timer.start();
    }
    // 验证码倒计时
    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setClickable(false);
            tvGetCode.setBackgroundResource(R.drawable.shape_gay_bg);
            tvGetCode.setText("重新发送("+millisUntilFinished/1000+")秒");
        }

        @Override
        public void onFinish() {
            tvGetCode.setClickable(true);
            tvGetCode.setBackgroundResource(R.drawable.shape_btn_main_bg_radiu);
            tvGetCode.setText("重新发送");
        }
    };
    /**
     * 提交修改密码请求
     */
    private void commitChangePwd() {
        String psw=editOldPassword.getText().toString().trim();
        String newpsw=editNewPassword.getText().toString().trim();
        String mobile=editPhoneNum.getText().toString().trim();
        String smscode=editCode.getText().toString().trim();
        if(TextUtils.isEmpty(psw)){
            getShortToastByString("请输入原密码");
            return;
        }
        if(TextUtils.isEmpty(newpsw)){
            getShortToastByString("请输入新密码");
            return;
        }
        if(TextUtils.isEmpty(mobile)){
            getShortToastByString("请输入手机号码");
            return;
        }
        if(!mobile.equals(SPUtil.getString(this, Constants.KEY_MOBILE))){
            getShortToastByString("请输入正确的手机号码");
        }
        if(TextUtils.isEmpty(smscode)){
            getShortToastByString("请输入验证码");
            return;
        }
        mPresenter.changePwd(this,psw,newpsw,mobile,smscode);
    }


    @Override
    public void getChangePwdResult(Object data) {

    }

}
