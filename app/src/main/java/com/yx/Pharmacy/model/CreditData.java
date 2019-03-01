package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created by KID on 2018/8/6.
 */

public class CreditData {

    public String credit;
    public List<CreditModel>creditlist;

    public static class CreditModel {
        public int amount;
        public String addtime;
        public String note;
        public int type;
        public int jumpType;
        public String orderid;
        public String couponInfo;
        public List<CouponListBean> couponList;
    }

    public static class CouponListBean {


        public String couponcontent;
        public String    couponid;
        public String    couponprice;
        public String couponlimit;
        public String    couponmodel;
        public String    coupontype;
        public String    couponstate;
        public String couponscene;
        public String couponovertime;
        public String    discountprice;
        public String    coupondiscount;
        public String    credit;
        public boolean     isSelectCoupon;
        public boolean     couponEnable;
    }

}
