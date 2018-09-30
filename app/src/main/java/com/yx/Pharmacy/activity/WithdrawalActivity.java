package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.yx.Pharmacy.presenter.WithdrawalPresenter;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.view.IWithdrawalView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现
 */
public class WithdrawalActivity extends BaseActivity implements IWithdrawalView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_bank)
    TextView tv_bank;
    @BindView(R.id.tv_where)
    TextView tv_where;
    @BindView(R.id.tv_card_num)
    TextView tv_card_num;
    @BindView(R.id.edit_withdrawal)
    EditText edit_withdrawal;


    @BindView(R.id.tv_my_money)
    TextView tv_my_money;
    private String money;

    private WithdrawalPresenter mPresenter;
    public static void startActivity(Activity activity,String money) {
        Intent intent = new Intent(activity, WithdrawalActivity.class);
        intent.putExtra("money",money);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        money=getIntent().getStringExtra("money");
        tv_title.setText("提现");
        tv_my_money.setText(TextUtils.isEmpty(money)?"":money);
        tv_bank.setText(!TextUtils.isEmpty(SPUtil.getString(this, Constants.KEY_BANK))?SPUtil.getString(this, Constants.KEY_BANK):"");
        tv_card_num.setText(!TextUtils.isEmpty(SPUtil.getString(this, Constants.KEY_ACCOUNT))?SPUtil.getString(this, Constants.KEY_ACCOUNT):"");
        tv_where.setText(!TextUtils.isEmpty(SPUtil.getString(this, Constants.KEY_BRANCH))?"("+SPUtil.getString(this, Constants.KEY_BRANCH)+")":"");
        edit_withdrawal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        edit_withdrawal.setText(s);
                        edit_withdrawal.setSelection(s.length());
                    }
                }
                //如果.在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    edit_withdrawal.setText(s);
                    edit_withdrawal.setSelection(2);
                }
                //如果起始位置为0并且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        edit_withdrawal.setText(s.subSequence(0, 1));
                        edit_withdrawal.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mPresenter=new WithdrawalPresenter(this);
    }

    @OnClick({R.id.rl_back,R.id.tv_commit,R.id.tv_take_all})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_commit://提现
                if(!TextUtils.isEmpty(edit_withdrawal.getText().toString().trim())){
                    mPresenter.commitWithdrawal(this,edit_withdrawal.getText().toString().trim());
                }
                break;
            case R.id.tv_take_all://全部提现
                edit_withdrawal.setText(TextUtils.isEmpty(money)?"":money);
                edit_withdrawal.setSelection(edit_withdrawal.getText().length());
                break;
        }
    }

    @Override
    public void withdrawalResult(Object data) {//提现成功返回

    }
}
