package com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels;

import android.widget.ImageView;

/**
 * Created by krunoslavtill on 09/08/16.
 */
public class ChildItem extends Item{
    private String playerNumber,playerName;
    private String playerImage;
    public ChildItem(String playerNumber,String playerName,String playerImage) {
        this.playerName=playerName;
        this.playerNumber=playerNumber;
        this.playerImage=playerImage;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    @Override
    public int getTypeItem() {
        return TYPE_ITEM;
    }
}
