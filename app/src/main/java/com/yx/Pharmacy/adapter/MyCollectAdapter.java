package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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
import com.yx.Pharmacy.activity.MyCollectActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.DateUtil;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;
import com.yx.Pharmacy.widget.CenterAlignImageSpan;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class MyCollectAdapter extends BaseQuickAdapter<DrugModel,BaseViewHolder> {
    private Context context;
    private AddListener listener;
    public interface  AddListener{
        void addCart(DrugModel item, ImageView imageView);
        void cancel(int position, DrugModel model);
        void click(DrugModel model);
    }
    public void setListener(AddListener listener) {
        this.listener = listener;
    }

    public MyCollectAdapter(Context context, @LayoutRes int layoutResId) {
        super(layoutResId);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final DrugModel item) {
        ImageView iv_category=helper.getView(R.id.iv_category);
        ImageView iv_presale = helper.getView(R.id.iv_presale);
        TextView tv_drug_name = helper.getView(R.id.tv_drug_name);
        TextView tv_levelnote = helper.getView(R.id.tv_levelnote);
        GlideUtil.loadImg(context,item.getThumb(),iv_category);
        TextView tv_oldprice=helper.getView(R.id.tv_oldprice);
        TextView  time    = helper.getView(R.id.tv_validity_time);
        TextView  tv_cancel    = helper.getView(R.id.tv_has_sale);
        iv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.click(item);
            }
        });

        if (TextUtils.isEmpty(item.validend))
        {
            time.setVisibility(View.GONE);
        }
        else
        {
            time.setText("有效期：" +DateUtil.formatyyyyMMdd(DensityUtils.parseLong(item.validend)*1000) );
        }
        tv_oldprice.setText("折后约"+item.disprice);

        iv_presale.setVisibility(TextUtils.equals(item.getPresale(),"0")? View.GONE:View.VISIBLE);
        helper.setText(R.id.tv_company,item.getScqy());

        helper.setText(R.id.tv_unit,item.getGg());
        tv_drug_name.setText(item.getTitle());
        tv_levelnote.setVisibility(View.GONE);
        helper.setText(R.id.tv_price,item.getPrice());

        if (!TextUtils.isEmpty(NetUtil.getToken())) {
            tv_oldprice.setVisibility(View.VISIBLE);
        }else {
            tv_oldprice.setVisibility(View.GONE);
        }


        final ImageView animImg= helper.getView(R.id.iv_item_car);
        TextView tv_product_state=helper.getView(R.id.tv_product_state);
        if(item.isQuehuo()){
            tv_product_state.setVisibility(View.VISIBLE);
            animImg.setVisibility(View.GONE);
        }else {
            tv_product_state.setVisibility(View.GONE);
            animImg.setVisibility(View.VISIBLE);
        }
        animImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.addCart(item,animImg);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.cancel(helper.getLayoutPosition(),item);
            }
        });

        int type = item.getType();
        if(type==1){
            long   endtime   = item.getEndtime()*1000;
            long   starttime = item.getStarttime()*1000;
            if (starttime>endtime) {
                setTitleImage(item,tv_drug_name,R.drawable.icon_shopcar_label_xs_n);
            }else {
                setTitleImage(item,tv_drug_name,R.drawable.icon_shopcar_label_xs);
            }
            tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            tv_oldprice.setText(item.getOldprice());
        }
        else if(type==2){
            // 特价
            setTitleImage(item,tv_drug_name,R.drawable.icon_shopcar_label_tj);
            tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            tv_oldprice.setText(item.getOldprice());
        }
        else if(type==3){
            // 满赠
            setTitleImage(item,tv_drug_name,R.drawable.icon_shopcar_label_mz);
            tv_levelnote.setText(item.getLevelnote());
            tv_levelnote.setVisibility(View.VISIBLE);
        }else if(type==9){
            // 控销
            setTitleImage(item,tv_drug_name,R.drawable.icon_shopcar_label_kx);
            tv_oldprice.setVisibility(View.GONE);
        }
    }


    private void setTitleImage(DrugModel item,TextView tvTitle,int resId)
    {
        Bitmap b = BitmapFactory.decodeResource(UiUtil.getContext().getResources(),resId);
        CenterAlignImageSpan       imgSpan    = new CenterAlignImageSpan(UiUtil.getContext(),b);
        SpannableString spanString = new SpannableString("icon ");
        spanString.setSpan(imgSpan, 0, 4, ImageSpan.ALIGN_BASELINE);
        tvTitle.setText(spanString);
        tvTitle.append(item.getTitle());
    }

}
