package com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels;

import com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels.AllPlayerPictures;

import java.util.List;

/**
 * Created by krunoslavtill on 04/08/16.
 */
public class Players {

    int id,playerAge,playerNumber;
    String playerName,playerNation,playerPostion,playerDominantHand,playerPicture;

    //List<PlayerStats>;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNation() {
        return playerNation;
    }

    public void setPlayerNation(String playerNation) {
        this.playerNation = playerNation;
    }

    public String getPlayerPostion() {
        return playerPostion;
    }

    public void setPlayerPostion(String playerPostion) {
        this.playerPostion = playerPostion;
    }

    public String getPlayerDominantHand() {
        return playerDominantHand;
    }

    public void setPlayerDominantHand(String playerDominantHand) {
        this.playerDominantHand = playerDominantHand;
    }

    public String getPlayerPicture() {
        return playerPicture;
    }

    public void setPlayerPicture(String playerPicture) {
        this.playerPicture = playerPicture;
    }
}
