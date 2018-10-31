package com.yx.Pharmacy.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.barlibrary.ImmersionBarUtil;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ComDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.util.DataCleanManager;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_more)
    TextView tv_more;
    @BindView(R.id.tv_cache)
    TextView tv_cache;
    private ComDialog mDialog;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        ImmersionBarUtil.setBarColor(R.color.white, this, true);
        tv_title.setText("设置");
        try {
            tv_cache.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.rl_back,R.id.rl_clean_cache,R.id.tv_login_out,
//            R.id.rl_user_info,
            R.id.rl_evaluation,R.id.rl_about})
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_clean_cache://清除缓存
                DataCleanManager.clearAllCache(SettingActivity.this);
                tv_cache.setText("0KB");
                getShortToastByString("缓存已清空");
                break;
            case R.id.rl_evaluation://联系我们
                showPhone();
                break;
            case R.id.rl_about://关于我们
                AboutActivity.startActivity(this);
                break;
            case R.id.tv_login_out://退出
                loginOut();
                break;
//            case R.id.rl_user_info://个人资料
//                UserInfoActivity.startActivity(this);
//                break;
        }
    }

    private void showPhone() {

        if (mDialog==null)
        {
            mDialog=new ComDialog(SettingActivity.this);
            mDialog.setContent("0755-290134870")
                    .setTitleView(false)
                    .setCancle("取消").setOk("拨打").builder().show();
            mDialog.setDialogClickListener(new ComDialog.DialogClickListener() {
                @Override
                public void cancle() {
                    mDialog.cancle();
                }

                @Override
                public void ok() {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + "0755-290134870");
                    intent.setData(data);
                    startActivity(intent);
                    mDialog.cancle();
                }
            });
        }
        else
        {
            mDialog.show();
        }

    }

    private void loginOut() {
        SPUtil.delete(this, Constants.KEY_TOKEN);
        SPUtil.delete(this, Constants.KEY_STORE_ID);
        SPUtil.delete(this, Constants.KEY_ITEM_ID);
        SPUtil.delete(this, Constants.KEY_STORENAME);
        SPUtil.delete(this, Constants.KEY_ADDRESS);
        SPUtil.delete(this, Constants.KEY_AVATAR);
        SPUtil.delete(this, Constants.KEY_TRUENAME);
        SPUtil.delete(this, Constants.KEY_MOBILE);
        CartCountManage.newInstance().setCount(0);

        SPUtil.delete(UiUtil.getContext(), Constants.KEY_TOKEN);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_MOBILE);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_COMPANY);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_MONEY);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_QRCODE);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_BANK);

        SPUtil.delete(UiUtil.getContext(), Constants.KEY_BRANCH);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_ACCOUNT);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_TRUENAME);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_HAVEBANK);
        SPUtil.delete(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY);
        
        LoginActivity.startActivity(this,2);
        finish();
    }
}
