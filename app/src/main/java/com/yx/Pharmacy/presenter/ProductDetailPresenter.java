package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   ProductDetailPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/21 14:37
 *  @描述：    TODO
 */

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.ProductDetailModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IProductDetailView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailPresenter {
    private IProductDetailView mView;

    public ProductDetailPresenter(IProductDetailView view) {
        this.mView = view;
    }

    /**
     * 获取商品详情
     *
     * @param activity
     * @param itemId
     */
    public void loadProductDetail(BaseActivity activity, String itemId) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", itemId);
        HomeNet.getHomeApi().getProductDetail(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<ProductDetailModel>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<ProductDetailModel> response) {
                        if (response.getData() != null) {
                            mView.showProductDetail(response.getData());
                        } else {
                            mView.errorView();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.errorNetView();
                        super.onError(e);
                    }
                });
    }

    /**
     * 收藏商品
     *
     * @param activity
     * @param itemId
     */
    public void collectProduct(final BaseActivity activity, String itemId) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", itemId);
        HomeNet.getHomeApi().collectProductDetail(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Boolean>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Boolean> response) {
                        if (response.getData() != null) {
                            mView.showCollect();
                            SPUtil.putString(UiUtil.getContext(), Constants.KEY_COLLECT, response.getExtention());
                        }
                        activity.getShortToastByString(response.getAlertmsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    public void cancleCollect(final BaseActivity activity, String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        HomeNet.getHomeApi().cancelcollect(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<Boolean>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Boolean> response) {
                        if (response.getData() != null) {
                            mView.showDisCollect();
                            SPUtil.putString(UiUtil.getContext(), Constants.KEY_COLLECT, response.getExtention());
                        }
                        activity.getShortToastByString(response.getAlertmsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }


    /**
     * 添加商品到购物车
     */
    public void addCartProduct(BaseActivity activity, String pid, int amount) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("count", String.valueOf(amount));
        HomeNet.getHomeApi().addShopcart(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData() != null) {
                            activity.getShortToastByString("添加成功");
                            mView.showAddResult(response.getData());
                        } else {
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
     * 添加商品到购物车
     */
    public void productArrive(BaseActivity activity, String pid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        HomeNet.getHomeApi().productArrive(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<String> response) {
                        activity.getShortToastByString(response.getAlertmsg());
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
                        if (response.getData() != null) {
//                            activity.getShortToastByString("添加成功");
                            if (!TextUtils.isEmpty(response.getData().count))
                                mView.getShopCarNum(response.getData().count);
                        } else {
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
     * 秒杀专区商品秒杀价购买
     * confirm：是否覆盖购物车内的的秒杀活动商品 0 不覆盖  1覆盖
     */
    public void miaoshaBuy(BaseActivity activity, String pid, String confirm) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("confirm", confirm);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {
                        if (TextUtils.equals(response.getCode(), "200")) {
                            mView.compelete();
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

    /**
     * 秒杀专区商品秒杀价购买
     */
    public void miaoshaBuy(BaseActivity activity, String pid, int count) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("count", "" + count);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<Object>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<Object> response) {
                        if (TextUtils.equals(response.getCode(), "201")) {
                            mView.ifFuGai();
                        } else {
                            if (!TextUtils.isEmpty(response.getAlertmsg()))
                            {
                                activity.getShortToastByString(response.getAlertmsg());
                            }
                            else
                            {
                                mView.compelete();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        super.onError(e);
                    }
                });
    }


    public void loadMyShop(BaseActivity activity, boolean isShow) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("doplace", "home");
        HomeNet.getHomeApi().getMyShop(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<List<MyShopModel>>>(activity, isShow) {
                    @Override
                    public void onSuccess(BasisBean<List<MyShopModel>> response) {
                        if (response.getData() != null) {
                            mView.showShopData(response.getData());
                        } else {
                            mView.hideFlash();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error=========" + e.toString());
                        mView.hideFlash();
                        super.onError(e);
                    }
                });
    }
}
