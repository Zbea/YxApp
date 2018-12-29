package com.yx.Pharmacy.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.model.SearchAutoModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

import java.util.List;

 /**
   * Author: Zbea
   * Date: 2018/12/19 11:06
   * Description:
  */
public class SearchAutoAdapter extends BaseQuickAdapter<SearchAutoModel,BaseViewHolder> {

    public SearchAutoAdapter(int layoutResId, @Nullable List<SearchAutoModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchAutoModel item) {
        TextView title = helper.getView(R.id.tv_title);
        title.setText(item.title);

    }
}
