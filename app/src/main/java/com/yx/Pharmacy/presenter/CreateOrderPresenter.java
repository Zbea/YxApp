package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   CreateOrderPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/8/8 22:09
 *  @描述：    TODO
 */

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.CreateOrderModel;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.ICreateOrderView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreateOrderPresenter {
    private ICreateOrderView mView;

    public CreateOrderPresenter(ICreateOrderView view) {
        this.mView = view;
    }


    public void createOrder(BaseActivity activity, String orderdata) {
        orderdata=orderdata.replace("\\","");
        L.i(orderdata);
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("orderdata",NetUtil.isStringNull(orderdata));
        HomeNet.getHomeApi().createOrder(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressNoCode<BasisBean<CreateOrderModel>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<CreateOrderModel> response) {
                       activity.getShortToastByString(response.getAlertmsg());
                       if (TextUtils.equals(response.getCode(),"200")) {
                           mView.showCreateResult(response.getData());
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
     *  发送验证码
     */
    public void getOrderNum(BaseActivity activity) {

        HomeNet.getHomeApi().getOrderNum().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<MyOrderNumModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<MyOrderNumModel> response) {
                        if (response!=null)
                        {
                            if (response.getCode().equals("200"))
                            {
                                mView.resultCartNum(response.getData());
                            }
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
