package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   AskAfterSalePresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import android.util.Log;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AskModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IAskAfterSaleView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AskAfterSalePresenter {
    private IAskAfterSaleView mView;

    public AskAfterSalePresenter(IAskAfterSaleView view) {
        this.mView = view;
    }

    /**
     *  获取分类列表数据
     */
    public void getAskAfterSaleData(final BaseActivity activity, String orderid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().ask_order_back(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AskModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AskModel> response) {
                        if (response.getData()!=null) {
                            mView.getData(response.getData());
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

    /**
     *  提交售后申请
     */
    public void commitAfterSale(final BaseActivity activity, String orderid, final String reason, String note, String price, String goodsList,int count,int type,String logistcsName,String logistcsNumber) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        urlMap.put("reason",reason);
        urlMap.put("note",note);
//        urlMap.put("price", price);
        urlMap.put("goodsList",goodsList);
        urlMap.put("count",""+count);
        urlMap.put("type",""+type);
        urlMap.put("sendNo",""+logistcsNumber);
        urlMap.put("sendType",""+logistcsName);
        HomeNet.getHomeApi().commitAfterSale(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {
                        if (response.getData()!=null) {//返回售后订单
                            mView.commitBack(response.getExtention());

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
