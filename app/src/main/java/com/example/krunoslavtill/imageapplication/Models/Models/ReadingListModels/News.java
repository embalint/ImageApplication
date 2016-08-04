package com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels;

/**
 * Created by krunoslavtill on 04/08/16.
 */
public class News {

    int id,newsViews;
    String date,time,type,newsTitle,newsBodyURL,newsYoutubeURL,pictureUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsViews() {
        return newsViews;
    }

    public void setNewsViews(int newsViews) {
        this.newsViews = newsViews;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsBodyURL() {
        return newsBodyURL;
    }

    public void setNewsBodyURL(String newsBodyURL) {
        this.newsBodyURL = newsBodyURL;
    }

    public String getNewsYoutubeURL() {
        return newsYoutubeURL;
    }

    public void setNewsYoutubeURL(String newsYoutubeURL) {
        this.newsYoutubeURL = newsYoutubeURL;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}

