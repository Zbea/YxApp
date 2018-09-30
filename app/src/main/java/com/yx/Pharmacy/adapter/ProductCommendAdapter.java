package com.yx.Pharmacy.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.ProductDetailModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.adapter
 *  @文件名:   ProductCommendAdapter
 *  @创建者:   CC
 *  @创建时间:  2018/7/21 17:06
 *  @描述：    TODO
 */

public class ProductCommendAdapter
        extends BaseQuickAdapter<ProductDetailModel.ProductBean,BaseViewHolder>
{
    public ProductCommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductDetailModel.ProductBean item) {
        helper.setText(R.id.tv_title,item.title).setText(R.id.tv_name,item.price);
        ImageView product = helper.getView(R.id.iv_product);
        GlideUtil.loadImg(UiUtil.getContext(),item.thumb,product);
    }
}
