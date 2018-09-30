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
}
