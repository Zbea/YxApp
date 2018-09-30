package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   BackOrderDetailPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IBackOrderDetailView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BackOrderDetailPresenter {
    private IBackOrderDetailView mView;

    public BackOrderDetailPresenter(IBackOrderDetailView view) {
        this.mView = view;
    }

    /**
     * 获取分类列表数据
     * @param activity
     * @param orderbackid 售后订单号
     */
    public void getOrderDetailData(final BaseActivity activity, String orderbackid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderbackid",orderbackid);
        HomeNet.getHomeApi().getBackOrderDetail(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<OrderModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<OrderModel> response) {
                        if (response.getData()!=null) {
                            mView.getOrderDetailData(response.getData());
                        } else {
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
     * 撤销售后申请
     * @param activity
     * @param orderbackid 订单号
     */
    public void cancelOrderBack(final BaseActivity activity, String orderbackid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderbackid",orderbackid);
        HomeNet.getHomeApi().cancelBackOrderAsk(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        mView.cancelOrderBack();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

}