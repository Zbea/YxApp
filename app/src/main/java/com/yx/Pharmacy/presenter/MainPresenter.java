package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   MainPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import android.util.Log;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.SplashData;
import com.yx.Pharmacy.model.UrlBean;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IMainView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private IMainView mView;

    public MainPresenter(IMainView view) {
        this.mView = view;
    }

    /**
     *  获取分类列表数据
     */
    public void getSplashAdData(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getSplashAd(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<SplashData>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<SplashData> response) {
                       if (response.getData()!=null) {
                           mView.getSplashAd(response.getData().splash);

                           try {
                               SPUtil.putBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, Boolean.parseBoolean(response.getExtention()));
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }else {
                           activity.getShortToastByString(response.getAlertmsg());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.e("kid","error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    /**
     *  获取本地url
     */
    public void getUrl(BaseActivity activity) {
        HomeNet.getHomeApi().getUrlData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<UrlBean>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<UrlBean> response) {
                        if (response.getData()!=null) {
                            mView.getUrl(response.getData());
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
