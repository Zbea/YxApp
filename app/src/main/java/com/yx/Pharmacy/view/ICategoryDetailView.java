package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   ILoginView
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import android.widget.ImageView;

import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;

import java.util.List;

public interface ICategoryDetailView {
    void getProductListResult(List<DrugModel> data);
    void addProductListResult(List<DrugModel> data);
    void showAddResult(AddShopCartModel data, DrugModel item, ImageView imgview);
    void getShopCarNum(String carnum);
    void noProductListData();
    void onErrorPage();
}
