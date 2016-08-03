package com.example.krunoslavtill.imageapplication.RetrofitModels;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by krunoslavtill on 03/08/16.
 */
@Root(name = "playerPictures",strict=false)
public class PlayerPictures {

    @ElementList(name = "prevPicture",inline = true)
    List<AllPlayerPictures> allPlayerPicturesList;

    public List<AllPlayerPictures> getAllPlayerPicturesList() {
        return allPlayerPicturesList;
    }

    public void setAllPlayerPicturesList(List<AllPlayerPictures> allPlayerPicturesList) {
        this.allPlayerPicturesList = allPlayerPicturesList;
    }
}
