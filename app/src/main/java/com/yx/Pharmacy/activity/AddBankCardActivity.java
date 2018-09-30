package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.presenter.AddBankCardPresenter;
import com.yx.Pharmacy.util.BankUtil;
import com.yx.Pharmacy.util.ComMethodsUtil;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.view.IAddBankCardView;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBankCardActivity
        extends BaseActivity implements IAddBankCardView
{

    @BindView(R.id.tv_title)
    TextView       tv_title;
    @BindView(R.id.edit_phone)
    EditText       edit_phone;
    @BindView(R.id.edit_name)
    EditText       edit_name;
    @BindView(R.id.edit_card_code)
    EditText       edit_card_code;
    @BindView(R.id.tv_kaihuhang)
    TextView       tv_kaihuhang;
    @BindView(R.id.edit_wangdian)
    EditText       edit_wangdian;
    @BindView(R.id.tv_get_code)
    TextView       tv_get_code;
    @BindView(R.id.edit_code)
    EditText       edit_code;
    @BindView(R.id.tv_commit)
    TextView       tv_commit;
    private String               phone;
    private AddBankCardPresenter mPresenter;
    // 验证码倒计时
    CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_code.setClickable(false);
            tv_get_code.setText(millisUntilFinished/1000+"S");
        }

        @Override
        public void onFinish() {
            tv_get_code.setClickable(true);
            tv_get_code.setText("重新发送");
        }
    };
    private String mKaiHuHang;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AddBankCardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        initView();
        mPresenter = new AddBankCardPresenter(this);
    }

    private void initView() {
        tv_title.setText("添加银行卡");
        phone = SPUtil.getString(this, Constants.KEY_MOBILE);
        edit_phone.setText(ComMethodsUtil.phoneFormat(phone));

        edit_card_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String code = edit_card_code.getText().toString().trim();
                mKaiHuHang = BankUtil.getname(code);
                if (TextUtils.isEmpty(mKaiHuHang)) {
                    tv_kaihuhang.setText("未识别出开户行");
                    tv_kaihuhang.setTextColor(Color.parseColor("#c8c8c8"));
                }else {
                    tv_kaihuhang.setText(BankUtil.getname(code));
                    tv_kaihuhang.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
    }

    @OnClick({R.id.rl_back,R.id.tv_get_code,
              R.id.tv_commit})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                if(timer!=null)timer.cancel();
                break;
            case R.id.tv_commit:
                commit();
                break;
            case R.id.tv_get_code:
                sendCheckCode();
                break;
        }
    }

    private void commit() {
        String name = edit_name.getText().toString().trim();
        String cardcode = edit_card_code.getText().toString().trim();
        String wangdian = edit_wangdian.getText().toString().trim();
        String code = edit_code.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            getShortToastByString("请输入开户名");
            return;
        }
        if (TextUtils.isEmpty(cardcode)) {
            getShortToastByString("请输入银行卡号");
            return;
        }
        if (TextUtils.isEmpty(wangdian)) {
            getShortToastByString("请输入开户网点");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            getShortToastByString("请输入验证码");
            return;
        }

        mPresenter.addBankCard(this,name,cardcode,mKaiHuHang,wangdian,code);
    }

    /**
     * 发送验证码
     */
    private void sendCheckCode() {
        mPresenter.sendCheckCode(this, phone, "addbankcard");
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null)timer.cancel();
    }
}
