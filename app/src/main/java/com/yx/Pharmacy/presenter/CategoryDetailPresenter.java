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
import android.widget.ImageView;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.ICategoryDetailView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryDetailPresenter {
    private ICategoryDetailView mView;

    public CategoryDetailPresenter(ICategoryDetailView view) {
        this.mView = view;
    }

    /**
     * 获取分类详情列表数据
     * @param activity
     * @param pagenum 页码
     * @param catid  分类id
     * @param isUpOrder 是否升序
     * @param type 类型 1价格 pricesort 2销量 salesort 3综合 colligatesort
     * @param isRefresh 是否下拉刷新
     * 价格升降排序 2降序   1升序
     * 销量 2降序  1升序
     * 综合排序 0降序 1升序 （按照商品的订单量进行匹配商品）
     */
    public void getProductList(final BaseActivity activity, int pagenum, int catid, int type, boolean isUpOrder, final boolean isRefresh) {
        HashMap<String, String> urlMap = new HashMap<>();
        urlMap.put("pagenum",pagenum+"");
        urlMap.put("catid",catid+"");
        if(type==1){
            urlMap.put("pricesort",isUpOrder?"1":"2");
            urlMap.put("salesort","");
            urlMap.put("colligatesort","");
        }else if(type==2){
            urlMap.put("salesort",isUpOrder?"1":"2");
            urlMap.put("pricesort","");
            urlMap.put("colligatesort","");
        }else {
            urlMap.put("colligatesort",isUpOrder?"1":"0");
            urlMap.put("salesort","");
            urlMap.put("pricesort","");
        }
        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<DrugModel>> response) {
                       if (response.getData()!=null) {
                           if(isRefresh){
                               mView.getProductListResult(response.getData());
                           }else {
                               mView.addProductListResult(response.getData());
                           }
                       }else if(pagenum==1){
                           mView.noProductListData();
                       }else {//后台返回null......
                           mView.addProductListResult(new ArrayList<DrugModel>());
                       }
                   }
                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                       mView.onErrorPage();
                   }
               });
    }
    /**
     * 添加商品到购物车
     */
    public void addCartProduct(final BaseActivity activity, String pid, final DrugModel item, final ImageView imgview) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",pid);
        urlMap.put("count", item.getMinimun()+"");
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
     * 获取购物车数量
     */
    public void getShopcarNum(final BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        HomeNet.getHomeApi().getShopcartNum(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
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

    public void miaoshaBuy(BaseActivity activity, String pid, String confirm,final DrugModel item, final ImageView imgview) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("confirm", confirm);
        urlMap.put("count", item.getMinimun()+"");
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            mView.showAddResult(response.getData(),item,imgview);
                        }
                        else
                        {
                            if (!TextUtils.isEmpty(response.getAlertmsg()))
                                activity.getShortToastByString(response.getAlertmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }
}
