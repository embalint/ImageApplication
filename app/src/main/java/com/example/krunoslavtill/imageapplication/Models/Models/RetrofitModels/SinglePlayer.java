package com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by krunoslavtill on 03/08/16.
 */
@Root(name = "singlePlayer", strict = false)
public class SinglePlayer {
    @Element(name = "playerName")
    private String playerName;

    @Element(name = "playerNumber")
    private String playerNumber;

    @Element(name = "playerPictures")
    private PlayerPictures playerPictures;

    @Element(name = "playerPosition")
    private String playerPosition;

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public PlayerPictures getPlayerPictures() {
        return playerPictures;
    }

    public void setPlayerPictures(PlayerPictures playerPictures) {
        this.playerPictures = playerPictures;
    }
}
