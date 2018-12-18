package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   ICreateOrderView
 *  @创建者:   CC
 *  @创建时间:  2018/8/8 22:09
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.CreateOrderModel;
import com.yx.Pharmacy.model.MyOrderNumModel;

public interface ICreateOrderView {
    void showCreateResult(CreateOrderModel data);
    void resultCartNum(MyOrderNumModel data);

}
