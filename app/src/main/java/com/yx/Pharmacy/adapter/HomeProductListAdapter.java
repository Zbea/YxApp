package com.yx.Pharmacy.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.HomeDataModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

import java.util.List;

/**
 * Created time  2018/7/17 0017
 * @author : mcx
 * 类描述 :
 */

public class HomeProductListAdapter extends BaseQuickAdapter<HomeDataModel.GoodlistsBean,BaseViewHolder> {
    private String mType;
    private onCountEndClick mOnCountEndClickClick;
    private boolean isNeedAdapta;

    public HomeProductListAdapter(int layoutResId, String type) {
        super(layoutResId);
        mType = type;
    }

    public HomeProductListAdapter(int layoutResId, @Nullable List<HomeDataModel.GoodlistsBean> data, String type) {
        super(layoutResId, data);
        this.mType = type ;
    }

    private long  millDay = 24*60*60*1000;
    @Override
    protected void convert(BaseViewHolder helper, HomeDataModel.GoodlistsBean item) {
        TextView title = helper.getView(R.id.tv_title);
        TextView price = helper.getView(R.id.tv_price);
        TextView oldPrice = helper.getView(R.id.tv_oldprice);
        ImageView product = helper.getView(R.id.iv_product);
        RelativeLayout rl_image = helper.getView(R.id.rl_image);
        GlideUtil.loadImg(UiUtil.getContext(),item.thumb,product);
        TextView  time    = helper.getView(R.id.tv_validity_time);
        if (TextUtils.isEmpty(item.validend))
        {
            time.setVisibility(View.GONE);
        }
        else
        {
            time.setText("有效期：" +DateUtil.formatyyyyMMdd(DensityUtils.parseLong(item.validend)*1000) );
        }
        price.setText(item.price);
        oldPrice.setText("折后约"+item.disprice);
        if (TextUtils.isEmpty(NetUtil.getToken()))
        {
            oldPrice.setVisibility(View.GONE);
        }
        if(TextUtils.equals(mType, "1")){
            // 秒杀
            int         max           = DensityUtils.parseInt(item.salesacti);
            int         progress             = DensityUtils.parseInt(item.sales);
            ProgressBar progressBar = helper.getView(R.id.progress_bar);
            progressBar.setMax(max);
            progressBar.setProgress(progress);

            int pressent = (int) ((float) progress / max * 100);
            title.setText(item.title);
            helper.setText(R.id.tv_product_progress,pressent+"%");
            oldPrice.setText("秒杀价");

        }else if(TextUtils.equals(mType,"2")){
            // 特价
            helper.setText(R.id.tv_title,item.title)
                  .setText(R.id.tv_scqy,item.scqy)
                  .setText(R.id.tv_gg,item.gg)
                  .setText(R.id.tv_sale,"已售"+item.sales)
                  .setGone(R.id.iv_presale,!TextUtils.equals(item.presale,"0"));
//            oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            ViewGroup.LayoutParams layoutParams = rl_image.getLayoutParams();
            layoutParams.height = (DensityUtils.getScreenWidth()-DensityUtils.dp2px(UiUtil.getContext(),60))/2;
            rl_image.setLayoutParams(layoutParams);

            Bitmap               b          = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_tj);
            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.title);
        }else if(TextUtils.equals(mType,"3")){
            // 满赠
            helper.setText(R.id.tv_scqy,item.scqy)
                  .setText(R.id.tv_gg,item.gg)
                  .setText(R.id.tv_sale,"已售"+item.sales)
                  .setGone(R.id.iv_presale,!TextUtils.equals(item.presale,"0"));
//            oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            TextView tv_levelnote = helper.getView(R.id.tv_levelnote);
            tv_levelnote.setVisibility(TextUtils.isEmpty(item.levelnote)?View.INVISIBLE:View.VISIBLE);
            tv_levelnote.setText(item.levelnote);
            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_mz);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            title.setText(spanString);
            title.append(item.title);
        }else if(TextUtils.equals(mType,"9")){
            // 控销
            helper.setText(R.id.tv_scqy,item.scqy)
                  .setText(R.id.tv_gg,item.gg)
                  .setText(R.id.tv_sale,"已售"+item.sales)
                  .setGone(R.id.iv_presale,!TextUtils.equals(item.presale,"0"));

            TextView tv_levelnote = helper.getView(R.id.tv_levelnote);
            tv_levelnote.setVisibility(TextUtils.isEmpty(item.levelnote)?View.INVISIBLE:View.VISIBLE);
            tv_levelnote.setText(item.levelnote);

            Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_kx);
            CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
            SpannableString spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            title.setText(spanString);
            title.append(item.title);
            oldPrice.setVisibility(View.GONE);
        }
    }

    public  interface onCountEndClick{
        void onEnd();
    }

    public void setOnCountEndListener(onCountEndClick click){
        this.mOnCountEndClickClick = click;
    }

}
