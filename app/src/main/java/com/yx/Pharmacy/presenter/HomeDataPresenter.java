package com.yx.Pharmacy.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.model.SupplierListModel;
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
               .subscribe(new ProgressSubscriber<BasisBean<List<HomeDataModel>>>(activity, true) {
                   @Override
                   public void onSuccess(BasisBean<List<HomeDataModel>> response) {
                       if (response.getCode().equals("200")) {
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
                       if (response.getCode().equals("200")) {
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

    public void getMessageData(BaseActivity activity) {
        HomeNet.getHomeApi().getMessageData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<HomeAdvanceModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<HomeAdvanceModel> response) {
                        if (response.getCode().equals("200")) {
                            mView.showMessageListResult(response.getData());
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
                       if (response.getCode().equals("200")) {
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


    /**
     * 添加商品到购物车
     */
    public void addCartProduct(final BaseActivity activity, final DrugModel item,int count ) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid",item.getItemid()+"");
        urlMap.put("count", count+"");
        HomeNet.getHomeApi().addShopcart(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
                        if (response.getData()!=null) {
                            activity.getShortToastByString("添加成功");
                            mView.showAddResult(response.getData(),item,null);
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
     * 秒杀专区商品秒杀价购买
     */
    public void miaoshaBuy(BaseActivity activity, String pid, int count) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("count", "" + count);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
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

    /**
     * 秒杀专区商品秒杀价购买
     * confirm：是否覆盖购物车内的的秒杀活动商品 0 不覆盖  1覆盖
     */
    public void miaoshaBuy(BaseActivity activity, String pid, String confirm,String count) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("pid", pid);
        urlMap.put("confirm", confirm);
        urlMap.put("count", count);
        HomeNet.getHomeApi().miaoshaBuy(urlMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressNoCode<BasisBean<AddShopCartModel>>(activity, false) {
                    @Override
                    public void onSuccess(BasisBean<AddShopCartModel> response) {
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




}
