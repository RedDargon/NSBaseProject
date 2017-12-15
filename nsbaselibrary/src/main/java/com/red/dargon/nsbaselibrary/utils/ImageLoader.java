package com.red.dargon.nsbaselibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.red.dargon.nsbaselibrary.R;
import com.red.dargon.nsbaselibrary.base.BaseApplication;
import com.red.dargon.nsbaselibrary.ui.CircleImageTransform;

import java.io.File;
import java.util.Random;

/**
 * 图片加载
 * Glide的二次封装
 */

public class ImageLoader {

    private static final DiskCacheStrategy DEFAULT_DISK_CACHE_STRATEGY = DiskCacheStrategy.RESULT;

    /**
     * 获取全局(Application)的Context
     * 生命周期为Application的生命周期
     */
    private static Context getGlobalContext() {
        return BaseApplication.getApplicationInstance();
    }


    public static void loadImage(Context context, ImageView imageView, int drawableId) {
        Glide.with(context)
                .load(drawableId)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, byte[] bytes) {
        Glide.with(context)
                .load(bytes)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, Bitmap bitmap) {
        Glide.with(context)
                .load(bitmap)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    @Deprecated
    public static void loadImage(Context context, ImageView imageView, File file, boolean isCache) {
        Glide.with(context)
                .load(file)
                .diskCacheStrategy(isCache ? DEFAULT_DISK_CACHE_STRATEGY : DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl,boolean isCache) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(isCache ? DEFAULT_DISK_CACHE_STRATEGY : DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .signature(new StringSignature(new Random().toString()))
                .into(imageView);
    }



    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl, int resPlaceId) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(resPlaceId)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    /**
     * 直接加载图片(没有占位图等其他处理，仅仅是加载一张图片)
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImage(Context context, ImageView imageView, String imageUrl, int resPlaceId, DiskCacheStrategy diskCacheStrategy) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(resPlaceId)
                .diskCacheStrategy(diskCacheStrategy != null ? diskCacheStrategy : DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }


    /**
     * 加载图片，在加载过程中会显示占位图，失败也会显示占位图
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadImageWithPlaceHolder(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default)
                .dontAnimate()
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     */
    public static void loadCircleImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .dontAnimate()
                .transform(new CircleImageTransform(getGlobalContext()))
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context   Context
     * @param imageView ImageView
     */
    public static void loadCircleImage(Context context, ImageView imageView, int resId) {
        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .dontAnimate()
                .transform(new CircleImageTransform(getGlobalContext()))
                .into(imageView);
    }

    /**
     * 加载Gif图
     *
     * @param context   Context
     * @param imageView ImageView
     * @param gifUrl    gif图地址
     */
    public static void loadGif(Context context, ImageView imageView, String gifUrl) {
        Glide.with(context)
                .load(gifUrl)
                .asGif()
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    /**
     * 加载Gif图
     *
     * @param context   Context
     * @param imageView ImageView
     */
    public static void loadGif(Context context, ImageView imageView, int resId) {
        Glide.with(context)
                .load(resId)
                .asGif()
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    /**
     * 加载Gif图
     *
     * @param context   Context
     * @param imageView ImageView
     */
    public static void loadGif(Context context, ImageView imageView, int resId, DiskCacheStrategy diskCacheStrategy) {
        Glide.with(context)
                .load(resId)
                .asGif()
                .diskCacheStrategy(diskCacheStrategy != null ? diskCacheStrategy : DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    /**
     * 加载圆形Gif图
     *
     * @param context   Context
     * @param imageView ImageView
     */
    public static void loadGifAsCircle(Context context, ImageView imageView, int resId) {
        Glide.with(context)
                .load(resId)
                .asGif()
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }

    /**
     * 根据特定的宽高加载图片
     *
     * @param context   Context
     * @param imageView ImageView
     * @param imageUrl  图片地址
     * @param width     图片的宽度
     * @param height    图片的高度
     */
    public static void loadImageWithSize(Context context, ImageView imageView, String imageUrl, int resId, int width, int height) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(resId)
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .override(width, height)
                .into(imageView);
    }

    public static void loadImageWithUri(Context context, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                .into(imageView);
    }


}
