package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.CouponModel;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class CouponListAdapter extends BaseQuickAdapter<CouponModel,BaseViewHolder> {
    private MyListener listener;
    public interface  MyListener{
        void toUse(int couponid);
    }
    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    private Context context;
    private int type;
    public CouponListAdapter(Context context,int type,@Nullable List<CouponModel> data) {
        super(R.layout.item_coupon, data);
        this.context=context;
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CouponModel item) {
        TextView tv_coupon_price=helper.getView(R.id.tv_coupon_price);
        TextView tv_coupon_use_range=helper.getView(R.id.tv_coupon_use_range);
        TextView tv_coupon_use_type=helper.getView(R.id.tv_coupon_use_type);

        helper.setGone(R.id.iv_isnew,item.getIsnew()==1);//首单显示

        helper.setText(R.id.tv_coupon_price,""+item.getCouponprice())
                .setText(R.id.tv_coupon_use_range,item.getCouponcontent())
                .setText(R.id.tv_coupon_couponscene,item.getCouponscene())
                .setText(R.id.tv_coupon_time_limit,item.getCouponovertime());

        if(item.getCoupontype()==1){
            helper.setText(R.id.tv_coupon_use_type,"【单品券】");
        }else if(item.getCoupontype()==2){
            helper.setText(R.id.tv_coupon_use_type,"【专区券】");
        }else if(item.getCoupontype()==3){
            helper.setText(R.id.tv_coupon_use_type,"【通用券】");
        }
        ImageView iv_bg=helper.getView(R.id.iv_bg);
        if(type==Constants.type_useful){//可使用
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.VISIBLE);
            iv_bg.setImageResource(R.drawable.kyyhqbj);
            tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_741177));
            tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_741177));
        }else if(type==Constants.type_used){//已使用
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.GONE);
            iv_bg.setImageResource(R.drawable.ysyyhqbj);
            tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_606060));
        }else {//过期
            helper.getView(R.id.tv_coupon_use_rightnow).setVisibility(View.GONE);
            iv_bg.setImageResource(R.drawable.ygqyhqbj);
            tv_coupon_price.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            tv_coupon_use_range.setTextColor(mContext.getResources().getColor(R.color.color_606060));
            tv_coupon_use_type.setTextColor(mContext.getResources().getColor(R.color.color_606060));
        }

        /*helper.getView(R.id.tv_coupon_use_rightnow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.toUse(item.getCouponid());
            }
        });*/


    }
}
