package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.util.DataCleanManager;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("关于源鑫");
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
