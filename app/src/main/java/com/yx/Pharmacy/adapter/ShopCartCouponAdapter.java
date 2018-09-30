package com.yx.Pharmacy.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.util.DensityUtils;

/**
 * Created by KID on 2018/7/17.
 */

public class ShopCartCouponAdapter
        extends BaseQuickAdapter<ShopCartModel.CouponListBean,BaseViewHolder> {



    public ShopCartCouponAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartModel.CouponListBean item) {
        TextView tv_coupon_price=helper.getView(R.id.tv_coupon_price);
        TextView tv_coupon_use_range=helper.getView(R.id.tv_coupon_use_range);
        TextView tv_coupon_use_type=helper.getView(R.id.tv_coupon_use_type);


        helper.setText(R.id.tv_coupon_price,""+item.couponprice)
                .setText(R.id.tv_coupon_use_range,item.couponcontent)
                .setText(R.id.tv_coupon_couponscene,item.couponscene)
                .setText(R.id.tv_coupon_time_limit,item.couponovertime);
        int coupontype = DensityUtils.parseInt(item.coupontype);
        int couponstate = DensityUtils.parseInt(item.couponstate);
        if(coupontype==1){
            helper.setText(R.id.tv_coupon_use_type,"【单品券】");
        }else if(coupontype==2){
            helper.setText(R.id.tv_coupon_use_type,"【专区券】");
        }else if(coupontype==3){
            helper.setText(R.id.tv_coupon_use_type,"【通用券】");
        }
        ImageView iv_bg=helper.getView(R.id.iv_bg);
        if(couponstate==0){//未领取
            iv_bg.setImageResource(R.drawable.kyyhqbj);
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.VISIBLE);
            if (TextUtils.equals(item.credit, "0")) {
                helper.setText(R.id.tv_coupon_use_rightnow,"领取");
            }else {
                helper.setText(R.id.tv_coupon_use_rightnow,item.credit+"积分兑换");
            }
            tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_741177));
        }else {//有的优惠券
            if (item.couponEnable) {
                iv_bg.setImageResource(R.drawable.kyyhqbj);
                helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_coupon_use_rightnow,"使用");
                tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_741177));
                tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_741177));
                tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            }else {
                helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.GONE);
                iv_bg.setImageResource(R.drawable.bkyyhqbj);
                tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_606060));
                tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_606060));
                tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            }
        }

        helper.addOnClickListener(R.id.tv_coupon_use_rightnow);
    }
}
