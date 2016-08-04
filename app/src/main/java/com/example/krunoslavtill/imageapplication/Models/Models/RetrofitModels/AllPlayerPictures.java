package com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by krunoslavtill on 03/08/16.
 */
@Root(name = "prevPicture",strict=false)
public class AllPlayerPictures {
    @Attribute
    private String picWidth;

    @Attribute
    private String picURL;

    @Attribute
    private String picChangeTime;

    public String getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(String picWidth) {
        this.picWidth = picWidth;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getPicChangeTime() {
        return picChangeTime;
    }

    public void setPicChangeTime(String picChangeTime) {
        this.picChangeTime = picChangeTime;
    }
}
