package com.yx.Pharmacy.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by Administrator on 2016/8/3.
 */
public class GuidePageAdapter extends PagerAdapter {

    private int res[];

    public GuidePageAdapter(int res[]){
        this.res=res;
    }

    @Override
    public int getCount() {
        return res.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView =new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(res[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


}
