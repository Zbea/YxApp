package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   LoginPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.LoginModel;
import com.yx.Pharmacy.net.CustomEncryptHelper;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.ILoginView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {
    private ILoginView mView;

    public LoginPresenter(ILoginView view) {
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

    /**
     *  注册
     */
    public void registerUser(BaseActivity activity, String phoneNum, String password, String code, String tuijainma) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("mobile",phoneNum);
        urlMap.put("smscode",code);
        urlMap.put("password",password);
        urlMap.put("inviter",NetUtil.isStringNull(tuijainma));
        HomeNet.getHomeApi().register(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<String> response) {
                       if (TextUtils.equals(response.getCode(), "200")) {
                           activity.getShortToastByString(response.getAlertmsg());
                           activity.finish();
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
     *  验证码登录
     */
    public void loginCode(BaseActivity activity, String phoneNum, String code) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("username",phoneNum);
        urlMap.put("smscode",code);
        HomeNet.getHomeApi().loginCode(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<LoginModel>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<LoginModel> response) {
                       if (response.getData()!=null) {
                           mView.loginResult(response.getData());
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
     *  密码登录
     */
    public void loginPwd(BaseActivity activity, String phoneNum, String code) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("username",phoneNum);
        urlMap.put("password",code);
        HomeNet.getHomeApi().loginPwd(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<LoginModel>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<LoginModel> response) {
                       if (response.getData()!=null) {
                           mView.loginResult(response.getData());
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
