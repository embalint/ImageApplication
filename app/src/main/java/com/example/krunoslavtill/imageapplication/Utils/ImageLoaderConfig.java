package com.example.krunoslavtill.imageapplication.Utils;

import android.content.Context;

import com.example.krunoslavtill.imageapplication.LazyLoad.ImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by krunoslavtill on 01/08/16.
 */
public class ImageLoaderConfig {
    Context context;
    DisplayImageOptions defaultOptions;
    ImageLoaderConfiguration config;
    public ImageLoaderConfig(Context context) {
        this.context = context;
        com.nostra13.universalimageloader.core.ImageLoader imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true).build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(defaultOptions)
                .writeDebugLogs()
                .build();
        imageLoader.init(config);
    }

    public ImageLoaderConfiguration getConfig() {
        return config;
    }

    public void setConfig(ImageLoaderConfiguration config) {
        this.config = config;
    }

    public DisplayImageOptions getDefaultOptions() {
        return defaultOptions;
    }

    public void setDefaultOptions(DisplayImageOptions defaultOptions) {
        this.defaultOptions = defaultOptions;
    }
}
