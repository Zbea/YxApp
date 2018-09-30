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
import com.yx.Pharmacy.view.IChangePwdView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangePhonePresenter {
    private IChangePwdView mView;

    public ChangePhonePresenter(IChangePwdView view) {
        this.mView = view;
    }


    /**
     *
     * @param activity
     * @param mobile   原手机
     * @param psw  密码
     * @param newmobile  新手机
     * @param smscode  验证码
     */
    public void changePhone(final BaseActivity activity, String mobile, String psw, String newmobile, String smscode) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("mobile",mobile);
        urlMap.put("psw",psw);
        urlMap.put("newmobile",newmobile);
        urlMap.put("smscode",smscode);
        HomeNet.getHomeApi().changePhone(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<Object>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<Object> response) {
                       if (response.getData()!=null) {
                           mView.getChangePwdResult(response.getData());
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

    /**
     *  发送验证码
     *  action：执行的操作  regist（注册时传入）   login（登录时传入） updatepsw（修改用户密码）updatemobile（修改手机号） addbankcard（设置密码）
     */
    public void sendCheckCode(final BaseActivity activity, String phoneNum, String type) {
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
}
