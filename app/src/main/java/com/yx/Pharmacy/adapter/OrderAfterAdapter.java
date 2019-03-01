package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.widget.OrderItemView;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class OrderAfterAdapter extends BaseQuickAdapter<OrderModel,BaseViewHolder> {
    private Context context;
    private ClickListener listener;
    public interface  ClickListener{
        void checkWuliu(String orderid);
        void cancelOrderBack(String orderbackid, int layoutPosition);
        void tuihuo(String orderbackid, int layoutPosition);
        void gotoDetails(String orderid, int layoutPosition);
    }
    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public OrderAfterAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<OrderModel> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderModel item) {

            //售后    //status：售后订单的状态  0已申请、1审核中，6.已撤销 7通过处理中，8不通过，9已完成
            helper.getView(R.id.tv_cancle_order).setVisibility(View.GONE);
            helper.setText(R.id.tv_order_todo,"撤销申请");
            helper.setText(R.id.tv_order_total_price,"退款金额 ¥ 0");
            if(item.status==0||item.status==1){//去支付
                helper.getView(R.id.ll_bottom).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_order_state,"审核中");
                if (item.type!=1)
                {
                    helper.setText(R.id.tv_cancle_order,"物流");
                    helper.getView(R.id.tv_cancle_order).setVisibility(View.VISIBLE);
                }
                else
                {
                    helper.getView(R.id.tv_cancle_order).setVisibility(View.GONE);
                }
            }else if(item.status==6){//待发货
                helper.getView(R.id.ll_bottom).setVisibility(View.GONE);
                helper.setText(R.id.tv_order_state,"已撤销");
            }else if(item.status==7){//已完成
                helper.getView(R.id.ll_bottom).setVisibility(View.GONE);
                helper.setText(R.id.tv_order_state,"处理中");
            }else if(item.status==8){
                helper.getView(R.id.ll_bottom).setVisibility(View.GONE);
                helper.setText(R.id.tv_order_state,"审核失败");
            } else {
                helper.getView(R.id.ll_bottom).setVisibility(View.GONE);
                helper.setText(R.id.tv_order_state,"已完成");
                helper.setText(R.id.tv_order_total_price,"退款金额 ¥"+item.price);
            }


        helper.setText(R.id.tv_order_total_num,"退货数量 "+item.count);
        helper.setText(R.id.tv_total_number,"总数量 "+item.number);

        helper.setText(R.id.tv_order_old_price,"已付金额 ¥"+item.oprice);
        helper.setText(R.id.tv_order_code,"订单编号:"+item.orderid);
        helper.setText(R.id.tv_order_old_number,"退货单号:"+item.orderbackid);

        if(item.goodsList.size()==1){//只有一种商品
            helper.getView(R.id.rl_only_one_product).setVisibility(View.VISIBLE);
            helper.getView(R.id.horizontal_scrollview).setVisibility(View.GONE);
            ImageView iv_product_thumb=helper.getView(R.id.iv_product_thumb);
            GlideUtil.loadImg(context,item.goodsList.get(0).thumb,iv_product_thumb);
            helper.setText(R.id.tv_order_title,item.goodsList.get(0).title);
            helper.setText(R.id.tv_order_company,item.goodsList.get(0).scqy);
            helper.setText(R.id.tv_price,"折后价："+item.goodsList.get(0).price);
            helper.getView(R.id.tv_price).setVisibility(View.GONE);
            helper.setText(R.id.tv_ph,"批号"+item.goodsList.get(0).ph1);
            TextView tv_oldprice=helper.getView(R.id.tv_oldprice);
//            tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); //中划线
            tv_oldprice.setText("原价："+item.goodsList.get(0).oprice);
            helper.setText(R.id.tv_order_unit,item.goodsList.get(0).gg);
            helper.setText(R.id.tv_num,"x"+item.goodsList.get(0).count);
        }else {//多种商品
            helper.getView(R.id.rl_only_one_product).setVisibility(View.GONE);
            helper.getView(R.id.horizontal_scrollview).setVisibility(View.VISIBLE);
            LinearLayout ll_many_products=helper.getView(R.id.ll_many_products);
            ll_many_products.removeAllViews();
            for (int i = 0; i < item.goodsList.size(); i++) {
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(DensityUtils.dp2px(context,100),DensityUtils.dp2px(context,100));
                lp.setMargins(DensityUtils.dp2px(context,5),0,DensityUtils.dp2px(context,5),0);
                OrderItemView orderItemView=new OrderItemView(context,item.goodsList.get(i));
                orderItemView.setLayoutParams(lp);
                ll_many_products.addView(orderItemView);
            }
        }
        helper.getView(R.id.ll_many_products).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.gotoDetails(item.orderid,helper.getLayoutPosition());
            }
        });

        helper.getView(R.id.tv_cancle_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.tuihuo(item.orderbackid,helper.getLayoutPosition());
            }
        });
        helper.getView(R.id.tv_order_todo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.cancelOrderBack(item.orderbackid,helper.getLayoutPosition());

            }
        });
        //新添加的查看物流按钮，之前status=7的时候也有查看物流，但是用的是tv_cancle_order，原本只有2个按钮，需求变动加了第三个
        helper.getView(R.id.tv_check_wuliu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.checkWuliu(item.orderid);
            }
        });
    }
}