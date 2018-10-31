package com.yx.Pharmacy.util;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.util
 *  @文件名:   SelectStoreUtil
 *  @创建者:   CC
 *  @创建时间:  2018/9/23 10:38
 *  @描述：    TODO
 */

import android.app.Activity;
import android.text.TextUtils;

import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.dialog.ChooseStoreDialog;
import com.yx.Pharmacy.manage.CartCountManage;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.net.HomeNet;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.net.ProgressNoCode;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelectStoreUtil {
    private Activity mContext;
    private ChooseStoreDialog mChooseStoreDialog;
    private OnSelectStoreListener mListener;

    public SelectStoreUtil(Activity constext,OnSelectStoreListener listener) {
        this.mContext = constext;
        this.mListener = listener;
        loadMyShop(mContext,true);
    }


    public void loadMyShop(Activity activity, boolean isShow) {
        HashMap<String, String> urlMap = NetUtil.getUrlMap();
        urlMap.put("doplace","home");
        HomeNet.getHomeApi().getMyShop(urlMap).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new ProgressNoCode<BasisBean<List<MyShopModel>>>(activity, isShow) {
                   @Override
                   public void onSuccess(BasisBean<List<MyShopModel>> response) {
                       if (response.getData()!=null) {
                           showShopData(response.getData());
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       LogUtils.e("error========="+e.toString());
                       super.onError(e);
                   }
               });
    }

    public void showShopData(List<MyShopModel> data) {
        if (data != null && data.size() > 0){
            // 修改门店认证状态
            SPUtil.putBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, true);
        }
        String storename = SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
        if (data != null && data.size() > 0&& TextUtils.isEmpty(storename)) {
            if (data.size()==1) {
                MyShopModel myShopModel = data.get(0);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_ITEM_ID, myShopModel.itemid);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORE_ID, myShopModel.storeid);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORENAME, myShopModel.storename);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_ADDRESS, myShopModel.storeaddress);
                CartCountManage.newInstance().refresh(Integer.parseInt(myShopModel.carcount));
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_AVATAR, myShopModel.avatar);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_MOBILE, myShopModel.mobile);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_TRUENAME, myShopModel.truename);
                if (mListener!=null) {
                    mListener.onSelect();
                }
                return;
            }
            showChooseStoreDialog(data);
        }
    }


    private void showChooseStoreDialog(List<MyShopModel> data) {
        if (mChooseStoreDialog!=null) {
            if (mChooseStoreDialog.isShown()) {
                return;
            }
        }
        mChooseStoreDialog = new ChooseStoreDialog(mContext, data);
        mChooseStoreDialog.builder().show();
        mChooseStoreDialog.setDialogClickListener(new ChooseStoreDialog.DialogClickListener() {
            @Override
            public void select(MyShopModel myShopModel) {
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_ITEM_ID, myShopModel.itemid);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORE_ID, myShopModel.storeid);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORENAME, myShopModel.storename);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_ADDRESS, myShopModel.storeaddress);
                CartCountManage.newInstance().refresh(Integer.parseInt(myShopModel.carcount));
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_AVATAR, myShopModel.avatar);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_MOBILE, myShopModel.mobile);
                SPUtil.putString(UiUtil.getContext(), Constants.KEY_TRUENAME, myShopModel.truename);
                if (mListener!=null) {
                    mListener.onSelect();
                }
            }
        });
    }

    public void setOnSelectStoreListener(OnSelectStoreListener listener){
        this.mListener = listener;
    }

    public interface OnSelectStoreListener{
        public void onSelect();
    }
}
