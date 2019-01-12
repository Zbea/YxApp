package com.yx.Pharmacy.presenter;


import android.util.Log;
import android.widget.ImageView;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.SearchAutoModel;
import com.yx.Pharmacy.model.TagModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.ISearchView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {
    private ISearchView mView;

    public SearchPresenter(ISearchView view) {
        this.mView = view;
    }

    /**
     * 获取分类列表数据
     */
    public void getHotSearchList(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getSearchHotList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<TagModel>>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<List<TagModel>> response) {
                        if (response.getData() != null) {
                            mView.getHotSearchList(response.getData());
                        } else {
                            activity.getShortToastByString(response.getAlertmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("kid", "error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }


    /**
     *z自动补全
     */
    public void productSearchAuto(final BaseActivity activity, String keyword) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("keyword", keyword);
        HomeNet.getHomeApi().getSearchAutoList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<SearchAutoModel>>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<List<SearchAutoModel>> response) {
                        mView.getAutoSearchList(response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.getAutoSearchList(null);
                    }

                    @Override
                    public void onFail(BasisBean<List<SearchAutoModel>> response) {
                        super.onFail(response);
                        mView.getAutoSearchList(null);
                    }
                });
    }



}
