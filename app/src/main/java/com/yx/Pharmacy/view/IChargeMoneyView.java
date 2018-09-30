package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IChargeMoneyView
 *  @创建者:   CC
 *  @创建时间:  2018/8/9 22:35
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.PayOrderModel;

public interface IChargeMoneyView {
    void showPay(PayOrderModel data, String alertmsg);

    void showCreate(Boolean data, String extention, String alertmsg);

}
