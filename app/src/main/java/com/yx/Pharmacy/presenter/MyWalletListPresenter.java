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
import com.yx.Pharmacy.model.WalletData;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IMyWalletListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyWalletListPresenter {
    private IMyWalletListView mView;

    public MyWalletListPresenter(IMyWalletListView view) {
        this.mView = view;
    }

    /**
     * 获取分类列表数据
     * @param activity
     * @param
     * @param pagenum
     */
    public void getMyWalletListData(final BaseActivity activity, final int pagenum, final boolean isRefresh) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",String.valueOf(pagenum));
        HomeNet.getHomeApi().getMyWalletList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<WalletData.WallatModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<WalletData.WallatModel>> response) {
                       if (response.getData()!=null) {
                           if(isRefresh){
                               mView.refreshWalletList(response.getData());
                           }else {
                               mView.getWalletList(response.getData());
                           }
                       }else if(pagenum==1){
                           mView.noWallList();
                       }else {//后台返回null......
                           mView.getWalletList(new ArrayList<WalletData.WallatModel>());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }
}
