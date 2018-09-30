package com.yx.Pharmacy.view;

import com.yx.Pharmacy.model.DrugModel;

import java.util.List;

/**
 * Created time  2018/7/31 0031
 * @author : mcx
 * 类描述 : 
 */

public interface ICommendView {
    void getProductListResult(List<DrugModel> data, String extention);

    void addProductListResult(List<DrugModel> data);
}
