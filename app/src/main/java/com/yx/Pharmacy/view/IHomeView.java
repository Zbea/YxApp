package com.yx.Pharmacy.view;

import android.widget.ImageView;

import com.yx.Pharmacy.model.AddShopCartModel;
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

    void showProductListResult(List<DrugModel> data);

    void addProductListResult(List<DrugModel> data);

    void showMessageListResult(HomeAdvanceModel data);

    void hideFlash();

    void showAddResult(AddShopCartModel data, DrugModel item, ImageView imgview);

    void ifFuGai();

    void compelete();

}
