package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;
    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, FeedBackActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("意见反馈");
        tv_more.setVisibility(View.VISIBLE);
        tv_more.setText("暂无回复");
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
