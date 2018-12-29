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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.L;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

/**
 * Created time  2018/7/31 0031
 * @author : mcx
 * 类描述 : 
 */

public class CommendProductAdapter extends BaseQuickAdapter<DrugModel,BaseViewHolder> {

    public CommendProductAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugModel item) {
        ImageView product  = helper.getView(R.id.iv_product);
        TextView  oldPrice = helper.getView(R.id.tv_oldprice);
        TextView  price = helper.getView(R.id.tv_price);
        TextView  title    = helper.getView(R.id.tv_title);
        TextView  time    = helper.getView(R.id.tv_validity_time);
        RelativeLayout rl_image = helper.getView(R.id.rl_image);

        String mType=item.getType()+"";

        title.setText(item.getTitle());
        price.setText(item.getPrice());
        if (TextUtils.isEmpty(item.validend))
        {
            time.setVisibility(View.GONE);
        }
        else
        {
            time.setText("有效期：" +DateUtil.formatyyyyMMdd(DensityUtils.parseLong(item.validend)*1000) );
        }
        oldPrice.setText("折后约"+item.disprice);
        if (TextUtils.isEmpty(NetUtil.getToken()))
        {
            oldPrice.setVisibility(View.GONE);
        }

        if (helper.getView(R.id.rl_image)!=null)
        {
            ViewGroup.LayoutParams layoutParams = rl_image.getLayoutParams();
            layoutParams.height = (DensityUtils.getScreenWidth()-DensityUtils.dp2px(UiUtil.getContext(), 60))/2;
            rl_image.setLayoutParams(layoutParams);
        }

        GlideUtil.loadImg(UiUtil.getContext(), item.getThumb(), product);
        helper.setText(R.id.tv_scqy,item.getScqy())
              .setText(R.id.tv_price,item.getPrice())
              .setText(R.id.tv_gg,item.getGg())
              .setText(R.id.tv_sale,"库存"+item.getSales())
              .setGone(R.id.iv_presale,!TextUtils.equals(item.getPresale(),"0"));
        if(TextUtils.equals(mType,"1")){
            // 特价
            helper.setText(R.id.tv_title,item.getTitle());
            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }else if(TextUtils.equals(mType,"2")){
            // 特价
            helper.setText(R.id.tv_title,item.getTitle());
            oldPrice.setVisibility(View.GONE);
            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }else if(TextUtils.equals(mType, "3")){
            // 满赠
            TextView tv_levelnote = helper.getView(R.id.tv_levelnote);
            if (tv_levelnote!=null)
            {
                tv_levelnote.setVisibility(TextUtils.isEmpty(item.getLevelnote()) ? View.INVISIBLE : View.VISIBLE);
                tv_levelnote.setText(item.getLevelnote());
            }

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
            oldPrice.setVisibility(View.GONE);
        }
    }
}

