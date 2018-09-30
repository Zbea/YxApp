package com.yx.Pharmacy.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.WalletData;
import com.yx.Pharmacy.util.DateUtil;

import java.util.List;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :商品清单列表
 */

public class WalletListAdapter extends BaseQuickAdapter<WalletData.WallatModel,BaseViewHolder> {

    public WalletListAdapter(int layoutResId, @Nullable List<WalletData.WallatModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletData.WallatModel item) {
        TextView tv_type=helper.getView(R.id.tv_type);
        if(item.type==1){//充值
            tv_type.setBackgroundResource(R.drawable.shape_point_zise);
            tv_type.setText("充");
            helper.setText(R.id.tv_integral_case,"+"+item.fee);
        }else if(item.type==2){//收入
            tv_type.setBackgroundResource(R.drawable.shape_point_zise);
            tv_type.setText("收");
            helper.setText(R.id.tv_integral_case,"+"+item.fee);
        }else if(item.type==3){//支出
            tv_type.setBackgroundResource(R.drawable.shape_point_orange);
            tv_type.setText("支");
            helper.setText(R.id.tv_integral_case,"-"+item.fee);
        } else if(item.type==4){//提现
            tv_type.setBackgroundResource(R.drawable.shape_point_zise);
            tv_type.setText("提");
            helper.setText(R.id.tv_integral_case,"-"+item.fee);
        } else if(item.type==7){//退款
            tv_type.setBackgroundResource(R.drawable.shape_point_zise);
            tv_type.setText("退");
            helper.setText(R.id.tv_integral_case,"+"+item.fee);
        }



        helper.setText(R.id.tv_note,item.title)
               .setText(R.id.tv_time, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.paytime+"000")));

    }
}
