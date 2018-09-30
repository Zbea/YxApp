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
import android.util.Log;

import com.yx.Pharmacy.base.BasePermissionActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IScanView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScanPresenter {
    private IScanView mView;

    public ScanPresenter(IScanView view) {
        this.mView = view;
    }

    /**
     *  获取分类列表数据
     */
    public void getScanData(BasePermissionActivity activity, String qrcode) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("qrcode",qrcode);
        HomeNet.getHomeApi().getScanProduct(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<DrugModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<DrugModel> response) {
                        if (response.getData()!=null) {
                            mView.scanResultProduct(response.getData());
                        }else {
                            mView.scanNoData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("kid","error========="+e.toString());
                        super.onError(e);
                    }
                });
    }

    /**
     * 添加商品到购物车
     */
    public void addCartProduct(final BasePermissionActivity activity, String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        HomeNet.getHomeApi().addShopcart(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
//                        if (response.getData()!=null) {
//
//                        }
                        activity.getShortToastByString(response.getAlertmsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }


    /**
     * 秒杀专区商品秒杀价购买
     * confirm：是否覆盖购物车内的的秒杀活动商品 0 不覆盖  1覆盖
     */
    public void miaoshaBuy(final BasePermissionActivity activity, String pid,String confirm) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        urlMap.put("confirm",confirm);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {
//                        if (TextUtils.equals(response.getCode(), "200")) {
////                            activity.getShortToastByString(response.getAlertmsg());
//                        }else {
//                            mView.ifFuGai();
//                        }
                        if(!TextUtils.isEmpty(response.getAlertmsg()))activity.getShortToastByString(response.getAlertmsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error========="+e.toString());
                        super.onError(e);
                    }
                });
    }
    /**
     * 秒杀专区商品秒杀价购买
     */
    public void miaoshaBuy(final BasePermissionActivity activity, String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {
                        if (TextUtils.equals(response.getCode(), "201")) {
                            mView.ifFuGai();
                        }else {
                            if(!TextUtils.isEmpty(response.getAlertmsg()))activity.getShortToastByString(response.getAlertmsg());
                            mView.compelete();
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
