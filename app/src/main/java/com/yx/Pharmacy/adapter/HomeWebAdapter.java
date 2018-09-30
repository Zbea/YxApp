package com.yx.Pharmacy.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yx.Pharmacy.R;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

/**
 * Created time  2018/7/16 0016
 * @author : mcx
 * 类描述 : 首页广告列表
 */

public class HomeWebAdapter extends BaseQuickAdapter<HomeAdvanceModel.GoldBean,BaseViewHolder> {
    public HomeWebAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeAdvanceModel.GoldBean item) {
        ImageView image = helper.getView(R.id.iv_image);
        GlideUtil.loadImgNoStyle(UiUtil.getContext(),item.image_src,image);

        helper.setText(R.id.tv_title,item.title);
    }
}
