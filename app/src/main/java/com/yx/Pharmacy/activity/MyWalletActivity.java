package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.WalletListAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.WalletData;
import com.yx.Pharmacy.presenter.MyWalletPresenter;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.TimeUtils;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMyWalletView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.util.ConvertUtils;

public class MyWalletActivity extends BaseActivity implements IMyWalletView {


    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.tv_my_money)
    TextView tv_my_money;
    @BindView(R.id.tv_select_ximing)
    TextView tv_select_ximing;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_nodata)
    LinearLayout ll_nodata;
    @BindView(R.id.ll_error)
    LinearLayout ll_error;

    private DatePicker mDataPicker;
    private MyWalletPresenter mPresenter;
    private WalletListAdapter mAdapter;
    private List<WalletData.WallatModel> wallatModels=new ArrayList<>();
    private int page=1;
    private String mOrganTime,mTvTime;


    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MyWalletActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void init() {
//        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        ImmersionBar mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        initView();

        initDate();

        mPresenter=new MyWalletPresenter(this);
        mPresenter.getMyIntegralData(this,page,mOrganTime);
    }

    private void initDate()
    {
        Calendar c = Calendar.getInstance();//
        mOrganTime = c.get(Calendar.YEAR)+"0"+String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mTvTime= c.get(Calendar.YEAR)+"-0"+String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        tv_select_ximing.setText(mTvTime);
    }

    private void initView() {
        tv_title.setText("我的钱包");

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        mAdapter=new WalletListAdapter(R.layout.item_my_integral,wallatModels);
        recyclerview.setAdapter(mAdapter);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {
                        initNestPage();
                    }
                }
            }
        });
    }
    private void initNestPage() {
        mPresenter.getMyIntegralData(this,page+1,mOrganTime);
    }

    /**
     * 选择时间弹窗
     */
    private void showDataDialog() {
        if (mDataPicker == null) {

            long l = System.currentTimeMillis();
            long ofter = l-311040000000L;

            mDataPicker = new DatePicker(this,DateTimePicker.YEAR_MONTH);
            mDataPicker.setCanceledOnTouchOutside(true);
            mDataPicker.setUseWeight(true);
            mDataPicker.setDividerVisible(false);
            mDataPicker.setTopPadding(ConvertUtils.toPx(this, 10));
            mDataPicker.setRangeEnd(DensityUtils.parseInt(TimeUtils.getDateYear(l)), DensityUtils.parseInt(TimeUtils.getDateMonth(l)));
            mDataPicker.setRangeStart(DensityUtils.parseInt(TimeUtils.getDateYear(ofter)), DensityUtils.parseInt(TimeUtils.getDateMonth(ofter)));
            mDataPicker.setResetWhileWheel(true);
            mDataPicker.setSelectedItem(DensityUtils.parseInt(TimeUtils.getDateYear(l)),DensityUtils.parseInt(TimeUtils.getDateMonth(l)));
            mDataPicker.setContentPadding(0, 10);
            mDataPicker.setTopPadding(20);
            mDataPicker.setLabel(null, null, null);
            mDataPicker.setTextColor(getResources().getColor(R.color.color_main));
            mDataPicker.setTextSize(18);
            mDataPicker.setContentPadding(0, 10);
            mDataPicker.setTopPadding(20);
            mDataPicker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {

                @Override
                public void onDatePicked(String year, String month) {
                    mOrganTime = year+month;
                    mTvTime= year+"-"+month;
                    tv_select_ximing.setText(mTvTime);
                    page=1;
                    mPresenter.getMyIntegralData(MyWalletActivity.this,page,mOrganTime);
                }
            });
            mDataPicker.show();
        } else {
            mDataPicker.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getMyIntegralData(this,page,mOrganTime);
    }

    @OnClick({R.id.rl_back, R.id.ll_charge_money, R.id.tv_select_ximing, R.id.ll_bank_card, R.id.ll_tixian, R.id.tv_reload})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_tixian://提现
                if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY)) {
                    WithdrawalActivity.startActivity(this, !TextUtils.isEmpty(myMoney)?myMoney:"");
                }else {
                    AddBankCardActivity.startActivity(this);
                }
                break;
            case R.id.ll_bank_card://银行卡
                if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY)) {
                    MyBankActivity.startActivity(this);
                }else {
                    AddBankCardActivity.startActivity(this);
                }
                break;
            case R.id.ll_charge_money://充值
                ChargeMoneyActivity.startActivity(this);
                break;
            case R.id.tv_select_ximing:
                showDataDialog();
                break;
            case R.id.tv_reload://重新加载
                List<WalletData.WallatModel>models=new ArrayList<>();
                mAdapter.setNewData(models);
                showNornaml();
                page=1;
                initDate();
                mPresenter.getMyIntegralData(MyWalletActivity.this,page,mOrganTime);
                break;
        }
    }
    private String myMoney;
    @Override
    public void getWalletInfo(WalletData data) {
        recyclerview.setVisibility(View.VISIBLE);
        ll_nodata.setVisibility(View.GONE);
        if(data.list!=null&&data.list.size()>0){
            mAdapter.setNewData(data.list);
        }else {
            setNoData();
        }
        tv_my_money.setText(data.money);
        myMoney=data.money;
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_MONEY,data.money);
    }
    @Override
    public void addWalletList(List<WalletData.WallatModel> data) {
        if(data!=null&&data.size()>0){
            mAdapter.addData(data);
            showNornaml();
            page++;
        }
    }

    @Override
    public void onErrorPage() {
        setErrorPage();
    }
    private void setNoData() {//无数据
        recyclerview.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.VISIBLE);
    }

    private void setErrorPage(){//错误页面
        recyclerview.setVisibility(View.GONE);
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
    }
    private void showNornaml(){//错误页面
        ll_nodata.setVisibility(View.GONE);
        ll_error.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);
    }
}
