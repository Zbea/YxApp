package com.yx.Pharmacy.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.manage.ProductMaxManage;
import com.yx.Pharmacy.model.SavaCouponModel;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.view.IShopCartView;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created time  2018/8/2 0002
 * @author : mcx
 * 类描述 :
 */

public class ShopCartPresenter {
    private IShopCartView mView;

    public ShopCartPresenter(IShopCartView view) {
        this.mView = view;
    }

    public void loadShopCart(BaseActivity activity) {
        HomeNet.getHomeApi().getShopCartList().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressNoCode<BasisBean<ShopCartModel>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<ShopCartModel> response) {
                       if (TextUtils.equals(response.getCode(), "200")) {
                           mView.showShopCartList(response.getData());
                       }else {
                           mView.showFailView();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       mView.showFailView();
                       super.onError(e);
                   }
               });
    }

    public void updateShopCart(BaseActivity activity, String activityid, String goodsid, double amount) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("activityid",NetUtil.isStringNull(activityid));
        urlMap.put("pid",NetUtil.isStringNull(goodsid));
        urlMap.put("count",String.valueOf(amount));
        HomeNet.getHomeApi().updateShopcart(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<String> response) {
                       if (TextUtils.equals(response.getCode(), "200")) {
                           if (!TextUtils.isEmpty(response.getExtention()))
                           {
                               mView.updateResult();
                               CartCountManage.newInstance().refresh(Integer.parseInt(response.getExtention()));
                           }
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       super.onError(e);
                   }
               });
    }

    public void deleteShopCart(BaseActivity activity, HashMap<String, ArrayList<String>> arrayList) {
        String                  json      = new Gson().toJson(arrayList);
        json=json.replace("\\","");
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pids", json);
        HomeNet.getHomeApi().deleteShopcart(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<String> response) {
                       activity.getShortToastByString(response.getAlertmsg());
                       if (TextUtils.equals(response.getCode(), "200")) {
                           if (!TextUtils.isEmpty(response.getExtention()))
                           {
                               CartCountManage.newInstance().refresh(Integer.parseInt(response.getExtention()));
                               mView.deleteSuccess();
                           }
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void checkMoney(BaseActivity activity, String cartdata) {
        cartdata=cartdata.replace("\\","");
        L.i(cartdata);
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("cartdata",NetUtil.isStringNull(cartdata));
        HomeNet.getHomeApi().checkMoney(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<String>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<String> response) {
                       mView.showCheckResult(response);
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void saveCoupon(BaseActivity activity, String couponid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("couponid",NetUtil.isStringNull(couponid));
        HomeNet.getHomeApi().saveCoupon(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<SavaCouponModel>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<SavaCouponModel> response) {
                       if (response.getData()!=null) {
                           mView.showSaveResult(couponid);
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

    String s="{ \"discount \": \" \", \"couponid \": \"218234 \", \"product \":{ \"4 \":{ \"activityid \": \"4 \", \"couponid \": \" \", \"goodsList \":[{ \"pid \": \"3409 \", \"price \": \"13.80 \", \"count \": \"40 \"}]}, \"25 \":{ \"activityid \": \"25 \", \"couponid \": \" \", \"goodsList \":[{ \"pid \": \"6743 \", \"price \": \"2.50 \", \"count \": \"41 \"}]}, \"single \":{ \"activityid \": \"single \", \"couponid \": \" \", \"goodsList \":[{ \"couponid \": \" \", \"count \": \"3 \", \"price \": \"8.50 \", \"pid \": \"5757 \"}]}}, \"needpay \": \"572.08 \"}";
}
