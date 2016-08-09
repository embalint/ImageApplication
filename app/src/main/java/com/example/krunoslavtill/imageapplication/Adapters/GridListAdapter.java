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
import android.widget.TextView;
import android.widget.Toast;

import com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels.ChildItem;
import com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels.HeaderItem;
import com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels.Item;
import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.Players;
import com.example.krunoslavtill.imageapplication.R;
import com.example.krunoslavtill.imageapplication.Utils.ImageLoaderConfig;
import com.example.krunoslavtill.imageapplication.View.Holder;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;


import java.util.List;

/**
 * @author Filippo Ash
 * @version 1.0.0
 * @date 11/7/2015
 */
public class GridListAdapter extends RecyclerView.Adapter<Holder> {

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
    private TextView headerPosition, defendingPosition;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    TextView tv;
    GridLayoutManager layoutManager;
    private List<Item> mItemList;


public GridListAdapter(Context context,String imageFramework,GridLayoutManager layoutManager,List<Item> itemList){
            defaultOptions= ((ImageLoaderConfig) Registry.getInstance().get("ImageLoaderConfig")).getDefaultOptions();
            imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            this.mContext=context;
            this.listOfAllPlayersAtributes=listOfAllPlayersAtributes;
            //this.listCount=listOfAllPlayersAtributes.size()+1;
            this.imageFramework=imageFramework;
            this.layoutManager=layoutManager;
            mItemList = itemList;
             layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isHeaderType(position) ? 2 : 1;
            }
        });
    }



    private boolean isHeaderType(int position) {
        return mItemList.get(position).getTypeItem() == Item.TYPE_HEADER ? true : false;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view;

        if(viewType == 0) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_header, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.players_atributes_row, viewGroup, false);
        }

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if(isHeaderType(position)) {
            bindHeaderItem(holder, position);
        } else {
            bindGridItem(holder, position);
        }
    }

    /**
     * This method is used to bind grid item value
     *
     * @param holder
     * @param position
     */

    private void bindGridItem(Holder holder, int position) {

        View container = holder.itemView;

        TextView playerNames = (TextView) container.findViewById(R.id.player_names);
        TextView playersNumber=(TextView) container.findViewById(R.id.player_number);
        ImageView playersThumbnail = (ImageView) container.findViewById(R.id.player_thumbnail);
        final ChildItem item = (ChildItem) mItemList.get(position);


            imageLoader.displayImage(item.getPlayerImage(), playersThumbnail, defaultOptions, new ImageLoadingListener() {
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
            playerNames.setText(item.getPlayerName());
            playersNumber.setText(item.getPlayerNumber());


/*
        title.setText(item.getItemTitle());
        count.setText(Integer.toString(item.getPosition()));
/*
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "You clicked on the " + item.getItemTitle() + " at Position -> " + item.getPosition() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    /**
     * This method is used to bind the header with the corresponding item position information
     *
     * @param holder
     * @param position
     */
    private void bindHeaderItem(Holder holder, int position) {
        TextView title = (TextView) holder.itemView.findViewById(R.id.headerPosition);
        final HeaderItem item = (HeaderItem) mItemList.get(position);
        title.setText(item.getPosition());
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position).getTypeItem() == Item.TYPE_HEADER ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    /**
     * This method is used to add an item into the recyclerview list
     *
     * @param item
     */
    public void addItem(Item item) {
        mItemList.add(item);
        notifyDataSetChanged();
    }

    /**
     * This method is used to remove items from the list
     *
     * @param item {@link Item}
     */
    public void removeItem(Item item) {
        mItemList.remove(item);
        notifyDataSetChanged();
    }

    private void addHeader(String position) {

        addItem(new HeaderItem(position));
    }

    private void addChildItem(String playerNumber,String playerName,String playerImage) {
        addItem(new ChildItem(playerNumber,playerName,playerImage));
    }
}
