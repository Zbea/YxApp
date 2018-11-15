package com.yx.Pharmacy.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.DrugModel;
import com.yx.Pharmacy.util.GlideUtil;

import java.util.List;

/**
 * Created by KID on 2018/7/17.
 */

public class MyCollectAdapter extends BaseQuickAdapter<DrugModel,BaseViewHolder> {
    private Context context;

    public interface MyListener{
        void cancel(int position, DrugModel model);
        void click(DrugModel model);
    }
    private MyListener listener;
    public void setListener(MyListener listener) {
        this.listener = listener;
    }
    public MyCollectAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<DrugModel> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DrugModel item) {
        ImageView iv_collect=helper.getView(R.id.iv_collect);
        GlideUtil.loadImg(context,!TextUtils.isEmpty(item.getThumb())?item.getThumb():"",iv_collect);
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_company_name,item.getScqy());
        helper.setText(R.id.tv_price,item.getPrice());
        helper.setText(R.id.tv_oldprice,item.getOldprice());
        TextView tv_oldprice=helper.getView(R.id.tv_oldprice);
        tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG); //中划线
        tv_oldprice.setText(item.getPrice());
        helper.setText(R.id.tv_unit,item.getGg());
        helper.getView(R.id.tv_cancel_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.cancel(helper.getLayoutPosition(),item);
            }
        });
        helper.getView(R.id.rl_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)listener.click(item);
            }
        });

//        helper.setText(R.id.tv_unit,item.getPrice());

    }
}
