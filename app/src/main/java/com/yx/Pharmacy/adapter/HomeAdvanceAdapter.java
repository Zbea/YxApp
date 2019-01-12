package com.yx.Pharmacy.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.util.DensityUtils;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 : 首页广告列表
 */

public class HomeAdvanceAdapter extends BaseQuickAdapter<HomeAdvanceModel.GoldBean,BaseViewHolder> {
    public HomeAdvanceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeAdvanceModel.GoldBean item) {
        ImageView image = helper.getView(R.id.iv_image);
//        GlideUtil.loadImg(UiUtil.getContext(),item.image_src,image,R.drawable.icon_image_loading_cc);

        image.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((DensityUtils.getScreenWidth()-60)/2,
                (DensityUtils.getScreenWidth()-60)/4);
        layoutParams.topMargin=20;
        if (helper.getLayoutPosition()%2==0)
        {
            layoutParams.rightMargin=10;
            layoutParams.leftMargin=20;
        }
        else
        {
            layoutParams.leftMargin=10;
            layoutParams.rightMargin=20;
        }
        image.setLayoutParams(layoutParams);
        GlideUtil.loadImgNoStyle(mContext,item.image_src,image,R.drawable.icon_image_loading_cc);
//        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(DensityUtils.getScreenWidth()/2,ViewGroup.LayoutParams.WRAP_CONTENT)
//        image.setLayoutParams(layoutParams);
    }
}
