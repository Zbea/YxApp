package com.yx.Pharmacy.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.CreditData;
import com.yx.Pharmacy.util.DateUtil;

import java.util.List;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :商品清单列表
 */

public class IntegralListAdapter extends BaseQuickAdapter<CreditData.CreditModel,BaseViewHolder> {

    public IntegralListAdapter(int layoutResId, @Nullable List<CreditData.CreditModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditData.CreditModel item) {
        TextView tv_type=helper.getView(R.id.tv_type);
        if(item.type==0){
            tv_type.setBackgroundResource(R.drawable.shape_point_zise);
            tv_type.setText("支");
            helper.setText(R.id.tv_integral_case,"-"+item.amount);
        }else {
            tv_type.setBackgroundResource(R.drawable.shape_point_orange);
            tv_type.setText("收");
            helper.setText(R.id.tv_integral_case,"+"+item.amount);
        }
        helper.setText(R.id.tv_note,item.note)
               .setText(R.id.tv_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.addtime+"000")));
    }
}
