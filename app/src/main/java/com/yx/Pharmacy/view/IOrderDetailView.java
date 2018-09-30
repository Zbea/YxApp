package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IMyCollectView
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.OrderModel;

import java.util.List;

public interface IOrderDetailView {
    void getOrderDetailData(OrderModel data);
    void showAdvanceData(HomeAdvanceModel data);
    void comfirmBack();

    void onErrorPage();

}
