package com.yx.Pharmacy.loader;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;
import com.yx.Pharmacy.R;

/**
 * Created time  2018/4/10 0010
 * @author : mcx
 * 类描述 : 
 */

public class GlideImageLoader
        extends ImageLoader
{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        try {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.icon_image_loading)
                    .error(R.drawable.icon_image_loading)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(context).load(path).apply(options)
//                    .crossFade()
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
