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
import com.yx.Pharmacy.model.WalletData;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IMyWalletView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyWalletPresenter {
    private IMyWalletView mView;

    public MyWalletPresenter(IMyWalletView view) {
        this.mView = view;
    }

    /**
     *
     * @param activity
     * @param pagenum
     * @param time  年月
     */
    public void getMyIntegralData(BaseActivity activity, int pagenum,String time) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",String.valueOf(pagenum));
        urlMap.put("time",String.valueOf(time));
        HomeNet.getHomeApi().getMyWalletInfo(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<WalletData>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<WalletData> response) {
                       if(pagenum==1){
                           if(response.getData()!=null){
                               mView.getWalletInfo(response.getData());
                           }
                       }else {
                           if(response.getData()!=null){
                               if(response.getData().list!=null){
                                   mView.addWalletList(response.getData().list);
                               }
                           }
                       }


                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                       mView.onErrorPage();
                   }
               });
    }
}
