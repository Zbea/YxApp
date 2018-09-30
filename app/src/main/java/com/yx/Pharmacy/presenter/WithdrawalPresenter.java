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
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IWithdrawalView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WithdrawalPresenter {
    private IWithdrawalView mView;

    public WithdrawalPresenter(IWithdrawalView view) {
        this.mView = view;
    }

    /**
     * 提现申请
     * @param activity
     * @param money 申请提现金额
     */
    public void commitWithdrawal(final BaseActivity activity,String money) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("money",money);
        HomeNet.getHomeApi().commit_Withdrawal(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<Object>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<Object> response) {//返回是布尔值
                       if (response.getData()!=null) {
                           mView.withdrawalResult(response.getData());
                       }
                       activity.getShortToastByString(response.getAlertmsg());

                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e("kid","error========="+e.toString());
                       super.onError(e);
                   }
               });
    }
}
