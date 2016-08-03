package com.example.krunoslavtill.imageapplication.RetrofitModels;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by krunoslavtill on 27/07/16.
 */
@Root
public class PlayerInfo {

    @ElementList(name = "singlePlayer",inline = true)
    List<SinglePlayer> singlePlayerList;

    public List<SinglePlayer> getSinglePlayerList() {
        return singlePlayerList;
    }

    public void setSinglePlayerList(List<SinglePlayer> singlePlayerList) {
        this.singlePlayerList = singlePlayerList;
    }
}
