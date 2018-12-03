package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   OrderDetailPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IOrderDetailView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderDetailPresenter {
    private IOrderDetailView mView;

    public OrderDetailPresenter(IOrderDetailView view) {
        this.mView = view;
    }


    public void getAdvanceData(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("type","34");
        HomeNet.getHomeApi().getAdData(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<HomeAdvanceModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<HomeAdvanceModel> response) {
                        if (response.getData()!=null) {
                            LogUtils.i("response.getData()========="+response.getData().toString());
                            mView.showAdvanceData(response.getData());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error========="+e.toString());
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取分类列表数据
     * @param activity
     * @param orderid 订单号
     */
    public void getOrderDetailData(final BaseActivity activity, String orderid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().getOrderDetail(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<OrderModel>>(activity, true) {
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
                        mView.onErrorPage();
                    }
                });
    }
    /**
     * 再次购买
     * @param activity
     * @param orderid 订单号
     */
    public void buyAgain(final BaseActivity activity, String orderid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().buyAgain(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        activity.getShortToastByString(response.getAlertmsg());
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    /**
     * 提醒发货
     * @param activity
     * @param orderid 订单号
     */
    public void notifySendOrder(final BaseActivity activity, String orderid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().nofitySendOrder(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        activity.getShortToastByString(response.getAlertmsg());
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    /**
     * 确认收货
     * @param activity
     * @param orderid 订单号
     */
    public void comfirmReceiveOrder(final BaseActivity activity, String orderid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().comfirmOrder(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        activity.getShortToastByString(response.getAlertmsg());
                        mView.comfirmBack();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}