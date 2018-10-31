package com.yx.Pharmacy.presenter;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.ICommendView;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created time  2018/7/31 0031
 * @author : mcx
 * 类描述 :
 */

public class CommendProductPresenter {
    private ICommendView mView;
    private int mPage;

    public CommendProductPresenter(ICommendView view) {
        this.mView = view;
    }

    public void loadProduct(BaseActivity activity, String levelid, int curtype, boolean isUpOrder) {
        mPage = 1;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",mPage+"");

        urlMap.put("levelid",levelid);

        if(curtype==1){
            urlMap.put("pricesort",isUpOrder?"1":"2");
            urlMap.put("salesort","");
            urlMap.put("colligatesort","");
        }else if(curtype==2){
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
                            mView.getProductListResult(response.getData(),response.getExtention());
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

    public void moreProduct(BaseActivity activity, String levelid, int curtype, boolean isUpOrder) {
        mPage ++ ;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",mPage+"");
        urlMap.put("levelid",levelid);
        if(curtype==1){
            urlMap.put("pricesort",isUpOrder?"1":"2");
        }else if(curtype==2){
            urlMap.put("salesort",isUpOrder?"1":"2");
        }else {
            urlMap.put("colligatesort",isUpOrder?"1":"0");
        }

        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                    @Override
                    public void onSuccess(BasisBean<List<DrugModel>> response) {
                        if (response.getData()!=null) {
                            mView.addProductListResult(response.getData());
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

    public void loadProduct(BaseActivity activity, String levelid, int curtype, boolean isUpOrder,int diff) {
        mPage = 1;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",mPage+"");

        if (diff==0)
        {
            urlMap.put("levelid",levelid);
        }
        else
        {
            urlMap.put("goodstype",NetUtil.isStringNull(levelid));
        }

        if(curtype==1){
            urlMap.put("pricesort",isUpOrder?"1":"2");
            urlMap.put("salesort","");
            urlMap.put("colligatesort","");
        }else if(curtype==2){
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
                           mView.getProductListResult(response.getData(),response.getExtention());
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

    public void moreProduct(BaseActivity activity, String levelid, int curtype, boolean isUpOrder,int diff) {
        mPage ++ ;
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pagenum",mPage+"");
        if (diff==0)
        {
            urlMap.put("levelid",levelid);
        }
        else
        {
            urlMap.put("goodstype",NetUtil.isStringNull(levelid));
        }
        if(curtype==1){
            urlMap.put("pricesort",isUpOrder?"1":"2");
        }else if(curtype==2){
            urlMap.put("salesort",isUpOrder?"1":"2");
        }else {
            urlMap.put("colligatesort",isUpOrder?"1":"0");
        }

        HomeNet.getHomeApi().getProductList(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<DrugModel>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<DrugModel>> response) {
                       if (response.getData()!=null) {
                           mView.addProductListResult(response.getData());
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
