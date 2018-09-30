package com.yx.Pharmacy.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.ShopCartModel;
import com.yx.Pharmacy.util.DensityUtils;

/**
 * Created by KID on 2018/7/17.
 */

public class ShopCouponListAdapter
        extends BaseQuickAdapter<ShopCartModel.CouponListBean,BaseViewHolder> {


    public ShopCouponListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartModel.CouponListBean item) {
        helper.setText(R.id.tv_coupon_price,""+item.couponprice)
                .setText(R.id.tv_coupon_use_range,item.couponcontent)
                .setText(R.id.tv_coupon_couponscene,item.couponscene)
                .setText(R.id.tv_coupon_time_limit,item.couponovertime);
        int coupontype = DensityUtils.parseInt(item.coupontype);
        int couponstate = DensityUtils.parseInt(item.couponstate);
        if(coupontype==1){
            helper.setText(R.id.tv_coupon_use_type,"【单品卷】");
        }else if(coupontype==2){
            helper.setText(R.id.tv_coupon_use_type,"【专区卷】");
        }else if(coupontype==3){
            helper.setText(R.id.tv_coupon_use_type,"【通用卷】");
        }
        ImageView iv_bg=helper.getView(R.id.iv_bg);
        if(couponstate==0){//可使用
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.VISIBLE);
            iv_bg.setImageResource(R.drawable.kyyhqbj);
        }else if(couponstate==1){//已使用
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.GONE);
            iv_bg.setImageResource(R.drawable.ysyyhqbj);
        }else {//过期
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.GONE);
            iv_bg.setImageResource(R.drawable.ygqyhqbj);
        }

        helper.addOnClickListener(R.id.tv_coupon_use_rightnow);
    }
}
