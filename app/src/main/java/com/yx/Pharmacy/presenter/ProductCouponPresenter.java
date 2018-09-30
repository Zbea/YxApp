package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   LoginPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.CouponModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IProductCouponView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductCouponPresenter {
    private IProductCouponView mView;

    public ProductCouponPresenter(IProductCouponView view) {
        this.mView = view;
    }

    public void getProductCouponList(final BaseActivity activity,String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        HomeNet.getHomeApi().getProduct_Coupon(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<CouponModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<CouponModel>> response) {
                       if (response.getData()!=null) {
                           mView.getProductCounponResult(response.getData());
                       }else {
                           activity.getShortToastByString(response.getAlertmsg());
                       }

                   }
                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }

    public void saveCoupon(BaseActivity activity, String couponid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("couponid",NetUtil.isStringNull(couponid));
        HomeNet.getHomeApi().saveCoupon(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Boolean>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<Boolean> response) {
                        if (response.getData()) {
//                            mView.showSaveResult(couponid);
                            activity.getShortToastByString("领取成功");
                        }else {
                            activity.getShortToastByString(response.getAlertmsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error========="+e.toString());
                        super.onError(e);
                    }
                });
    }

}
