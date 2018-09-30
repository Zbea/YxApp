package com.yx.Pharmacy.presenter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.presenter
 *  @文件名:   MyShopPresenter
 *  @创建者:   CC
 *  @创建时间:  2018/7/23 0:29
 *  @描述：    TODO
 */

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddressModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.StoreDetailModel;
import com.yx.Pharmacy.model.StoreTypeModel;
import com.yx.Pharmacy.model.UploadModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.util.LogUtils;
import com.yx.Pharmacy.view.IMyShopView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyShopPresenter {
    private IMyShopView mView;

    public MyShopPresenter(IMyShopView view) {
        this.mView = view;
    }
    public void getAdvanceData(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("type","33");
        HomeNet.getHomeApi().getAdData(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<HomeAdvanceModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<HomeAdvanceModel> response) {
                        if (response.getData()!=null) {
                            LogUtils.i("response.getData()========="+response.getData().toString());
                            mView.showAdvanceData(response.getData());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("error========="+e.toString());
                        super.onError(e);
                    }
                });
    }

    public void loadMyShop(BaseActivity activity) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("doplace","mine");
        HomeNet.getHomeApi().getMyShop(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<MyShopModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<MyShopModel>> response) {
                       mView.showShopData(response.getData());
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void getAddressList(BaseActivity activity) {
        HomeNet.getHomeApi().getAddressList().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<AddressModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<AddressModel>> response) {
                       mView.showAddressList(response.getData());
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void getStoreType(BaseActivity activity) {
        HomeNet.getHomeApi().getStoreType().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<StoreTypeModel>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<StoreTypeModel>> response) {
                       mView.showStoreType(response.getData());
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void uploadFile(BaseActivity activity,String path) {
        File               file        = new File(path);
        RequestBody        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file.getAbsoluteFile());
        MultipartBody.Part body        = MultipartBody.Part.createFormData("File", file.getName(), requestFile);
        HomeNet.getHomeApi().uploadFile(body).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<UploadModel>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<UploadModel> response) {
                       mView.showUploadResult(response.getData());
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void addStore(BaseActivity activity, HashMap<String, String> urlMap) {
        HomeNet.getHomeApi().storeAdd(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<Object>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<Object> response) {
                       if (TextUtils.equals(response.getCode(),"200")){
                           activity.getShortToastByString(response.getAlertmsg());
                           EventBus.getDefault().post("addShop");
                           activity.finish();
                       }else {
                           activity.getShortToastByString(response.getAlertmsg());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void getStoreDetail(BaseActivity activity, String itemid) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("itemid",itemid);
        HomeNet.getHomeApi().storeDetail(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<StoreDetailModel>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<StoreDetailModel> response) {
                       if (response.getData()!=null) {
                           mView.showStoreDetail(response.getData());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }
}
