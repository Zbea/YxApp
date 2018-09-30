package com.yx.Pharmacy.model;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.model
 *  @文件名:   CreateOrderIntentModel
 *  @创建者:   CC
 *  @创建时间:  2018/8/8 21:37
 *  @描述：    TODO
 */

import java.io.Serializable;
import java.util.HashMap;

public class CreateOrderIntentModel implements Serializable {
    public CreateOrderIntentModel(HashMap<String, Object> product,
                                  String couponid,
                                  String needpay,
                                  String count,
                                  String discount)
    {
        this.product = product;
        this.couponid = couponid;
        this.needpay = needpay;
        this.count = count;
        this.discount = discount;
    }

    public HashMap<String, Object> product;
    public HashMap<String, Object> address;
    public String                  couponid;
    public String                  needpay;
    public String                  usemoney;
    public String                  count;
    public String                  discount;
    public String                  note;
}
