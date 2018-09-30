package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IMyCollectView
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.SaleRecordModel;

import java.util.List;

public interface ISaleRecordView {
    void getSaleRecordList(List<SaleRecordModel> data);
    void refreshSaleRecordList(List<SaleRecordModel> data);
    void noSaleRecordList();
    void showAddResult(AddShopCartModel data);
    void getShopCarNum(String count);
}
