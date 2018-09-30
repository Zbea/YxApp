package com.yx.Pharmacy.model;

import java.util.List;

/**
 * Created by KID on 2018/7/26.
 */

public class CouponData {
    //可使用
    private List<CouponModel>useful;
    //已使用
    private List<CouponModel>used;
    //已过期
    private List<CouponModel>outtime;

    public CouponData() {
    }

    public List<CouponModel> getUseful() {
        return useful;
    }

    public void setUseful(List<CouponModel> useful) {
        this.useful = useful;
    }

    public List<CouponModel> getUsed() {
        return used;
    }

    public void setUsed(List<CouponModel> used) {
        this.used = used;
    }

    public List<CouponModel> getOuttime() {
        return outtime;
    }

    public void setOuttime(List<CouponModel> outtime) {
        this.outtime = outtime;
    }
}
