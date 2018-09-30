package com.yx.Pharmacy.presenter;

import android.app.Activity;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IHomeView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 :
 */

public class HomeDataPresenter {
    private IHomeView mView;
    private int pagenum;

    public HomeDataPresenter(IHomeView view) {
        this.mView = view;
    }

    public void getHomeData(BaseActivity activity) {
        HomeNet.getHomeApi().getHomeData().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<HomeDataModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<HomeDataModel>> response) {
                       if (response.getData()!=null) {
                           mView.showHomeData(response.getData());
                       }else {
                           activity.getShortToastByString(response.getAlertmsg());
                           mView.hideFlash();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       mView.hideFlash();
                       super.onError(e);
                   }
               });
    }

    public void getAdvanceData(BaseActivity activity) {
        HomeNet.getHomeApi().getBannerData().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<HomeAdvanceModel>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<HomeAdvanceModel> response) {
                       if (response.getData()!=null) {
                           mView.showAdvanceData(response.getData());
                       }else {
                           activity.getShortToastByString(response.getAlertmsg());
                           mView.hideFlash();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       mView.hideFlash();
                       super.onError(e);
                   }
               });
    }

    public void loadMyShop(BaseActivity activity,boolean isShow) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("doplace","home");
        HomeNet.getHomeApi().getMyShop(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressNoCode<BasisBean<List<MyShopModel>>>(activity, isShow) {
                   @Override
                   public void onSuccess(BasisBean<List<MyShopModel>> response) {
                       if (response.getData()!=null) {
                           mView.showShopData(response.getData());
                       }else {
                           mView.hideFlash();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       mView.hideFlash();
                       super.onError(e);
                   }
               });
    }

    public void loadProductList(BaseActivity activity) {
        pagenum = 1;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",pagenum+"");
        urlMap.put("ishome","1");
        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<DrugModel>> response) {
                       if (response.getData()!=null) {
                           mView.showProductListResult(response.getData());
                       }else {
                           mView.hideFlash();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       mView.hideFlash();
                       super.onError(e);
                   }
               });
    }

    public void moreProduct(Activity activity) {
        pagenum ++ ;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",pagenum+"");
        urlMap.put("ishome","1");
        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<DrugModel>> response) {
                       mView.addProductListResult(response.getData());
                   }
                   @Override
                   public void onError(Throwable e) {
                       mView.hideFlash();
                       super.onError(e);
                   }
               });
    }
}
