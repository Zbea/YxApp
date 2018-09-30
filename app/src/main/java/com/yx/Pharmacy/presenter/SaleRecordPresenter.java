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
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.SaleRecordModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.ISaleRecordView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SaleRecordPresenter {
    private ISaleRecordView mView;

    public SaleRecordPresenter(ISaleRecordView view) {
        this.mView = view;
    }

    /**
     * 获取分类列表数据
     * @param activity
     * @param
     * @param pagenum
     */
    public void getsaleRecordList(final BaseActivity activity, String pid, final int pagenum, final boolean isRefresh) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        urlMap.put("pagenum",String.valueOf(pagenum));
        HomeNet.getHomeApi().getSaleRecordList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressNoCode<BasisBean<List<SaleRecordModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<SaleRecordModel>> response) {
                       if (response.getData()!=null) {
                           if(isRefresh){
                               mView.refreshSaleRecordList(response.getData());
                           }else {
                               mView.getSaleRecordList(response.getData());
                           }
                       }else if(pagenum==1){
                           mView.noSaleRecordList();
                       }else {//后台返回null......
                           mView.getSaleRecordList(new ArrayList<SaleRecordModel>());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }

    /**
     * 收藏商品
     * @param activity
     * @param itemId
     */
    public void collectProduct(final BaseActivity activity, String itemId) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",itemId);
        HomeNet.getHomeApi().collectProductDetail(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Boolean>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Boolean> response) {
                        activity.getShortToastByString(response.getAlertmsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 添加商品到购物车
     */
    public void addCartProduct(BaseActivity activity, String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        HomeNet.getHomeApi().addShopcart(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
                            activity.getShortToastByString("添加成功");
                            mView.showAddResult(response.getData());
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

    /**
     * 获取购物车数量
     */
    public void getShopcarNum(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getShopcartNum(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
//                            activity.getShortToastByString("添加成功");
                            mView.getShopCarNum(response.getData().count);
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
