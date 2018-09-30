package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IMyCollectView
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.OrderModel;

import java.util.List;

public interface IAfterOrderListView {
    void getOrderList(List<OrderModel> data);
    void refreshOrderList(List<OrderModel> data);
    void noOrderList();
    void cancelOrderBack(int position);

    void onErrorPage();
    void tuihuoBack();
}
