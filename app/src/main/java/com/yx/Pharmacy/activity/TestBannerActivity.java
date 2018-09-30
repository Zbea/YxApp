package com.yx.Pharmacy.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yx.Pharmacy.R;
import com.yx.Pharmacy.base.BaseActivity;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestBannerActivity extends BaseActivity {

    private MZBannerView mMZBanner;
    private List<Integer>list=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_banner;
    }

    @Override
    protected void init() {
        mMZBanner = (MZBannerView)findViewById(R.id.banner);
        list.add(R.mipmap.banner1);
        list.add(R.mipmap.banner2);
        list.add(R.mipmap.banner3);
        list.add(R.mipmap.banner4);
        list.add(R.mipmap.banner5);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int i) {
                Toast.makeText(TestBannerActivity.this, "view==="+i, Toast.LENGTH_SHORT).show();
            }
        });
        mMZBanner.setIndicatorVisible(false);
        mMZBanner.start();
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }
    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }
}
