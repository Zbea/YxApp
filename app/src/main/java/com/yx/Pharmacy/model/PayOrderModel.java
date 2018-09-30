package com.yx.Pharmacy.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created time  2018/8/9 0009
 * @author : mcx
 * 类描述 : 
 */

public class PayOrderModel {
    public  String pay_code;


    /**
     * appid : wx2e654dca1ada96c7
     * partnerid : 1510324511
     * timestamp : 1533810974
     * noncestr : zhMYfwluGK9MwVuS
     * package : Sign=WXPay
     * prepayid : wx0918361480424786562948660915830987
     * sign : 3E6531CEDFD1390EF8316E0AD590550B
     */

    public String appid;
    public String partnerid;
    public String timestamp;
    public String noncestr;
    @SerializedName("package")
    public String packageX;
    public String prepayid;
    public String sign;

}
