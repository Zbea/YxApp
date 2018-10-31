package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.AskAfterSaleAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.dialog.ReasonDialog;
import com.yx.Pharmacy.model.AskModel;
import com.yx.Pharmacy.model.CommitModel;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.presenter.AskAfterSalePresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.DoubleMath;
import com.yx.Pharmacy.view.IAskAfterSaleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 申请售后
 */
public class AskForAfterSaleActivity extends BaseActivity implements IAskAfterSaleView {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.iv_all_select)
    ImageView iv_all_select;
    @BindView(R.id.tv_back_price)
    TextView tv_back_price;
    @BindView(R.id.edittext)
    EditText edittext;

    @BindView(R.id.tv_reason)
    TextView tv_reason;
    @BindView(R.id.rg_sale_after_type)
    RadioGroup rgSaleAfterType;
    @BindView(R.id.tv_can_write_num)
    TextView tvCanWriteNum;
    @BindView(R.id.et_logistics_name)
    EditText etLogisticsName;
    @BindView(R.id.et_logistics_number)
    EditText etLogisticsNumber;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;


    private AskAfterSalePresenter mPresenter;
    private String orderid;
    private String reason;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AskAfterSaleAdapter mAdapter;
    private AskModel model;
    private List<OrderModel.Goods> goods = new ArrayList<>();
    private List<String> reasons = new ArrayList<>();
    private int selectNum = 0;
    private int type=1;

    public static void startActivity(Activity activity, String orderid) {
        Intent intent = new Intent(activity, AskForAfterSaleActivity.class);
        intent.putExtra("orderid", orderid);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ask_for_after_sale;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        orderid = getIntent().getStringExtra("orderid");
        initView();
        mPresenter = new AskAfterSalePresenter(this);
        mPresenter.getAskAfterSaleData(this, orderid);
    }

    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("申请售后");

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AskAfterSaleAdapter(this, R.layout.item_ask_aftersale, goods);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setNestedScrollingEnabled(false);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.getData().get(position).isSelect = !mAdapter.getData().get(position).isSelect;
//                mAdapter.notifyItemChanged(position);
                mAdapter.notifyDataSetChanged();
                selectNum = 0;
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (mAdapter.getData().get(i).isSelect) {
                        selectNum++;
                    }
                }
                if (selectNum == mAdapter.getData().size()) {//全选
                    iv_all_select.setImageResource(R.drawable.jizhu_check);
                } else {
                    iv_all_select.setImageResource(R.drawable.jizhu_uncheck);
                }
                calculateTotal();
            }
        });
        mAdapter.setListener(new AskAfterSaleAdapter.ChangeListener() {
            @Override
            public void countChange() {
                calculateTotal();
            }
        });

        rgSaleAfterType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rb_tkth)
                {
                    type=1;
                }
                if (checkedId==R.id.rb_jtk)
                {
                    type=2;
                }
            }
        });

        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String str=s.toString();
                tvCanWriteNum.setText("您还可以输入"+(100-str.length())+"字");
            }
        });

    }

    //计算总价
    private double total = 0;

    public void calculateTotal() {
        total = 0;
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            if (mAdapter.getData().get(i).isSelect) {
                total = DoubleMath.add(total, Double.valueOf(mAdapter.getData().get(i).count));
            }
        }
        tv_back_price.setText(DensityUtils.doubleToString(total));
    }

    @OnClick({R.id.rl_back, R.id.ll_all_select, R.id.tv_commit, R.id.ll_reason})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_reason://选择原因
                ReasonDialog dialog = new ReasonDialog(this, reasons);
                dialog.builder().show();
                dialog.setDialogClickListener(new ReasonDialog.DialogClickListener() {
                    @Override
                    public void reason(String rea) {
                        reason = rea;
                        tv_reason.setText(reason);
                    }
                });
                break;
            case R.id.ll_all_select://点击全选
                if (selectNum == mAdapter.getData().size()) {//当前为全选，点击反选
                    for (int i = 0; i < mAdapter.getData().size(); i++) {
                        mAdapter.getData().get(i).isSelect = false;
                    }
                    mAdapter.notifyDataSetChanged();
                    iv_all_select.setImageResource(R.drawable.jizhu_uncheck);
                    selectNum = 0;
                } else {
                    for (int i = 0; i < mAdapter.getData().size(); i++) {//全选
                        mAdapter.getData().get(i).isSelect = true;
                    }
                    mAdapter.notifyDataSetChanged();
                    iv_all_select.setImageResource(R.drawable.jizhu_check);
                    selectNum = mAdapter.getData().size();
                }
                calculateTotal();
                break;
            case R.id.tv_commit://提交
                String note = edittext.getText().toString().trim();
                String logisticsNma=etLogisticsName.getText().toString().trim();
                String logisticsNumber=etLogisticsNumber.getText().toString().trim();
                if (TextUtils.isEmpty(note)) {
                    getShortToastByString("请填写申请说明");
                    return;
                }
                if (selectNum == 0) {
                    getShortToastByString("请选择商品");
                    return;
                }
                if (TextUtils.isEmpty(reason)) {
                    getShortToastByString("请选择申请原因");
                    return;
                }
                int count = 0;
                List<CommitModel> commitModels = new ArrayList<>();
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (mAdapter.getData().get(i).isSelect) {
                        commitModels.add(new CommitModel(mAdapter.getData().get(i).mallid, mAdapter.getData().get(i).count));
                        count = count + mAdapter.getData().get(i).count;
                    }
                }
                Gson gson = new Gson();
                String goodList = gson.toJson(commitModels);
                mPresenter.commitAfterSale(AskForAfterSaleActivity.this, orderid, reason, note, DensityUtils.doubleToString(total), goodList, count,type,logisticsNma,logisticsNumber);
                break;
        }
    }

    @Override
    public void getData(AskModel data) {
        if (data != null) {
            reasons = data.reason;
            tv_num.setText("总数量" + data.count);
            mAdapter.setNewData(data.goodsList);
        }

    }

    @Override
    public void commitBack(String orderbackid) {//提交成功
        finish();
        AfterOrderDetailActivity.startActivity(this, orderbackid);
    }

}
