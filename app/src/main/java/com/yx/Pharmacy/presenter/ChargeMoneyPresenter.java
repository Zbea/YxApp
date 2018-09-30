package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   ChargeMoneyPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/8/9 22:35
 *  @描述：    TODO
 */

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.PayOrderModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IChargeMoneyView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChargeMoneyPresenter {
    private IChargeMoneyView mView;

    public ChargeMoneyPresenter(IChargeMoneyView view) {
        this.mView = view;
    }


    public void chargeWallte(BaseActivity activity, String money) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("money",money);
        HomeNet.getHomeApi().chargeWallet(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<Boolean>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<Boolean> response) {
                       if (TextUtils.equals(response.getCode(), "200")) {
                           mView.showCreate(response.getData(),response.getExtention(),response.getAlertmsg());
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
     *  支付
     */
    public void payOrder(BaseActivity activity, String action, String order_no, String total_amount) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("action",action);
        urlMap.put("order_no",order_no);
        urlMap.put("total_amount",total_amount);
        urlMap.put("describe","充值");
        HomeNet.getHomeApi().payOrder(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<PayOrderModel>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<PayOrderModel> response) {
                       if (TextUtils.equals(response.getCode(), "200")) {
                           mView.showPay(response.getData(),response.getAlertmsg());
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
