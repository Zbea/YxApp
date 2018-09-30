package com.yx.Pharmacy.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.OrderModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

import java.util.List;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :商品清单列表
 */

public class GoodsListAdapter extends BaseQuickAdapter<OrderModel.Goods,BaseViewHolder> {

    private boolean isAfter;

    public GoodsListAdapter(int layoutResId, @Nullable List<OrderModel.Goods> data,boolean isAfter) {
        super(layoutResId, data);
        this.isAfter=isAfter;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderModel.Goods item) {
        ImageView product = helper.getView(R.id.iv_product);
        GlideUtil.loadImg(UiUtil.getContext(),item.thumb,product);


        TextView title = helper.getView(R.id.tv_title);
//        title.setText(item.title);
        helper.setText(R.id.tv_scqy,item.scqy)
               .setText(R.id.tv_gg,item.gg)
               .setText(R.id.tv_count,"x"+item.count);
        int type=item.type;
        if(type==1){
            Bitmap   b = null;
            b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }else if(type==2){
            // 特价

            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }
        else if(type==3){
            // 满赠
            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
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

        if(!isAfter){
            helper.setText(R.id.tv_price,item.price);
            TextView tv_oldprice=helper.getView(R.id.tv_oldprice);
            tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); //中划线
            tv_oldprice.setText(item.oprice);
        }else {
            helper.setText(R.id.tv_price,item.disprice);
            TextView tv_oldprice=helper.getView(R.id.tv_oldprice);
            tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); //中划线
            tv_oldprice.setText(!TextUtils.isEmpty(item.oprice)?item.oprice:"");
        }


    }
}
