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
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IMyOrderListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyOrderListPresenter {
    private IMyOrderListView mView;

    public MyOrderListPresenter(IMyOrderListView view) {
        this.mView = view;
    }

    /**
     * 获取分类列表数据
     * @param activity
     * @param status 0获取所有订单 1未支付，2已支付，3拣货中，，7.已发货，8已取消，9订单完成
     * @param pagenum
     */
    public void getMyOrderListData(final BaseActivity activity, String status, final int pagenum, final boolean isRefresh) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("status",status);
        urlMap.put("pagenum",String.valueOf(pagenum));
        HomeNet.getHomeApi().getOrderList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<OrderModel>>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<List<OrderModel>> response) {
                        if (response.getData()!=null) {
                            if(isRefresh){
                                mView.refreshOrderList(response.getData());
                            }else {
                                mView.getOrderList(response.getData());
                            }
                        }else if(pagenum==1){
                            mView.noOrderList();
                        }else {//后台返回null......
                            mView.getOrderList(new ArrayList<OrderModel>());
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
    public void comfirmReceiveOrder(final BaseActivity activity, String orderid,int position) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().comfirmOrder(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        activity.getShortToastByString(response.getAlertmsg());
                        mView.comfirmBack(position);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
    /**
     * 取消订单
     * @param activity
     * @param orderid 订单号
     */
    public void cancelOrder(final BaseActivity activity, String orderid,int position) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderid",orderid);
        HomeNet.getHomeApi().cancelOrder(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
//                        activity.getShortToastByString(response.getAlertmsg());
                        mView.cancelBack(position);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
