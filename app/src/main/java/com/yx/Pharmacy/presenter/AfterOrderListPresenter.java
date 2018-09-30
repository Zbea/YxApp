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
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IAfterOrderListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AfterOrderListPresenter {
    private IAfterOrderListView mView;

    public AfterOrderListPresenter(IAfterOrderListView view) {
        this.mView = view;
    }

    /**
     * 获取分类列表数据
     * @param activity
     * @param status status：售后订单的状态  0已申请、1审核中，6.已撤销 7通过处理中，8不通过，9已完成
     * @param pagenum
     */
    public void getAfterOrderListData(final BaseActivity activity, int status, final int pagenum, final boolean isRefresh) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("status",String.valueOf(status));
//        urlMap.put("pagenum",String.valueOf(pagenum));
        HomeNet.getHomeApi().getAfterOrderList(urlMap).subscribeOn(Schedulers.io())
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
//                       else {
//                           activity.getShortToastByString(response.getAlertmsg());
//                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e("kid","error========="+e.toString());
                       super.onError(e);
                       mView.onErrorPage();
                   }
               });
    }

    /**
     * 撤销售后申请
     * @param activity
     * @param orderbackid 订单号
     */
    public void cancelOrderBack(final BaseActivity activity, String orderbackid,int position) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderbackid",orderbackid);
        HomeNet.getHomeApi().cancelBackOrderAsk(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
//                        activity.getShortToastByString(response.getAlertmsg());
                        mView.cancelOrderBack(position);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 添加售后订单的快递信息
     * @param activity
     * @param orderbackid 订单号
     */
    public void tuihuo(final BaseActivity activity, String orderbackid,String name,String orderNum) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderBackId",orderbackid);
        urlMap.put("sendNo",orderNum);
        urlMap.put("sendType",name);
        HomeNet.getHomeApi().tuihuoOrderAsk(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        activity.getShortToastByString(response.getAlertmsg());
                        Log.e("kid","response.getAlertmsg()===="+response.getAlertmsg());
                        mView.tuihuoBack();
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
