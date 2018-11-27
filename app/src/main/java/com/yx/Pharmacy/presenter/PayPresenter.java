package com.yx.Pharmacy.presenter;

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.MyOrderNumModel;
import com.yx.Pharmacy.model.PayOrderModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IPayView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created time  2018/8/9 0009
 * @author : mcx
 * 类描述 :
 */

public class PayPresenter {
    private IPayView mView;

    public PayPresenter(IPayView view) {
        this.mView = view;
    }

    /**
     *  订单支付
     */
    public void payOrder(BaseActivity activity, String action, String order_no, String total_amount) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("action",action);
        urlMap.put("order_no",order_no);
        urlMap.put("total_amount",total_amount);
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

    /**
     *  发送验证码
     */
    public void getOrderNum(BaseActivity activity) {

        HomeNet.getHomeApi().getOrderNum().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<MyOrderNumModel>>(activity, true) {
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
