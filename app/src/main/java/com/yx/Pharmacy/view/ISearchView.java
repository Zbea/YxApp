package com.yx.Pharmacy.view;


import android.widget.ImageView;

import com.yx.Pharmacy.model.AddShopCartModel;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.model.SearchAutoModel;
import com.yx.Pharmacy.model.TagModel;

import java.util.List;

public interface ISearchView {
    void getHotSearchList(List<TagModel> data);
    void getAutoSearchList(List<SearchAutoModel> data);
}
