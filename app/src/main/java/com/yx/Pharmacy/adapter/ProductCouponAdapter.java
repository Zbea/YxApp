package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.CouponModel;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class ProductCouponAdapter extends BaseQuickAdapter<CouponModel,BaseViewHolder> {
    private MyListener listener;
    public interface  MyListener{
        void toSave(int couponid);
    }
    public void setListener(MyListener listener) {
        this.listener = listener;
    }
    private Context context;
    public ProductCouponAdapter(Context context,@Nullable List<CouponModel> data) {
        super(R.layout.item_coupon, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CouponModel item) {
        TextView tv_coupon_price=helper.getView(R.id.tv_coupon_price);
        TextView tv_coupon_use_range=helper.getView(R.id.tv_coupon_use_range);
        TextView tv_coupon_use_type=helper.getView(R.id.tv_coupon_use_type);


        helper.setText(R.id.tv_coupon_price,""+item.getCouponprice())
                .setText(R.id.tv_coupon_use_range,item.getCouponcontent())
                .setText(R.id.tv_coupon_couponscene,item.getCouponscene())
                .setText(R.id.tv_coupon_time_limit,item.getCouponovertime());

        if(item.getCoupontype()==1){
            helper.setText(R.id.tv_coupon_use_type,"【单品卷】");
        }else if(item.getCoupontype()==2){
            helper.setText(R.id.tv_coupon_use_type,"【专区卷】");
        }else if(item.getCoupontype()==3){
            helper.setText(R.id.tv_coupon_use_type,"【通用卷】");
        }
        ImageView iv_bg=helper.getView(R.id.iv_bg);
        if(item.getCouponstate()==1){//已领取
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.GONE);
            iv_bg.setImageResource(R.drawable.ylqyhqbj);
            tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_606060));
        }else {
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.VISIBLE);
            iv_bg.setImageResource(R.drawable.kyyhqbj);
            tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_741177));
        }
        helper.setText(R.id.tv_coupon_use_rightnow,"领取");
        helper.getView(R.id.tv_coupon_use_rightnow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.toSave(item.getCouponid());
            }
        });


    }
}
