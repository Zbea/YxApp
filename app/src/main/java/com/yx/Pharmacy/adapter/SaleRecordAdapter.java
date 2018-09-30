package com.yx.Pharmacy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.SaleRecordModel;
import com.yx.Pharmacy.util.ComMethodsUtil;
import com.yx.Pharmacy.util.DateUtil;

import java.util.List;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :商品清单列表
 */

public class SaleRecordAdapter extends BaseQuickAdapter<SaleRecordModel,BaseViewHolder> {

    public SaleRecordAdapter(int layoutResId, @Nullable List<SaleRecordModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleRecordModel item) {
//        ImageView iv_sale_record_head=helper.getView(R.id.iv_sale_record_head);
//        GlideUtil.loadRoundImg(mContext,);

        helper.setText(R.id.tv_username, ComMethodsUtil.phoneFormat(item.mobile))
               .setText(R.id.tv_date, DateUtil.formatyyyyMMddHHmmss(Long.valueOf(item.add_time+"000")))
                .setText(R.id.tv_sale_num,"x"+item.count);
    }
}
