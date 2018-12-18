package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IProductDetailView
 *  @创建者:   CC
 *  @创建时间:  2018/7/21 14:37
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.MyShopModel;
import com.yx.Pharmacy.model.ProductDetailModel;

import java.util.List;

public interface IProductDetailView {
    void showProductDetail(ProductDetailModel data);

    void showAddResult(AddShopCartModel data);
    void getShopCarNum(String count);
    void showCollect();
    void showDisCollect();
    void ifFuGai();

    void compelete();

    void errorView();

    void errorNetView();


}
