package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   LoginPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import android.util.Log;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.WuliuData;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IWuliuView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WuliuPresenter {
    private IWuliuView mView;

    public WuliuPresenter(IWuliuView view) {
        this.mView = view;
    }

    /**
     *  获取物流信息
     */
    public void getWuliuData(BaseActivity activity,String orderid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().check_wuliu(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<WuliuData>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<WuliuData> response) {
                       if (response.getData()!=null) {
                           mView.getWuliuResult(response.getData());
                       }else {
                           activity.getShortToastByString(response.getAlertmsg());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e("kid","error========="+e.toString());
                       super.onError(e);
                   }
               });
    }
}
