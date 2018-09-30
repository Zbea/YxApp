package com.yx.Pharmacy.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created time  2018/7/5 0005
 * @author : mcx
 * 类描述 : 
 */

public class GlideTransImageLoader
        extends ImageLoader
{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        try {
            RequestOptions options = new RequestOptions()
                    .fitCenter()
                    .bitmapTransform(new BlurTransformation(context,25,3))
                    .diskCacheStrategy(DiskCacheStrategy.ALL);


            Glide.with(context).load(path).apply(options).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
