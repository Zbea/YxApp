package com.yx.Pharmacy.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.MyShopModel;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.adapter
 *  @文件名:   MyShopAdapter
 *  @创建者:   CC
 *  @创建时间:  2018/7/23 0:55
 *  @描述：    TODO
 */

public class MyShopAdapter
        extends BaseQuickAdapter<MyShopModel,BaseViewHolder>
{
    public MyShopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyShopModel item) {
        // 门店状态: 0待审核  1门店已关闭   8审核不通过   9认证通过
        ImageView iv_type = helper.getView(R.id.iv_type);
        ImageView iv_state = helper.getView(R.id.iv_state);
        TextView tv_modifi  = helper.getView(R.id.tv_modifi);
        TextView tv_cut  = helper.getView(R.id.tv_cut);

        helper.setText(R.id.tv_name,item.storename)
              .setText(R.id.tv_business,item.storenumber)
              .setText(R.id.tv_address,item.storeaddress);

        if (TextUtils.equals(item.storetype, "商业")) {
            iv_type.setImageResource(R.drawable.zsbs);
        }else{
            iv_type.setImageResource(R.drawable.ydbs);
        }

        if (TextUtils.equals(item.status, "0")) {
            iv_state.setImageResource(R.drawable.icon_my_store_certify_z);
        }else if (TextUtils.equals(item.status, "1")) {
            iv_state.setImageResource(R.drawable.icon_my_store_certify_b);
        }else if (TextUtils.equals(item.status, "8")) {
            iv_state.setImageResource(R.drawable.icon_my_store_certify_n);
        }else if (TextUtils.equals(item.status, "9")) {
            iv_state.setImageResource(R.drawable.icon_my_store_certify_y);
        }

        helper.setGone(R.id.tv_cut,TextUtils.equals(item.status, "9"))
              .addOnClickListener(R.id.tv_cut)
              .addOnClickListener(R.id.tv_modifi);
    }
}
