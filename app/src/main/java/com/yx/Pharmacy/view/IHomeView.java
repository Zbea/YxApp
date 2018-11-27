package com.yx.Pharmacy.view;

import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.model.MyShopModel;

import java.util.List;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 : 
 */

public interface IHomeView {
    void showHomeData(List<HomeDataModel> data);

    void showAdvanceData(HomeAdvanceModel data);

    void showShopData(List<MyShopModel> data);

    void showProductListResult(List<DrugModel> data);

    void addProductListResult(List<DrugModel> data);

    void showMessageListResult(HomeAdvanceModel data);

    void hideFlash();
}
