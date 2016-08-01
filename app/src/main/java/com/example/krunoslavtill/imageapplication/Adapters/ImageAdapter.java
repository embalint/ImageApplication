package com.example.krunoslavtill.imageapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.krunoslavtill.imageapplication.LazyLoad.ImageLoader;
import com.example.krunoslavtill.imageapplication.R;
import com.example.krunoslavtill.imageapplication.Utils.ImageLoaderConfig;
import com.example.krunoslavtill.imageapplication.Utils.NetworkDisablingLoader;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 19/07/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {


    private Context mContext;
    private List<String> urlList;
    private List<String> newsURL;
    private String imageFramework;


    String url;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        TextView textView;
        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            textView = (TextView) view.findViewById(R.id.textViewDim);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }
    }


    public ImageAdapter(Context mContext, List<String> urlList,List<String> newsURL,String imageFramework) {
        this.mContext = mContext;
        this.urlList=urlList;
        this.newsURL = newsURL;
        this.imageFramework=imageFramework;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.image_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch(imageFramework) {
            case "Glide":
                Log.d("Display","glide");
           Glide.with(mContext)
                   .using(new NetworkDisablingLoader())
                   .load(urlList.get(position))
                   .diskCacheStrategy(DiskCacheStrategy.ALL)
                   .into(holder.getThumbnail());

            holder.textView.setText(newsURL.get(position));

                break;
            case "Picasso":
                Log.d("Display","Picasso");
                Picasso.with(mContext)
                        .load(urlList.get(position)).into(holder.getThumbnail());
                holder.textView.setText(newsURL.get(position));
                break;

            case "LazyLoad":
                Log.d("Display","LazyLoad");
                DisplayImageOptions defaultOptions= ((ImageLoaderConfig)Registry.getInstance().get("ImageLoaderConfig")).getDefaultOptions();
                com.nostra13.universalimageloader.core.ImageLoader imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
                imageLoader.displayImage(urlList.get(position), holder.getThumbnail(), defaultOptions);
                holder.textView.setText(newsURL.get(position));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return urlList.size();
        //return 1;
    }



}


