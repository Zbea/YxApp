package com.yx.Pharmacy.view;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.view
 *  @文件名:   ILoginView
 *  @创建者:   CC
 *  @创建时间:  2018/7/14 13:11
 *  @描述：    TODO
 */

import com.yx.Pharmacy.model.DrugModel;

public interface IScanView {
    void scanResultProduct(DrugModel data);
    void scanNoData();
    void ifFuGai();
    void compelete();
}
