package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   IMiaoShaView
 *  @创建者:   CC
 *  @创建时间:  2018/8/11 14:36
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.DrugModel;

import java.util.List;

public interface IMiaoShaView {
    void getProductListResult(List<DrugModel> data, String extention);

    void moreProductListResult(List<DrugModel> data);
}
