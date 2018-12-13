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
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.fragment.OrderFragment;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.SPUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.OrderItemView;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderModel,BaseViewHolder> {
    private Context context;
    private ClickListener listener;
    private boolean isAfterSale=false;
    public interface  ClickListener{
        void checkWuliu(String orderid);
        void cancleOrder(String orderid, int position);
        void checkTansfer(String orderid, int position);
        void goPay(String price, String orderid);
        void bugAgain(String orderid);
        void notifySendOrder(String orderid);
        void comfirmOrder(String orderid, int position);
        void askForAfterSale(String orderid);
        void cancelOrderBack(String orderbackid, int layoutPosition);
        void tuihuo(String orderbackid, int layoutPosition);
        void gotoDetails(String orderid, int layoutPosition);
    }
    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public OrderAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<OrderModel> data,boolean isAfterSale) {
        super(layoutResId, data);
        this.context=context;
        this.isAfterSale=isAfterSale;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderModel item) {
        helper.setText(R.id.tv_order_code,"订单编号:"+item.orderid);
        if(!isAfterSale){//非售后
            if(item.status==1){//去支付
                helper.setText(R.id.tv_order_state,"待付款");
                helper.setText(R.id.tv_order_todo,"去支付");
                helper.setText(R.id.tv_cancle_order,"取消订单");
                helper.getView(R.id.tv_cancle_order).setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(item.payment_type))
                {
                    helper.setText(R.id.tv_check_wuliu,"转账截图");
                    helper.getView(R.id.tv_check_wuliu).setVisibility(View.VISIBLE);
                }
            }else if(item.status==2){//待发货
                helper.setText(R.id.tv_order_state,"待发货");
                helper.setText(R.id.tv_order_todo,"提醒发货");
                helper.setText(R.id.tv_cancle_order,"申请售后");
//                helper.setText(R.id.tv_check_wuliu,"申请售后");
                helper.getView(R.id.tv_check_wuliu).setVisibility(View.GONE);
//                if(item.order_back){
//                    helper.getView(R.id.tv_check_wuliu).setVisibility(View.VISIBLE);
//                }else {
//                    helper.getView(R.id.tv_check_wuliu).setVisibility(View.GONE);
//                }
                if(item.order_back){
                    helper.getView(R.id.tv_cancle_order).setVisibility(View.VISIBLE);
                }else {
                    helper.getView(R.id.tv_cancle_order).setVisibility(View.GONE);
                }
            } else if(item.status==9){//已完成
                helper.setText(R.id.tv_order_state,"已完成");
                helper.setText(R.id.tv_order_todo,"再次采购");
                helper.setText(R.id.tv_cancle_order,"申请售后");
//                helper.getView(R.id.tv_cancle_order).setVisibility(View.VISIBLE);
                if(item.order_back){
                    helper.getView(R.id.tv_cancle_order).setVisibility(View.VISIBLE);
                }else {
                    helper.getView(R.id.tv_cancle_order).setVisibility(View.GONE);
                }
                helper.getView(R.id.tv_check_wuliu).setVisibility(View.VISIBLE);
            }else if(item.status==8){
                helper.setText(R.id.tv_order_state,"已取消");
                helper.setText(R.id.tv_order_todo,"再次采购");
                helper.getView(R.id.tv_cancle_order).setVisibility(View.GONE);
                helper.getView(R.id.tv_check_wuliu).setVisibility(View.GONE);
            }else if(item.status==7){
                helper.setText(R.id.tv_order_state,"待收货");
                helper.setText(R.id.tv_order_todo,"确认收货");
                helper.getView(R.id.tv_cancle_order).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_check_wuliu).setVisibility(View.GONE);
                helper.setText(R.id.tv_cancle_order,"查看物流");
            }else if(item.status==3){
                helper.setText(R.id.tv_order_state,"待收货");
                helper.setText(R.id.tv_order_todo,"确认收货");
                helper.getView(R.id.tv_cancle_order).setVisibility(View.GONE);
                helper.getView(R.id.tv_check_wuliu).setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_order_total_num,"共"+item.number+"件商品 合计:");
            helper.getView(R.id.ll_bottom).setVisibility(View.VISIBLE);

        }

        helper.setText(R.id.tv_order_total_price,"¥"+item.price);

        if(item.goodsList.size()==1){//只有一种商品
            helper.getView(R.id.rl_only_one_product).setVisibility(View.VISIBLE);
            helper.getView(R.id.horizontal_scrollview).setVisibility(View.GONE);
            ImageView iv_product_thumb=helper.getView(R.id.iv_product_thumb);
            GlideUtil.loadImg(context,item.goodsList.get(0).thumb,iv_product_thumb);
            helper.setText(R.id.tv_order_title,item.goodsList.get(0).title);
            helper.setText(R.id.tv_order_company,item.goodsList.get(0).scqy);
            helper.setText(R.id.tv_price,item.goodsList.get(0).price);
            TextView tv_oldprice=helper.getView(R.id.tv_oldprice);
            tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); //中划线
            tv_oldprice.setText(item.goodsList.get(0).oprice);
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
                if(!isAfterSale){//非售后
                    if(item.status==9){//申请售后
                        if(listener!=null)listener.askForAfterSale(item.orderid);
                    }else if(item.status==2)
                    {
                        ((BaseActivity)context).contactService();
                    }
                    else if(item.status==7){
                        if(listener!=null)listener.checkWuliu(item.orderid);
                    }else {//取消订单
                        if(listener!=null)listener.cancleOrder(item.orderid,helper.getLayoutPosition());
                    }
                }
            }
        });
        helper.getView(R.id.tv_order_todo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAfterSale) {//非售后
                    if(item.status==1){//去支付
                        if(listener!=null)listener.goPay(item.price,item.orderid);
                    }else if(item.status==2||item.status==3) {//提醒发货
                        if(listener!=null)listener.notifySendOrder(item.orderid);
                    }else if(item.status==8||item.status==9) {//再次采购
                        if(listener!=null)listener.bugAgain(item.orderid);
                    }else if(item.status==7){//确认收货
                        if(listener!=null)listener.comfirmOrder(item.orderid,helper.getLayoutPosition());
                    }
                }
            }
        });
        //新添加的查看物流按钮，之前status=7的时候也有查看物流，但是用的是tv_cancle_order，原本只有2个按钮，需求变动加了第三个
        helper.getView(R.id.tv_check_wuliu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener==null)
                    return;
                if (item.status==1)
                {
                    listener.checkTansfer(item.orderid,helper.getLayoutPosition());
                }
                else
                {
                    listener.checkWuliu(item.orderid);
                }
            }
        });
    }
}