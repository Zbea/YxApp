package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.widget.AmountView;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class AskAfterSaleAdapter extends BaseQuickAdapter<OrderModel.Goods,BaseViewHolder> {
    private Context context;
    private ChangeListener listener;
    public interface  ChangeListener{
        void countChange();
    }
    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public AskAfterSaleAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<OrderModel.Goods> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel.Goods item) {
        ImageView iv_product=helper.getView(R.id.iv_product);
        GlideUtil.loadImg(context,item.thumb,iv_product);
        helper.setText(R.id.tv_title,item.title)
                .setText(R.id.tv_order_company,item.scqy)
                .setText(R.id.tv_price,item.disprice)
                .setText(R.id.tv_order_unit,item.gg);
        ImageView iv_is_select=helper.getView(R.id.iv_is_select);
        if(item.isSelect){
            iv_is_select.setImageResource(R.drawable.jizhu_check);
        }else {
            iv_is_select.setImageResource(R.drawable.jizhu_uncheck);
        }
        AmountView amountView = helper.getView(R.id.amount_view);
        amountView.setMinNum(1);
        amountView.setAddNum(1);
        amountView.setGoods_storage(item.cartcount);
        amountView.setAmount(item.count);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount,boolean isEdit) {
//                item.count = amount;
                getData().get(helper.getLayoutPosition()).count=amount;
                if(listener!=null)listener.countChange();
            }
        });
    }
}
