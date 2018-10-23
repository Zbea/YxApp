package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yx.Pharmacy.MainActivity;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.adapter.GuidePageAdapter;
import com.yx.Pharmacy.barlibrary.ImmersionBar;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.SplashData;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    private int picRes[]=new int[]{R.drawable.splash1, R.drawable.splash2, R.drawable.splash3,R.drawable.splash4};
    @BindView(R.id.iv_splash)
    ImageView iv_splash;
    @BindView(R.id.vp_guide)
    ViewPager vp_guide;
    @BindView(R.id.enter_home_page_bottom_layout)
    LinearLayout enter_home_page_bottom_layout;
    @BindView(R.id.tv_downcount)
    TextView tv_downcount;
    @BindView(R.id.rl_downcount)
    RelativeLayout rl_downcount;

    private static final Long enterDelayed=1500L;
    private static final long one_s_Later=1000L;
    private static final int MESSAGE_1s_LATER=1001;
    private GuidePageAdapter guidePageAdapter;

    private boolean canClick=false;
    private static  final int CODE_WEB_BACK=1002;
    private int curTime=3;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_1s_LATER:
                    curTime--;
                    tv_downcount.setText(curTime+"跳过");
                    if(curTime==0){
                        enterMainActivity();
                    }else {
                        mHandler.sendEmptyMessageDelayed(MESSAGE_1s_LATER,one_s_Later);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };


    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SplashActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        ImmersionBar.with(this).transparentStatusBar().init();
        initView();
    }

    private void initView() {
        if(SPUtil.getBoolean(this, Constants.hasEnterApp,false)){
            iv_splash.setVisibility(View.VISIBLE);
            vp_guide.setVisibility(View.GONE);
            canClick=false;
            String json=SPUtil.getString(this,Constants.KEY_AD,"");
            if(!TextUtils.isEmpty(json)){//有广告
                Gson gson=new Gson();
                SplashData.SplashModel splashModel=gson.fromJson(json,new TypeToken<SplashData.SplashModel>(){}.getType());
                GlideUtil.loadImgFit(this,splashModel.image_src,iv_splash,R.drawable.splash2);
                iv_splash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SplashActivity.this, HHActivity.class);
                        intent.putExtra(Constants.H5_URL,splashModel.weburl);
                        SplashActivity.this.startActivityForResult(intent,CODE_WEB_BACK);
                        mHandler.removeMessages(MESSAGE_1s_LATER);
                        SPUtil.putLong(SplashActivity.this,Constants.KEY_LAST_CLICK_SP_AD,System.currentTimeMillis());
                    }
                });
                rl_downcount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterMainActivity();
                    }
                });
                //开启倒计时
                rl_downcount.setVisibility(View.VISIBLE);
                tv_downcount.setText(curTime+"跳过");
                mHandler.sendEmptyMessageDelayed(MESSAGE_1s_LATER,one_s_Later);
            }else {
                iv_splash.setImageResource(R.drawable.splash_icon);
                iv_splash.setVisibility(View.VISIBLE);
                iv_splash.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enterMainActivity();
                    }
                }, enterDelayed);
            }
        }else {
            canClick=true;
            SPUtil.putBoolean(this, Constants.hasEnterApp,true);
            initViewPager();
        }
    }

    private void initViewPager() {
        vp_guide.setVisibility(View.VISIBLE);
        iv_splash.setVisibility(View.GONE);
        guidePageAdapter=new GuidePageAdapter(picRes);
        vp_guide.setAdapter(guidePageAdapter);
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @OnClick({R.id.enter_home_page_bottom_layout})
    public void click(View v){
        switch (v.getId()){
            case R.id.enter_home_page_bottom_layout:
                if(canClick&&vp_guide.getCurrentItem()==picRes.length-1){//最后一张
                    enterMainActivity();
                }
                break;
        }
    }

    private void enterMainActivity(){
        mHandler.removeMessages(MESSAGE_1s_LATER);
        startActivity(new Intent(SplashActivity.this,MainActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CODE_WEB_BACK){
           enterMainActivity();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        mHandler.removeMessages(MESSAGE_1s_LATER);
        finish();
    }
}
