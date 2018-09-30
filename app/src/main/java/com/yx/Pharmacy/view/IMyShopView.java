package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IMyShopView
 *  @创建者:   CC
 *  @创建时间:  2018/7/23 0:29
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.AddressModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.StoreDetailModel;
import com.yx.Pharmacy.model.StoreTypeModel;
import com.yx.Pharmacy.model.UploadModel;

import java.util.List;

public interface IMyShopView {
    void showShopData(List<MyShopModel> data);

    void showStoreType(List<StoreTypeModel> data);

    void showAddressList(List<AddressModel> data);

    void showUploadResult(UploadModel data);

    void showStoreDetail(StoreDetailModel data);

    void showAdvanceData(HomeAdvanceModel data);
}
