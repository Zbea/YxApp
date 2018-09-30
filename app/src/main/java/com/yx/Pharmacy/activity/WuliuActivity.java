package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.WuliuAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.model.WuliuData;
import com.yx.Pharmacy.presenter.WuliuPresenter;
import com.yx.Pharmacy.view.IWuliuView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WuliuActivity extends BaseActivity implements IWuliuView {
    @BindView(R.id.tv_title)
    TextView tv_title;
    private String orderid;
    private WuliuPresenter mPresenter;

    @BindView(R.id.recyclerView_wuliu)
    RecyclerView recyclerView_wuliu;
    private WuliuAdapter wuliuAdapter;


    @BindView(R.id.tv_wuliu_right1)
    TextView tv_wuliu_right1;
    @BindView(R.id.tv_wuliu_right2)
    TextView tv_wuliu_right2;
    @BindView(R.id.tv_wuliu_right3)
    TextView tv_wuliu_right3;
    private List<WuliuData.WuliModel>models=new ArrayList<>();


    public static void startActivity(Activity activity, String orderid) {
        Intent intent = new Intent(activity, WuliuActivity.class);
        intent.putExtra("orderid",orderid);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wuliu;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        orderid=getIntent().getStringExtra("orderid");
        Log.e("kid","orderid===="+orderid);
        initView();
    }

    private void initView() {
        tv_title.setText("查看物流");

        recyclerView_wuliu.setLayoutManager(new LinearLayoutManager(this));
        wuliuAdapter=new WuliuAdapter(this,R.layout.item_wuliu,models);
        recyclerView_wuliu.setAdapter(wuliuAdapter);
        recyclerView_wuliu.setNestedScrollingEnabled(false);

        mPresenter=new WuliuPresenter(this);
        mPresenter.getWuliuData(this,orderid);
    }

    @Override
    public void getWuliuResult(WuliuData data) {
        if(data!=null){
            tv_wuliu_right1.setText(data.translatetel);
            tv_wuliu_right2.setText(data.send_type);
            tv_wuliu_right3.setText(data.send_no);
            if(data.translatedetail!=null&&data.translatedetail.size()>0){
                wuliuAdapter.setNewData(data.translatedetail);
            }
        }
    }

    @OnClick({R.id.rl_back})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }
}
