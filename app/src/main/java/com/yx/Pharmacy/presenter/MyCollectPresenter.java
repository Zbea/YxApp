package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   MyCollectPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/23 0:29
 *  @描述：    TODO
 */

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IMyCollectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyCollectPresenter {
    private IMyCollectView mView;

    public MyCollectPresenter(IMyCollectView view) {
        this.mView = view;
    }

    public void getCollectList(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum","1");
        HomeNet.getHomeApi().getCollectionList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<List<DrugModel>> response) {
                        List<DrugModel>models=new ArrayList<DrugModel>();
                        if(response.getData()!=null){
                            models=response.getData();
                        }
                        mView.getCollectList(models);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error========="+e.toString());
                        super.onError(e);
                    }
                });
    }


    public void cancleCollect(final BaseActivity activity, int pid, final int position) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",String.valueOf(pid));
        HomeNet.getHomeApi().cancelcollect(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Boolean>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<Boolean> response) {
                        if(response.getData()){
                            mView.cancelSuccess(position);
                        }
                        activity.getShortToastByString(response.getAlertmsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error========="+e.toString());
                        super.onError(e);
                    }
                });
    }
}
