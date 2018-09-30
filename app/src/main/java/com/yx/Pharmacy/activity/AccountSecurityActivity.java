package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.util.ComMethodsUtil;
import com.yx.Pharmacy.util.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 账户安全
 */
public class AccountSecurityActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_phone_num)
    TextView tv_phone_num;

    private String phone;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AccountSecurityActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_security;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("账户安全");

        phone= SPUtil.getString(this, Constants.KEY_MOBILE);
        tv_phone_num.setText(ComMethodsUtil.phoneFormat(phone));
    }
    @OnClick({R.id.rl_back,R.id.rl_phone,R.id.rl_password})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_phone:
                ChangePhoneActivity.startActivity(this);
                break;
            case R.id.rl_password:
                ChangePasswordActivity.startActivity(this);
                break;
        }
    }
}
