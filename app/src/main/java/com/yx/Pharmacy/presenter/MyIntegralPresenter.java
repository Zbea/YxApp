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
import com.yx.Pharmacy.model.CreditData;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IMyIntegralView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyIntegralPresenter {
    private IMyIntegralView mView;

    public MyIntegralPresenter(IMyIntegralView view) {
        this.mView = view;
    }

    public void getMyIntegralData(BaseActivity activity, int pagenum) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",String.valueOf(pagenum));
        HomeNet.getHomeApi().getMyInteral(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<CreditData>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<CreditData> response) {
                        if(response.getData()!=null){
                            mView.getIntegralData(response.getData());
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
