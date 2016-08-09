package com.example.krunoslavtill.imageapplication.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.krunoslavtill.imageapplication.LazyLoad.ImageLoader;
import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.Players;
import com.example.krunoslavtill.imageapplication.R;
import com.example.krunoslavtill.imageapplication.Utils.ImageLoaderConfig;
import com.example.krunoslavtill.imageapplication.Utils.NetworkDisablingLoader;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 19/07/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<String> urlList;
    private List<String> newsURL;
    private int listCount;
    List<Players> listOfAllPlayersAtributes;
    private List<String> playerPicturesUrl;
    private List<String> playerNames;
    private List<Integer> playersNumber;
    private String imageFramework;
    DisplayImageOptions defaultOptions;
    com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private TextView headerPosition,defendingPosition;
    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    TextView tv;
    private int sectionPosition;
    GridLayoutManager layoutManager;
    boolean spanFlag=false;
    int item;


    public ImageAdapter(Context context, List<Players> listOfAllPlayersAtributes,String imageFramework,GridLayoutManager layoutManager){
        defaultOptions= ((ImageLoaderConfig)Registry.getInstance().get("ImageLoaderConfig")).getDefaultOptions();
        imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        this.mContext=context;
        this.listOfAllPlayersAtributes=listOfAllPlayersAtributes;
        this.listCount=listOfAllPlayersAtributes.size()+1;
        this.imageFramework=imageFramework;
        this.layoutManager=layoutManager;

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if(position==0||position==3||position==11||position==24){
                    return 2;
                }
                else {
                    return 1;
                }
                //return position == 2 ? 0 : 1;
            }
        });


    }
    public ImageAdapter(Context mContext, List<String> urlList,List<String> newsURL,String imageFramework,int sectionPosition) {
        defaultOptions= ((ImageLoaderConfig)Registry.getInstance().get("ImageLoaderConfig")).getDefaultOptions();
        imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        this.mContext = mContext;
        this.urlList=urlList;
        this.newsURL = newsURL;
        this.imageFramework=imageFramework;
        this.listCount=urlList.size();
        this.sectionPosition=sectionPosition;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail,playersThumbnail;
        TextView textView,playerNames,playersNumber;
        public ItemHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            playersThumbnail = (ImageView) view.findViewById(R.id.player_thumbnail);
            textView = (TextView) view.findViewById(R.id.textViewDim);
            playerNames = (TextView) view.findViewById(R.id.player_names);
            playersNumber=(TextView) view.findViewById(R.id.player_number);


        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }

        public ImageView getPlayersThumbnail() {
            return playersThumbnail;
        }

        public TextView getPlayerNames() {
            return playerNames;
        }

        public TextView getPlayersNumber() {
            return playersNumber;
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View view) {

            super(view);
            tv= (TextView) view.findViewById(R.id.headerPosition);



        }


    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=null;
        if (viewType==TYPE_HEADER){
             itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.recycler_view_header, parent, false);

            HeaderHolder headerHolder= new HeaderHolder(itemView);
            return headerHolder;
        }
        else {



        switch(imageFramework) {

            case "LazyLoad":
                itemView = LayoutInflater.from(mContext)
                        .inflate(R.layout.image_row, parent, false);
                ItemHolder itemHolder = new ItemHolder(itemView);
                return itemHolder;



            case "Squad":
                itemView = LayoutInflater.from(mContext)
                        .inflate(R.layout.players_atributes_row, parent, false);
                ItemHolder itemHoldersquad = new ItemHolder(itemView);
                return itemHoldersquad;

        }

    }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0||position==3||position==11||position==24){
            spanFlag=true;
            return TYPE_HEADER;
        }
        else {
            spanFlag=false;
            return TYPE_ITEM;
        }

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderHolder){

        }
        else if(holder instanceof ItemHolder){

        switch(imageFramework) {
            case "Glide":
                Log.d("Display","glide");

           Glide.with(mContext)
                   //.using(new NetworkDisablingLoader())
                   .load(urlList.get(position))
                   .diskCacheStrategy(DiskCacheStrategy.ALL)
                   .into(((ItemHolder) holder).getThumbnail());



            ((ItemHolder) holder).textView.setText(newsURL.get(position));

                break;
            case "Picasso":
                Log.d("Display","Picasso");
                Picasso.with(mContext)
                        .load(urlList.get(position)).into(((ItemHolder) holder).getThumbnail());
                ((ItemHolder) holder).textView.setText(newsURL.get(position));
                break;

            case "LazyLoad":
                Log.d("Display","LazyLoad");

                //imageLoader.displayImage(urlList.get(position), holder.getThumbnail(), defaultOptions);
                imageLoader.displayImage(urlList.get(position), ((ItemHolder) holder).getThumbnail(), defaultOptions, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }
                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        Log.d("Complete",failReason.getType().toString());
                    }
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        Log.d("Complete","Image is loader");


                    }
                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        //Toast.makeText(mContext, R.string.error_lost_internet_connection,
                          //      Toast.LENGTH_LONG).show();
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {

                    }
                });
                ((ItemHolder) holder).textView.setText(newsURL.get(position));

                break;

            case "Squad":


                if(listOfAllPlayersAtributes.get(position).getPlayerPostion().equals("Portero")){
                    tv.setText("Goalkeper");
                    //sectionPosition=0;
                    //position=position-1;

                    imageLoader.displayImage(listOfAllPlayersAtributes.get(position).getPlayerPicture(), ((ItemHolder) holder).getPlayersThumbnail(), defaultOptions, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }
                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            Log.d("Complete",failReason.getType().toString());
                        }
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            Log.d("Complete","Image is loader");

                        }
                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            //Toast.makeText(mContext, R.string.error_lost_internet_connection,
                            //      Toast.LENGTH_LONG).show();
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {

                        }
                    });
                   // Log.d("Numbers", String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                    ((ItemHolder) holder).getPlayerNames().setText(listOfAllPlayersAtributes.get(position).getPlayerName());
                    ((ItemHolder) holder).getPlayersNumber().setText(String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                }
                //position=position-1;
                if(listOfAllPlayersAtributes.get(position).getPlayerPostion().equals("Defensa")){
                    //position=position-1;
                    tv.setText("Defense");
                    imageLoader.displayImage(listOfAllPlayersAtributes.get(position).getPlayerPicture(), ((ItemHolder) holder).getPlayersThumbnail(), defaultOptions, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }
                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            Log.d("Complete",failReason.getType().toString());
                        }
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            Log.d("Complete","Image is loader");

                        }
                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            //Toast.makeText(mContext, R.string.error_lost_internet_connection,
                            //      Toast.LENGTH_LONG).show();
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {

                        }
                    });
                   // Log.d("Numbers", String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                    ((ItemHolder) holder).getPlayerNames().setText(listOfAllPlayersAtributes.get(position).getPlayerName());
                    ((ItemHolder) holder).getPlayersNumber().setText(String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                }

                if(listOfAllPlayersAtributes.get(position).getPlayerPostion().equals("Volante")){
                    tv.setText("Middle");
                    //headerPosition.setVisibility(View.VISIBLE);
                    //headerPosition.setText("Volante");
                    imageLoader.displayImage(listOfAllPlayersAtributes.get(position).getPlayerPicture(), ((ItemHolder) holder).getPlayersThumbnail(), defaultOptions, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }
                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            Log.d("Complete",failReason.getType().toString());
                        }
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            Log.d("Complete","Image is loader");

                        }
                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            //Toast.makeText(mContext, R.string.error_lost_internet_connection,
                            //      Toast.LENGTH_LONG).show();
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {

                        }
                    });
                   // Log.d("Numbers", String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                    ((ItemHolder) holder).getPlayerNames().setText(listOfAllPlayersAtributes.get(position).getPlayerName());
                    ((ItemHolder) holder).getPlayersNumber().setText(String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                }
                if(listOfAllPlayersAtributes.get(position).getPlayerPostion().equals("Delantero")){

                    tv.setText("Attack");
                    imageLoader.displayImage(listOfAllPlayersAtributes.get(position).getPlayerPicture(), ((ItemHolder) holder).getPlayersThumbnail(), defaultOptions, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }
                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            Log.d("Complete",failReason.getType().toString());
                        }
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            Log.d("Complete","Image is loader");

                        }
                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            //Toast.makeText(mContext, R.string.error_lost_internet_connection,
                            //      Toast.LENGTH_LONG).show();
                        }
                    }, new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {

                        }
                    });
                    Log.d("Numbers", String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                    ((ItemHolder) holder).getPlayerNames().setText(listOfAllPlayersAtributes.get(position).getPlayerName());
                    ((ItemHolder) holder).getPlayersNumber().setText(String.valueOf(listOfAllPlayersAtributes.get(position).getPlayerNumber()));
                }

                break;

        }

    }
    }

    @Override
    public int getItemCount() {


        return listCount+4;
        //return 1;
    }



}


