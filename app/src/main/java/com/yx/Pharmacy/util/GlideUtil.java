package com.yx.Pharmacy.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yx.Pharmacy.R;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by KID on 2018/3/19.
 */

public class GlideUtil {

    public static void loadImgNoStyle(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions().error(R.drawable.icon_image_loading)
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

    }

    public static void loadImg(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop().error(R.drawable.icon_image_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

    }

    public static void loadLongImg(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop().error(R.drawable.icon_image_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);

    }


    public static void loadRadiusImg(Context context, String url, ImageView imageView,int resource,int width,int height){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(width,height)
                .placeholder(resource)
                .transform(new GlideRoundTransform(context, 5))
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadImg(Context context,String url, ImageView imageView,int resource,int width,int height){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(width,height)
                .placeholder(resource)
                .placeholder(R.drawable.icon_image_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadImg(Context context,String url, ImageView imageView,int resource){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(resource)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadImgError(Context context,String url, ImageView imageView,int resource){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(resource)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadImgFit(Context context,String url, ImageView imageView,int resource){
        RequestOptions options = new RequestOptions()
                .placeholder(resource)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadRoundImg(Context context,String url, ImageView imageView,int resource){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .override(250)
                .placeholder(resource)
                .error(resource)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
    public static void loadRoundDrawable(Context context,int res, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(res)
                .apply(options)
                .into(imageView);
    }
    public static void loadRoundImg(Context context,String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
    public static void loadRoundImgByPath(Context context,String path, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(new File(path))
                .apply(options)
                .into(imageView);
    }
    public static void loadBlurImage(Context context,String path, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .bitmapTransform(new BlurTransformation(context, 15) )
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(new File(path))
                .apply(options)
                .into(imageView);
    }
    public static void loadImgByPath(Context context,String path, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(new File(path))
                .apply(options)
                .into(imageView);
    }
}
