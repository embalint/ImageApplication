package com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by krunoslavtill on 08/08/16.
 */
public class SquadModel {

    List<String> picturesList;
    List<String> playerPicturesUrl;
    List<String> newsURL;
    List<String> playerNames;
    private RecyclerView mRecyclerView;
    private int itemSpace;
    String imageFramework;

    public List<String> getPlayerPicturesUrl() {
        return playerPicturesUrl;
    }

    public void setPlayerPicturesUrl(List<String> playerPicturesUrl) {
        this.playerPicturesUrl = playerPicturesUrl;
    }

    public List<String> getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(List<String> newsURL) {
        this.newsURL = newsURL;
    }

    public int getItemSpace() {
        return itemSpace;
    }

    public void setItemSpace(int itemSpace) {
        this.itemSpace = itemSpace;
    }

    public List<String> getPicturesList() {
        return picturesList;
    }

    public void setPicturesList(List<String> picturesList) {
        this.picturesList = picturesList;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public void setmRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }



    public String getImageFramework() {
        return imageFramework;
    }

    public void setImageFramework(String imageFramework) {
        this.imageFramework = imageFramework;
    }
}
