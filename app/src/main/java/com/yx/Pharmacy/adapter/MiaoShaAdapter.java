package com.yx.Pharmacy.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

import cn.iwgang.countdownview.CountdownView;


/*
 *  @项目名：  android 
 *  @包名：    com.yx.Pharmacy.adapter
 *  @文件名:   MiaoShaAdapter
 *  @创建者:   CC
 *  @创建时间:  2018/8/11 15:02
 *  @描述：    TODO
 */

public class MiaoShaAdapter
        extends BaseQuickAdapter<DrugModel,BaseViewHolder>
{
    public MiaoShaAdapter(int layoutResId) {
        super(layoutResId);
    }

    private long  millDay = 24*60*60*1000;
    @Override
    protected void convert(BaseViewHolder helper, DrugModel item) {

        TextView       title      = helper.getView(R.id.tv_title);
        TextView       oldPrice   = helper.getView(R.id.tv_oldprice);
        TextView       tv_price   = helper.getView(R.id.tv_price);
        RelativeLayout rl_iv_bg   = helper.getView(R.id.rl_iv_bg);
        LinearLayout   ll_time_bg = helper.getView(R.id.ll_time_bg);
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ImageView product = helper.getView(R.id.iv_product);
        GlideUtil.loadImg(UiUtil.getContext(), item.getThumb(), product);

        helper.setText(R.id.tv_scqy,item.getScqy())
              .setText(R.id.tv_price,item.getPrice())
              .setText(R.id.tv_oldprice, item.getOldprice())
              .setText(R.id.tv_time_state,"距结束：")
              .setText(R.id.tv_gg,item.getGg());
        CountdownView countdownView = helper.getView(R.id.cv_countdownView);
        CountdownView countdownViewDay = helper.getView(R.id.cv_countdownView_day);
        ProgressBar   progressbar   = helper.getView(R.id.progressbar);
        try {
            TextView now       = helper.getView(R.id.tv_now);
            long     endtime   = item.getEndtime()*1000;
            long     starttime = item.getStarttime()*1000;
            long    currentTimeMillis = System.currentTimeMillis();
            helper.setText(R.id.tv_time_state,starttime > currentTimeMillis?"距开始：":"距结束：");
            if (starttime > currentTimeMillis) {//距开始

                long countdown = starttime - currentTimeMillis;
                if (countdown>=millDay){//开始时间-现在时间大于一天
                    countdownViewDay.setVisibility(View.VISIBLE);
                    countdownView.setVisibility(View.GONE);
                    countdownViewDay.start(countdown);
                }else {
                    countdownViewDay.setVisibility(View.GONE);
                    countdownView.setVisibility(View.VISIBLE);
                    countdownView.start(countdown);
                }
                tv_price.setTextColor(Color.parseColor("#909090"));
                now.setEnabled(false);
                rl_iv_bg.setBackground(mContext.getResources().getDrawable(R.drawable.shape_miaosha_iv_bg_n));
                ll_time_bg.setEnabled(false);
            }else {
                tv_price.setTextColor(Color.parseColor("#670F6A"));
                now.setEnabled(true);
                rl_iv_bg.setBackground(mContext.getResources().getDrawable(R.drawable.shape_miaosha_iv_bg));
                ll_time_bg.setEnabled(true);
                long countdown = endtime - currentTimeMillis;
                if (countdown>=millDay){//结束时间减去现在时间大于一天
                    countdownViewDay.setVisibility(View.VISIBLE);
                    countdownView.setVisibility(View.GONE);
                    countdownViewDay.start(countdown);
                }else {
                    countdownViewDay.setVisibility(View.GONE);
                    countdownView.setVisibility(View.VISIBLE);
                    countdownView.start(countdown);
                }
            }

            Bitmap b;
            if (starttime > currentTimeMillis) {
                b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs_n);
            }else {
                b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(), R.drawable.icon_shopcar_label_xs);
            }

            int         max           = item.activityCount;
            int         progress             = item.salesCount;
            progressbar.setMax(max);
            progressbar.setProgress(progress);
            int pressent = (int) ((float) progress / max * 100);
            helper.setText(R.id.tv_progress,pressent+"%");

            CenterAlignImageSpan imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(), b);
            SpannableString      spanString = new SpannableString("icon ");
            spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
            title.setText(spanString);
            title.append(item.getTitle());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
