package com.red.dargon.nsbaselibrary.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * 自定义配置缓存目录和大小
 * Created by dutay on 2017/7/5.
 */

public class GlideConfiguration implements GlideModule {

    // 需要在AndroidManifest.xml中声明
//     <meta-data
//        android:name="com.example.base.utils.glide.GlideConfiguration"
//        android:value="GlideModule" />

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                GlideCacheUtils.GLIDE_CARCH_DIR,
                GlideCacheUtils.GLIDE_CATCH_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
