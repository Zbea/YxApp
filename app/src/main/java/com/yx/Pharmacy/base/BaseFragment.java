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
import com.yx.Pharmacy.constant.Constants;
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
