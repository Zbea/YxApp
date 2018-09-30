package com.yx.Pharmacy.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

/**
 * Created time  2018/7/31 0031
 * @author : mcx
 * 类描述 : 
 */

public class CommendProductAdapter extends BaseQuickAdapter<DrugModel,BaseViewHolder> {
    private String mType;

    public CommendProductAdapter(int layoutResId, String type) {
        super(layoutResId);
        mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugModel item) {
        ImageView product  = helper.getView(R.id.iv_product);
        TextView  oldPrice = helper.getView(R.id.tv_oldprice);
        TextView  title    = helper.getView(R.id.tv_title);
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        title.setText("");

        ViewGroup.LayoutParams layoutParams = product.getLayoutParams();
        layoutParams.height = (DensityUtils.getScreenWidth()-DensityUtils.dp2px(UiUtil.getContext(), 60))/2;
        product.setLayoutParams(layoutParams);

        GlideUtil.loadImg(UiUtil.getContext(), item.getThumb(), product);
        helper.setText(R.id.tv_scqy,item.getScqy())
              .setText(R.id.tv_price,item.getPrice())
              .setText(R.id.tv_oldprice,item.getOldprice())
              .setText(R.id.tv_gg,item.getGg())
              .setText(R.id.tv_sale,"已售"+item.getSales())
              .setGone(R.id.iv_presale,!TextUtils.equals(item.getPresale(),"0"));
        if(TextUtils.equals(mType,"2")){
            // 特价
            helper.setText(R.id.tv_title,item.getTitle());

            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }else if(TextUtils.equals(mType, "3")){
            // 满赠

            TextView tv_levelnote = helper.getView(R.id.tv_levelnote);
            tv_levelnote.setVisibility(TextUtils.isEmpty(item.getLevelnote()) ? View.INVISIBLE : View.VISIBLE);
            tv_levelnote.setText(item.getLevelnote());

            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }else if(TextUtils.equals(mType,"9")){
            // 控销

            TextView tv_levelnote = helper.getView(R.id.tv_levelnote);
            tv_levelnote.setVisibility(TextUtils.isEmpty(item.getLevelnote())?View.INVISIBLE:View.VISIBLE);
            tv_levelnote.setText(item.getLevelnote());

            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_kx);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }
    }
}

