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
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.presenter.ChangePhonePresenter;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.view.IChangePwdView;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity implements IChangePwdView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.edit_old_phone_num)
    EditText editOldPhoneNum;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_new_phone_num)
    EditText editNewPhoneNum;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.edit_code)
    EditText editCode;

    private ChangePhonePresenter mPresenter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, ChangePhoneActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void init() {
        initView();
        mPresenter=new ChangePhonePresenter(this);
    }

    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("修改手机号码");
    }

    @OnClick({R.id.rl_back,R.id.tv_commit,R.id.tv_get_code})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                timer.cancel();
                finish();
                break;
            case R.id.tv_commit://提交
                ConfirmDialog confirmDialog=new ConfirmDialog(this);
                confirmDialog.setTitle("修改操作确认!").setContent("是否修改新手机号码?").setOk("确认").setcancle("取消");
                confirmDialog.builder().show();
                confirmDialog.setDialogClickListener(new ConfirmDialog.DialogClickListener() {
                    @Override
                    public void ok() {
                        confirmDialog.cancle();
                        commitChangePhone();
                    }

                    @Override
                    public void cancle() {

                    }
                });
                break;
            case R.id.tv_get_code://发送验证码
                sendCheckCode();
                break;

        }
    }

    /**
     * 发送验证码
     */
    private void sendCheckCode() {
        String mPhoneNum = editNewPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNum)|| mPhoneNum.length()<11) {
            getShortToastByString("请输入正确的手机号");
            return;
        }
        mPresenter.sendCheckCode(this, mPhoneNum, "updatemobile");
        timer.start();
    }
    // 验证码倒计时
    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setClickable(false);
            tvGetCode.setText(millisUntilFinished/1000+"S");
        }

        @Override
        public void onFinish() {
            tvGetCode.setClickable(true);
            tvGetCode.setText("重新发送");
        }
    };


    @Override
    public void getChangePwdResult(Object data) {
        loginOut();
        LoginActivity.startActivity(this);
        finish();
    }

    private void commitChangePhone() {
        String oldphone=editOldPhoneNum.getText().toString().trim();
        String psw=editPassword.getText().toString().trim();
        String newPhone=editNewPhoneNum.getText().toString().trim();
        String smscode=editCode.getText().toString().trim();
        if(TextUtils.isEmpty(oldphone)){
            getShortToastByString("请输入原手机号码");
            return;
        }
        if(TextUtils.isEmpty(psw)){
            getShortToastByString("请输入密码");
            return;
        }
        if(TextUtils.isEmpty(newPhone)){
            getShortToastByString("请输入新手机号码");
            return;
        }
        if(TextUtils.isEmpty(smscode)){
            getShortToastByString("请输入验证码");
            return;
        }
        mPresenter.changePhone(this,oldphone,psw,newPhone,smscode);
    }


    private void loginOut() {
        SPUtil.delete(this, Constants.KEY_TOKEN);
        SPUtil.delete(this, Constants.KEY_STORE_ID);
        SPUtil.delete(this, Constants.KEY_ITEM_ID);
        SPUtil.delete(this, Constants.KEY_STORENAME);
        SPUtil.delete(this, Constants.KEY_ADDRESS);
        SPUtil.delete(this, Constants.KEY_AVATAR);
        SPUtil.delete(this, Constants.KEY_TRUENAME);
        SPUtil.delete(this, Constants.KEY_MOBILE);
        SPUtil.delete(this, Constants.KEY_TOKEN);
        SPUtil.delete(this, Constants.KEY_MOBILE);
        SPUtil.delete(this, Constants.KEY_COMPANY);
        SPUtil.delete(this, Constants.KEY_MONEY);
        SPUtil.delete(this, Constants.KEY_QRCODE);
        SPUtil.delete(this, Constants.KEY_BANK);
        SPUtil.delete(this, Constants.KEY_BRANCH);
        SPUtil.delete(this, Constants.KEY_ACCOUNT);
        SPUtil.delete(this, Constants.KEY_TRUENAME);
        SPUtil.delete(this, Constants.KEY_HAVEBANK);
        SPUtil.delete(this, Constants.KEY_STORE_CERTIFY);
        CartCountManage.newInstance().setCount(0);
    }
}
