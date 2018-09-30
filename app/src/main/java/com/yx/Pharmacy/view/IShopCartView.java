package com.yx.Pharmacy.view;

import com.yx.Pharmacy.base.BasisBean;
import com.yx.Pharmacy.model.ShopCartModel;

/**
 * Created time  2018/8/2 0002
 * @author : mcx
 * 类描述 : 
 */

public interface IShopCartView {
    void showShopCartList(ShopCartModel data);

    void deleteSuccess();

    void showCheckResult(BasisBean<String> response);

    void showFailView();

    void showSaveResult(String couponid);

    void updateResult();
}
