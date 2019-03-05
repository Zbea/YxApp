package com.yx.Pharmacy.model;

/**
 * Created by KID on 2018/7/26.
 * 优惠卷的model
 */

public class CouponModel {

    private String couponprice;
    private int coupontype;
    private String couponscene;//活动名称
    private int couponid;
    private String couponcontent;
    private String couponovertime;
    private String pid;
    public String leveltype;
    public String levelid;
    public String title;
    public String activityname;
    //查看领取优惠劵
    private String limitprice;
    private int couponmodel;
    private String discountprice;
    private int credit;
    private int couponstate;
    private int isnew;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public CouponModel() {
    }

    public String getLimitprice() {
        return limitprice;
    }

    public void setLimitprice(String limitprice) {
        this.limitprice = limitprice;
    }

    public int getCouponmodel() {
        return couponmodel;
    }

    public void setCouponmodel(int couponmodel) {
        this.couponmodel = couponmodel;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCouponstate() {
        return couponstate;
    }

    public void setCouponstate(int couponstate) {
        this.couponstate = couponstate;
    }

    public String getCouponprice() {
        return couponprice;
    }

    public void setCouponprice(String couponprice) {
        this.couponprice = couponprice;
    }

    public int getCoupontype() {
        return coupontype;
    }

    public void setCoupontype(int coupontype) {
        this.coupontype = coupontype;
    }

    public String getCouponscene() {
        return couponscene;
    }

    public void setCouponscene(String couponscene) {
        this.couponscene = couponscene;
    }

    public int getCouponid() {
        return couponid;
    }

    public void setCouponid(int couponid) {
        this.couponid = couponid;
    }

    public String getCouponcontent() {
        return couponcontent;
    }

    public void setCouponcontent(String couponcontent) {
        this.couponcontent = couponcontent;
    }

    public String getCouponovertime() {
        return couponovertime;
    }

    public void setCouponovertime(String couponovertime) {
        this.couponovertime = couponovertime;
    }
}
