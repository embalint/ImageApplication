package com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by krunoslavtill on 27/07/16.
 */
@Root(name="header")
public class Head {

    @Element(name = "facebookURL")
    private String facebookurl;

    @Element(name ="twitterURL")
    private String twitter;

    @Element(name ="storeURL")
    private String storeurl;

    @Element(name ="ticketsURL")
    private String ticketsurl;

    @Element(name ="instagramURL")
    private String instagramurl;

    @Element(name ="youtubeURL")
    private String youtubeurl;

    @Element(name ="magazineURL")
    private String magazineurl;

    @Element(name ="topBanner")
    private String topbanner;

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
