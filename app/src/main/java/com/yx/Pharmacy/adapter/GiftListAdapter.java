package com.yx.Pharmacy.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

import java.util.List;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :商品清单列表
 */

public class GiftListAdapter extends BaseQuickAdapter<OrderModel.Goods,BaseViewHolder> {

    public GiftListAdapter(int layoutResId, @Nullable List<OrderModel.Goods> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel.Goods item) {
        ImageView product = helper.getView(R.id.iv_product);
        GlideUtil.loadImg(UiUtil.getContext(),item.thumb,product);

        TextView title = helper.getView(R.id.tv_title);
        title.setText(item.title);
        helper.setText(R.id.tv_gg,item.gg)
               .setText(R.id.tv_count,"x"+item.count);

    }
}
