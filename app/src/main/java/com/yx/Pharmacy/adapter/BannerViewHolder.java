package com.yx.Pharmacy.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.activity.MyShopAddActivity;
import com.yx.Pharmacy.activity.CommendProductActivity;
import com.yx.Pharmacy.activity.LoginActivity;
import com.yx.Pharmacy.activity.MyShopActivity;
import com.yx.Pharmacy.activity.ProductDetailActivity;
import com.yx.Pharmacy.activity.SearchActivity;
import com.yx.Pharmacy.base.BaseActivity;
import com.yx.Pharmacy.base.HHActivity;
import com.yx.Pharmacy.constant.Constants;
import com.yx.Pharmacy.model.HomeAdvanceModel;
import com.yx.Pharmacy.net.NetUtil;
import com.yx.Pharmacy.util.AppStatusTracker;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.SPUtil;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by KID on 2018/8/10.
 */

public class BannerViewHolder implements MZViewHolder<HomeAdvanceModel.GoldBean> {
    private ImageView mImageView;
    private Context context;
    @Override
    public View createView(Context context) {
        this.context=context;
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, HomeAdvanceModel.GoldBean data) {
        // 数据绑定
//        mImageView.setImageResource(data);
//        GlideUtil.loadImg(context,data.image_src,mImageView);
        GlideUtil.loadImgNoStyle(context,data.image_src,mImageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick(data);
            }
        });
    }
    //第三方banner点击事件被拦截，所以需要给imageView设置点击事件。此处获取当前的activity，或者直接将点击的position通过EventBus发送出去
    private void doClick(HomeAdvanceModel.GoldBean data) {
        //获取当前的activity
        Activity activity=AppStatusTracker.getInstance().getOnResumActivity();
        if (activity instanceof  BaseActivity)
            ((BaseActivity)activity).gotoClick(data);
    }
}
