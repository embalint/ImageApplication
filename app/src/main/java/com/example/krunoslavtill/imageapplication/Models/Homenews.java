package com.example.krunoslavtill.imageapplication.Models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by krunoslavtill on 27/07/16.
 */
@Root(name = "homeNews", strict = false)
public class Homenews {

    @Element(name = "newsTitle")
    private String newsTitle;

    @Element(name = "homePictures")
    private Homepictures homepictures;

    public Homepictures getHomepictures() {
        return homepictures;
    }

    public void setHomepictures(Homepictures homepictures) {
        this.homepictures = homepictures;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
