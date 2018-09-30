package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   MiaoShaPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/8/11 14:36
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IMiaoShaView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MiaoShaPresenter {
    private IMiaoShaView mView;
    private int mPage;

    public MiaoShaPresenter(IMiaoShaView view) {
        this.mView = view;
    }


    public void loadProduct(BaseActivity activity, String levelid, int action) {
        mPage = 1;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",mPage+"");
        urlMap.put("levelid",levelid);
        urlMap.put("action",action+"");
        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<DrugModel>> response) {
                       mView.getProductListResult(response.getData(),response.getExtention());
                       if (response.getData()==null) {
                           activity.getShortToastByString(response.getAlertmsg());
                       }
                   }
                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }

    public void moreProduct(BaseActivity activity, String levelid, int action) {
        mPage = 1;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",mPage+"");
        urlMap.put("levelid",levelid);
        urlMap.put("action",action+"");
        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<DrugModel>> response) {
                       if (response.getData()!=null) {
                           mView.moreProductListResult(response.getData());
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
}
