package com.yx.Pharmacy.view;

import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.PayOrderModel;

/**
 * Created time  2018/8/9 0009
 * @author : mcx
 * 类描述 : 
 */

public interface IPayView {
    void showPay(PayOrderModel data, String alertmsg);
}
