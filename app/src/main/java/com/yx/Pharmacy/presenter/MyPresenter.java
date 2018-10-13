package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   LoginPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.LoginModel;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.ILoginView;
import com.yx.Pharmacy.view.IMyOrderNumView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyPresenter {
    private IMyOrderNumView mView;

    public MyPresenter(IMyOrderNumView view) {
        this.mView = view;
    }

    /**
     *  发送验证码
     */
    public void getOrderNum(BaseActivity activity) {

        HomeNet.getHomeApi().getOrderNum().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<MyOrderNumModel>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<MyOrderNumModel> response) {
                       if (response!=null)
                       {
                           if (response.getCode().equals("200"))
                           {
                               mView.resultCartNum(response.getData());
                           }
                       }
                   }
                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }


}
