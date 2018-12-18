package com.yx.Pharmacy.manage;

import android.text.TextUtils;

import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;

import java.util.ArrayList;
import java.util.List;

/**
   * Author: Zbea
   * Date: 2018/12/8 9:57
   * Description:  门店管理
  */
public class StoreManage {

    private static StoreManage mStoreManage= null;
    private StoreManageListener mStoreManageListener;
//    private StoresManageListener mStoresManageListener;
    private List<MyShopModel> shopModels=new ArrayList<>();
    private MyShopModel shopModel;//当前门店
    private List<StoreManageListener> storeManageListeners=new ArrayList<>();

    public static StoreManage newInstance()
    {
        if (mStoreManage==null)
        {
            mStoreManage=new StoreManage();
        }
        return mStoreManage;
    }

//    //刷新整个门店集合
//    public void onRefresh(List<MyShopModel> datas)
//    {
//        shopModels=datas;
//        if (mStoresManageListener!=null)
//            mStoresManageListener.onRefresh(datas);
//    }

    //保存当前门店
    public void saveStore(MyShopModel data)
    {
        shopModel=data;
        if (data!=null)
            saveShopStore(data);
        for (int i = 0; i < storeManageListeners.size(); i++) {
            StoreManageListener storeManageListener=storeManageListeners.get(i);
            storeManageListener.onRefresh(data);
        }
    }

    //获取当前门店
    public MyShopModel getStore()
    {
        if (shopModel==null)
        {
            shopModel=new MyShopModel();
            shopModel.itemid=SPUtil.getString(UiUtil.getContext(), Constants.KEY_ITEM_ID);
            shopModel.storeid=SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORE_ID);
            shopModel.storeStutus=SPUtil.getBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY);
            shopModel.storename=SPUtil.getString(UiUtil.getContext(), Constants.KEY_STORENAME);
            shopModel.storeaddress=SPUtil.getString(UiUtil.getContext(), Constants.KEY_ADDRESS);
            shopModel.avatar=SPUtil.getString(UiUtil.getContext(), Constants.KEY_AVATAR);
            shopModel.mobile=SPUtil.getString(UiUtil.getContext(), Constants.KEY_MOBILE);
            shopModel.truename=SPUtil.getString(UiUtil.getContext(), Constants.KEY_TRUENAME);
            shopModel.sign=SPUtil.getString(UiUtil.getContext(), Constants.KEY_SIGN);
            shopModel.coupon=SPUtil.getString(UiUtil.getContext(), Constants.KEY_COUPON);
            shopModel.exchange=SPUtil.getString(UiUtil.getContext(), Constants.KEY_EXCHANGE);
            shopModel.prizetask=SPUtil.getString(UiUtil.getContext(), Constants.KEY_PRIZETASK);
            shopModel.company=TextUtils.isEmpty( SPUtil.getString(UiUtil.getContext(), Constants.KEY_COMPANY))?"深圳市源鑫药业有限公司": SPUtil.getString(UiUtil.getContext(), Constants.KEY_COMPANY);
        }
        return shopModel;
    }

    /**
     * 保存门店信息
     * @param myShopModel
     */
    private void saveShopStore(MyShopModel myShopModel )
    {
        SPUtil.putBoolean(UiUtil.getContext(), Constants.KEY_STORE_CERTIFY, true);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_ITEM_ID, myShopModel.itemid);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORE_ID,myShopModel.storeid);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_STORENAME,myShopModel.storename);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_ADDRESS,myShopModel.storeaddress);
        CartCountManage.newInstance().refresh(Integer.parseInt(myShopModel.carcount));
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_AVATAR,myShopModel.avatar);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_MOBILE,myShopModel.mobile);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_TRUENAME,myShopModel.truename);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_COMPANY, myShopModel.company);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_SIGN,myShopModel.sign);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_COUPON,myShopModel.coupon);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_EXCHANGE,myShopModel.exchange);
        SPUtil.putString(UiUtil.getContext(), Constants.KEY_PRIZETASK, myShopModel.prizetask);
    }

//    //刷新所有门店
//    public StoresManageListener setStoresManageListener(StoresManageListener storesManageListener)
//    {
//        if (mStoresManageListener!=null)
//            mStoresManageListener=storesManageListener;
//        return mStoresManageListener;
//    }
//
//    public interface StoresManageListener
//    {
//        void onRefresh(List<MyShopModel> datas);
//    }

    //刷新门店切换
    public void setStoreManageListener(StoreManageListener storeManageListener)
    {
        if (storeManageListener!=null)
            storeManageListeners.add(storeManageListener);
    }

    public void clearStoreManageListener(StoreManageListener storeManageListener)
    {
        if (storeManageListener!=null)
            storeManageListeners.remove(storeManageListener);
    }

    public interface StoreManageListener
    {
        void onRefresh(MyShopModel data);
    }

}
