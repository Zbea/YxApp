package com.yx.Pharmacy.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.yx.Pharmacy.util.GlideUtil;
import com.yx.Pharmacy.util.UiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created time  2018/5/4 0004
 * @author : mcx
 * 类描述 : 
 */

public class BigPicAdapter
        extends PagerAdapter
{
    private int mAvatarVerifyState = 0;
    private ArrayList<String> mImgList;
    private OnClickFinishListener mOnClickFinishListener;

    public BigPicAdapter(ArrayList<String> imgList) {
        this.mImgList = imgList;
    }

    public BigPicAdapter(ArrayList<String> imgList, int state) {
        this.mImgList = imgList;
        this.mAvatarVerifyState = state;
    }

    @Override
    public int getCount() {
        if (mImgList != null) {
            return mImgList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(UiUtil.getContext());
        photoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        String    url       = mImgList.get(position);
        if(mAvatarVerifyState==1){
            // 文件
            GlideUtil.loadImgByPath(UiUtil.getContext(), url, photoView);
        }else {
            GlideUtil.loadImg(UiUtil.getContext(), url, photoView);
        }
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickFinishListener!=null) {
                    mOnClickFinishListener.onClick();
                }
            }
        });
        container.addView(photoView);
        return photoView;
    }

    public void setData(List<String> data) {
        mImgList = (ArrayList<String>) data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public interface OnClickFinishListener{
        void onClick();
    }
    public void setOnClick(OnClickFinishListener listener){
        this.mOnClickFinishListener = listener;
    }
}
