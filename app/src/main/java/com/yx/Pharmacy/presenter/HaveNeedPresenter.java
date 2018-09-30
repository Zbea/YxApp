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
import com.yx.Pharmacy.model.YaoType1;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IHaveNeedView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HaveNeedPresenter {
    private IHaveNeedView mView;

    public HaveNeedPresenter(IHaveNeedView view) {
        this.mView = view;
    }

    /**
     *  获取分类列表数据
     */
    public void getCategoryData(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getCategoryData(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<YaoType1>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<YaoType1>> response) {
                       if (response.getData()!=null) {
                           mView.getCategoryResult(response.getData());
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
     *  提交商品需求
     */
    public void commitProductNeed(BaseActivity activity,String productname,String count,int type,String note) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("productname",productname);
        urlMap.put("count",count);
        urlMap.put("type",String.valueOf(type));
        urlMap.put("note",note);

        HomeNet.getHomeApi().sendProductNeed(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        if (response.getData()!=null) {//返回的data是 true或者false
                            mView.commitSuccess();
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
