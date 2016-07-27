package com.example.krunoslavtill.imageapplication.Models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by krunoslavtill on 27/07/16.
 */
@Root(strict=false)
public class PrevPicture {
    @Attribute
    private String picWidth;

    @Attribute
    private String picHeight;

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

    public String getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(String picHeight) {
        this.picHeight = picHeight;
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
