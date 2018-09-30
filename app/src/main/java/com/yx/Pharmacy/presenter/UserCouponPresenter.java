package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   MyCollectPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/23 0:29
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.CouponData;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IUserCouponView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserCouponPresenter {
    private IUserCouponView mView;

    public UserCouponPresenter(IUserCouponView view) {
        this.mView = view;
    }

    public void getCouponList(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getUserCouponList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<CouponData>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<CouponData> response) {
                       CouponData model=new CouponData();
                        if(response.getData()!=null){
                            model=response.getData();
                        }
                       mView.getCouponList(model);
                   }

                   @Override
                   public void onError(Throwable e) {
                       mView.errorData();
                       activity.getShortToastByString("内部错误");
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }
}
