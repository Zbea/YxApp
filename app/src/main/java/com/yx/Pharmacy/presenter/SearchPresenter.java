package com.yx.Pharmacy.presenter;


import android.util.Log;
import android.widget.ImageView;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.TagModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
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
     *  获取分类列表数据
     */
    public void getHotSearchList(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getSearchHotList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<TagModel>>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<List<TagModel>> response) {
                        if (response.getData()!=null) {
                            mView.getHotSearchList(response.getData());
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
     * 获取搜索结果数据
     * @param activity
     * @param pagenum 页码
     * @param keyword  关键字
     * @param isUpOrder 是否升序
     * @param type 类型 1价格 pricesort 2销量 salesort 3综合 colligatesort
     * @param isRefresh 是否下拉刷新
     * 价格升降排序 2降序   1升序
     * 销量 2降序  1升序
     * 综合排序 0降序 1升序 （按照商品的订单量进行匹配商品）
     */
    public void getSearchResult(final BaseActivity activity, final int pagenum, String keyword, int type, boolean isUpOrder, final boolean isRefresh) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",pagenum+"");
        urlMap.put("keyword",NetUtil.isStringNull(keyword));
        if(type==1){
            urlMap.put("colligatesort","0");
            urlMap.put("pricesort",isUpOrder?"1":"2");
            urlMap.put("salesort","2");
        }else if(type==2){
            urlMap.put("colligatesort","0");
            urlMap.put("pricesort","2");
            urlMap.put("salesort",isUpOrder?"1":"2");
        }else {
            urlMap.put("colligatesort",isUpOrder?"1":"0");
            urlMap.put("pricesort","2");
            urlMap.put("salesort","2");
        }
        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<List<DrugModel>> response) {
                        if (response.getData()!=null) {
                            if(isRefresh){
                                mView.getSearchResultList(response.getData());
                            }else {
                                mView.addSearchResultList(response.getData());
                            }
                        }else {
                            if(pagenum==1){//无数据
                                mView.noData();
                            }
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("kid","getSearchResult error========="+e.toString());
                        mView.noErrorData();
                        super.onError(e);
                    }
                });
    }

    /**
     * 添加商品到购物车
     */
    public void addCartProduct(final BaseActivity activity, String pid, final DrugModel item, final ImageView imgview) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        HomeNet.getHomeApi().addShopcart(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
//                            activity.getShortToastByString("添加成功");
                            mView.showAddResult(response.getData(),item,imgview);
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
     * 到货通知
     */
    public void productArrive(final BaseActivity activity, String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        HomeNet.getHomeApi().product_arriveNotify(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
                            activity.getShortToastByString(response.getAlertmsg());
//                            activity.getShortToastByString("已为您设置到货提醒，请留意平台到货通知");
//                            mView.showAddResult(response.getData(),item,imgview);
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
     * 获取购物车数量
     */
    public void getShopcarNum(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getShopcartNum(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
//                            activity.getShortToastByString("添加成功");
                            mView.getShopCarNum(response.getData().count);
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
