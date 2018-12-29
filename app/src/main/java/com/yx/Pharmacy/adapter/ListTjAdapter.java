package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.base.BaseListAdapter;
import com.yx.Pharmacy.base.ViewHolder;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

import java.util.List;

/**
 * Created by Zbea on 2017/6/23.
 */

public class ListTjAdapter extends BaseListAdapter<HomeDataModel.GoodlistsBean>
{

    public ListTjAdapter(Context context, List<HomeDataModel.GoodlistsBean> list)
    {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null)
            convertView=mInflater.inflate(R.layout.item_home_product_special,null);
        TextView tvName = ViewHolder.get(convertView,R.id.tv_title);
        ImageView product = ViewHolder.get(convertView,R.id.iv_product);
        TextView price = ViewHolder.get(convertView,R.id.tv_price);
        TextView oldPrice = ViewHolder.get(convertView,R.id.tv_oldprice);
        TextView time = ViewHolder.get(convertView,R.id.tv_validity_time);
        TextView scqy = ViewHolder.get(convertView,R.id.tv_scqy);
        TextView gg = ViewHolder.get(convertView,R.id.tv_gg);
        TextView tv_sale = ViewHolder.get(convertView,R.id.tv_sale);
        RelativeLayout rl_image = ViewHolder.get(convertView,R.id.rl_image);

        HomeDataModel.GoodlistsBean itemBean=getItem(position);
        tvName.setText(itemBean.title);
        GlideUtil.loadImg(UiUtil.getContext(),itemBean.thumb,product);
        if (TextUtils.isEmpty(itemBean.validend))
        {
            time.setVisibility(View.GONE);
        }
        else
        {
            time.setText("有效期：" +DateUtil.formatyyyyMMdd(DensityUtils.parseLong(itemBean.validend)*1000) );
        }
        price.setText(itemBean.price);
        oldPrice.setText("折后约"+itemBean.disprice);
        if (TextUtils.isEmpty(NetUtil.getToken()))
        {
            oldPrice.setVisibility(View.GONE);
        }
        scqy.setText(itemBean.scqy);
        gg.setText(itemBean.gg);
        tv_sale.setText("库存"+itemBean.sales);

        if(TextUtils.equals(itemBean.type,"2"))
        {
            Bitmap b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            tvName.setText(spanString);
            tvName.append(itemBean.title);
            oldPrice.setVisibility(View.GONE);
        }

        return convertView;
    }
}
