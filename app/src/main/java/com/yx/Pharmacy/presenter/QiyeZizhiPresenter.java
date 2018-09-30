package com.yx.Pharmacy.presenter;

import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.ProgressSubscriber;
import com.yx.Pharmacy.view.IQiyeZizhiView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created time  2018/8/28 0028
 * @author : mcx
 * 类描述 :
 */

public class QiyeZizhiPresenter {
    private IQiyeZizhiView mView;

    public QiyeZizhiPresenter(IQiyeZizhiView view) {
        this.mView = view;
    }

    public void getStoreValide(BaseActivity activity) {
        HomeNet.getHomeApi().getStoreValide().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressSubscriber<BasisBean<List<String>>>(activity, false) {
                   @Override
                   public void onSuccess(BasisBean<List<String>> response) {
                       if (response.getData()!=null) {
                           mView.showList(response.getData());
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
