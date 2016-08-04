package com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by krunoslavtill on 27/07/16.
 */
@Root(name="homePictures",strict=false)
public class Homepictures {

    @ElementList(name = "prevPicture",inline = true)
    List<PrevPicture> prevPictureList;

    public List<PrevPicture> getPrevPictureList() {
        return prevPictureList;
    }

    public void setPrevPictureList(List<PrevPicture> prevPictureList) {
        this.prevPictureList = prevPictureList;
    }
}


