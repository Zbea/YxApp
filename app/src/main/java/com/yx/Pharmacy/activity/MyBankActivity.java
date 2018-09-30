package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.util.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBankActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_bank)
    TextView tv_bank;
    @BindView(R.id.tv_card_num)
    TextView tv_card_num;
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyBankActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_bank;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("我的银行卡");
        tv_bank.setText(!TextUtils.isEmpty(SPUtil.getString(this, Constants.KEY_BANK))?SPUtil.getString(this, Constants.KEY_BANK):"");
        tv_card_num.setText(!TextUtils.isEmpty(SPUtil.getString(this, Constants.KEY_ACCOUNT))?SPUtil.getString(this, Constants.KEY_ACCOUNT):"");
    }

    @OnClick({R.id.rl_back,R.id.cardView})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.cardView:
                ChangeBankCardActivity.startActivity(this);
                break;
        }
    }
}
