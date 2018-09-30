package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   AddBankCardPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/8/9 23:14
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IAddBankCardView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddBankCardPresenter {
    private IAddBankCardView mView;

    public AddBankCardPresenter(IAddBankCardView view) {
        this.mView = view;
    }


    /**
     *  发送验证码
     */
    public void sendCheckCode(BaseActivity activity, String phoneNum, String type) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("mobile",phoneNum);
        urlMap.put("action",type);
        HomeNet.getHomeApi().getCheckCode(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<String> response) {
                       activity.getShortToastByString("发送验证码成功");
                   }

                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }

    public void addBankCard(BaseActivity activity, String name, String cardcode, String kaiHuHang, String wangdian, String code) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("username",name);
        urlMap.put("bankname",NetUtil.isStringNull(kaiHuHang));
        urlMap.put("cardnum",cardcode);
        urlMap.put("branch",wangdian);
        urlMap.put("smscode",code);
        urlMap.put("action","addbankcard");
        HomeNet.getHomeApi().addBankCard(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<String> response) {
                       activity.getShortToastByString(response.getAlertmsg());

                       SPUtil.putString(UiUtil.getContext(), Constants.KEY_BANK_USERNAME, name);
                       SPUtil.putString(UiUtil.getContext(), Constants.KEY_BANK, kaiHuHang);
                       SPUtil.putString(UiUtil.getContext(), Constants.KEY_BRANCH,wangdian);
                       SPUtil.putString(UiUtil.getContext(), Constants.KEY_ACCOUNT,cardcode);
                       SPUtil.putBoolean(UiUtil.getContext(), Constants.KEY_HAVEBANK,true);
                   }

                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }
}
