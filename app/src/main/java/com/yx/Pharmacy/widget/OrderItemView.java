package com.yx.Pharmacy.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.util.GlideUtil;

/**
 * Created by KID on 2018/7/22.
 */

public class OrderItemView extends RelativeLayout {

    private Context context;

    private TextView tv_order_item_num;
    private ImageView iv_order_item;
    private OrderModel.Goods goods;
    private TextView tv_gift;


    public OrderItemView(Context context,OrderModel.Goods goods) {
        super(context);
        this.context=context;
        this.goods=goods;
        init(context);
    }
//    public OrderItemView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.context=context;
//        init(context);
//    }
//    public OrderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context=context;
//    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_order_item, this);
        tv_order_item_num=findViewById(R.id.tv_order_item_num);
        iv_order_item=findViewById(R.id.iv_order_item);
        tv_gift=findViewById(R.id.tv_gift);
        setModel(goods);
    }
    public void setModel(OrderModel.Goods goods){
        if(!TextUtils.isEmpty(goods.thumb)){
            GlideUtil.loadImg(context,goods.thumb,iv_order_item);
        }
        tv_order_item_num.setText(String.valueOf(goods.count));
        if(goods.goodstype==1){//赠品
            tv_gift.setVisibility(View.VISIBLE);
        }else {
            tv_gift.setVisibility(View.GONE);
        }

    }



}
