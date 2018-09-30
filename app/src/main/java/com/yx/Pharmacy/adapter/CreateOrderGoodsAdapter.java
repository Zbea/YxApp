package com.yx.Pharmacy.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :商品清单列表
 */

public class CreateOrderGoodsAdapter
        extends BaseQuickAdapter<OrderModel.Goods,BaseViewHolder> {
    public CreateOrderGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel.Goods item) {
        ImageView product = helper.getView(R.id.iv_product);
        GlideUtil.loadImg(UiUtil.getContext(),item.thumb,product);
        TextView oldPrice = helper.getView(R.id.tv_oldprice);
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ImageView iv_presale = helper.getView(R.id.iv_presale);
        iv_presale.setVisibility(TextUtils.equals(item.presale, "0") ? View.GONE : View.VISIBLE);

        TextView title = helper.getView(R.id.tv_title);
        helper.setText(R.id.tv_scqy,item.scqy)
               .setText(R.id.tv_gg,item.gg)
              .setText(R.id.tv_price,item.price)
              .setText(R.id.tv_oldprice,item.oprice)
               .setText(R.id.tv_count,"x"+item.count);


        int type = item.type;
        if(type==1){
            Bitmap   b = null;

            long endtime = DensityUtils.parseLong(item.endtime)*1000;
            long starttime = DensityUtils.parseLong(item.starttime)*1000;
            if (starttime>endtime) {
                b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs_n);
            }else {
                b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
            }
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }else if(type==3){
            // 满赠

            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }else if(type==9){
            // 控销
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_kx);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }else {
            title.setText(item.title);
        }

    }
}
