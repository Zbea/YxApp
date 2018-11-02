package com.yx.Pharmacy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.yx.Pharmacy.activity.CommendMsActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.CommendTjActivity;
import com.yx.Pharmacy.activity.LoginActivity;
import com.yx.Pharmacy.activity.MyShopActivity;
import com.yx.Pharmacy.activity.MyShopAddActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.activity.SearchActivity;
import com.yx.Pharmacy.activity.WebviewActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/8.
 */
public abstract class BaseFragment
        extends Fragment
{

    private Toast toast;
    protected  Activity mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mContext = getActivity();
//        View view = View.inflate(mContext, getLayoutId(), null);
        View view=inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void init();


    /**
     * 显示短时间Toast
     * @param hint
     */
    public void getShortToastByString(String hint){
        if(TextUtils.isEmpty(hint)) return;
        if (toast == null) {
            if(getActivity().getApplicationContext()!=null) {
                toast = Toast.makeText(getActivity().getApplicationContext(), hint, Toast.LENGTH_SHORT);
            }else {
                toast = Toast.makeText(UiUtil.getContext(), hint, Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        toast.show();
    }

    /**
     * 广告跳转类型
     * @param goldBean
     */
    public void gotoClick(HomeAdvanceModel.GoldBean goldBean)
    {
        switch (goldBean.pushtype){
            case 1://app跳转活动模块，参数weburl
                HHActivity.startActivity(mContext,goldBean.weburl);
//                if (goldBean.weburl.contains("coupon"))
//                {
//                    WebviewActivity.startActivity(mContext,goldBean.weburl);
//                }
//                else
//                {
//                    HHActivity.startActivity(mContext,goldBean.weburl);
//                }
                break;
            case 2://app跳转商品详情，参数goodsid
                ProductDetailActivity.startActivity(mContext,goldBean.goodsid);
                break;
            case 3://如果当前用户登录并且没有认证门店，跳转到门店认证页，不需要参数
                if (TextUtils.isEmpty(NetUtil.getToken())) {
                    LoginActivity.startActivity(mContext);
                }else {
                    if(SPUtil.getBoolean(mContext, Constants.KEY_STORE_CERTIFY)){
                        MyShopActivity.startActivity(mContext);
                    }else {
                        MyShopAddActivity.startActivity(mContext);
                    }
                }
                break;
            case 4://app携带关键字跳转至搜索页，参数keyword(需要查找商品的关键字)
                SearchActivity.startActivity(mContext,goldBean.keyword);
                break;
            case 5://跳转到其他专区的活动，参数activityname（活动名册）levelid（活动id）type（活动的类型）
                String type = goldBean.type;
                if (TextUtils.equals(type, "1")) {
                    // 秒杀
                    CommendMsActivity.startActivity(mContext,goldBean.levelid,goldBean.activityname);
                } else if (TextUtils.equals(type, "2")) {
                    // 特价
                    CommendTjActivity.startActivity(mContext, type, goldBean.levelid,goldBean.activityname);
                } else if (TextUtils.equals(type, "3")) {
                    // 满减
                    CommendProductActivity.startActivity(mContext, type, goldBean.levelid,goldBean.activityname);
                } else if (TextUtils.equals(type, "9")) {
                    // 控销
                    CommendProductActivity.startActivity(mContext, type, goldBean.levelid,goldBean.activityname);
                }
                break;
            case 6://新特药新区
                CommendProductActivity.startActivity(mContext,goldBean.pushtype+"",goldBean.goodstype,goldBean.title,1);
                break;
        }
    }

    public void contactService()
    {
        YSFUserInfo userInfo = new YSFUserInfo();
        String title = "";
        if (TextUtils.isEmpty(NetUtil.getToken())){
            title = "游客"+ DensityUtils.getRandomString(16);
            userInfo.userId = title;
        }else {
            if (SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, false)) {
                title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE);
            }else {
                title = SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
            }
            userInfo.userId = NetUtil.getToken();
        }
        userInfo.data = "[{\"key\":\"real_name\", \"value\":"+title+"}]";
        Unicorn.setUserInfo(userInfo);
        ConsultSource source = new ConsultSource("我的门店", title, "custom information string");
        Unicorn.openServiceActivity(mContext, "点药呗", source);
    }

}
