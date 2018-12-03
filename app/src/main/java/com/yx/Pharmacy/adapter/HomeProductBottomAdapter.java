package com.yx.Pharmacy.adapter;

/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.adapter
 *  @文件名:   HomeProductBottomAdapter
 *  @创建者:   CC
 *  @创建时间:  2018/8/24 1:38
 *  @描述：    TODO
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

public class HomeProductBottomAdapter extends BaseQuickAdapter<DrugModel,BaseViewHolder> {
    public HomeProductBottomAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugModel item) {
        TextView title = helper.getView(R.id.tv_title);
        TextView oldPrice = helper.getView(R.id.tv_oldprice);
//        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ImageView product = helper.getView(R.id.iv_product);
        ImageView iv_presale = helper.getView(R.id.iv_presale);
        GlideUtil.loadImg(UiUtil.getContext(), item.getThumb(), product);
        TextView  time    = helper.getView(R.id.tv_validity_time);
        RelativeLayout rl_image = helper.getView(R.id.rl_image);
        if (TextUtils.isEmpty(item.validend))
        {
            time.setVisibility(View.GONE);
        }
        else
        {
            time.setText("有效期：" +DateUtil.formatyyyyMMdd(DensityUtils.parseLong(item.validend)*1000) );
        }
        ViewGroup.LayoutParams layoutParams = rl_image.getLayoutParams();
        layoutParams.height = (DensityUtils.getScreenWidth()-DensityUtils.dp2px(UiUtil.getContext(), 60))/2;
        rl_image.setLayoutParams(layoutParams);
        TextView price=helper.getView(R.id.tv_price);
        iv_presale.setVisibility(TextUtils.equals(item.getPresale(),"0")? View.GONE:View.VISIBLE);
        helper.setText(R.id.tv_scqy,item.getScqy())
              .setText(R.id.tv_gg,item.getGg())
              .setText(R.id.tv_sale, "已售"+item.getSales());
        price.setText(item.getPrice());
        oldPrice.setText("折后约"+item.disprice);
        if (TextUtils.isEmpty(NetUtil.getToken()))
        {
            oldPrice.setVisibility(View.GONE);
        }
        title.setText(item.getTitle());
        int type = item.getType();
        if(type==1){
            Bitmap   b = null;
            long endtime = item.getEndtime()*1000;
            long starttime = item.getStarttime()*1000;
            if (starttime>endtime) {
                b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs_n);
            }else {
                b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
            }
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
            oldPrice.setVisibility(View.GONE);
        }
        else if(type==2){
            // 满赠
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }
        else if(type==3){
            // 满赠
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        }else if(type==9){
            // 控销
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
