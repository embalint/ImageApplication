package com.example.krunoslavtill.imageapplication.Models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by krunoslavtill on 27/07/16.
 */
@Root(strict=false)
public class Meritum {
    @Element(name = "header")
    private Head header;

    @Element(name = "homePage")
    private Homepage homepage;

    @Element(name = "dimTv",required=false)
    private DimTv dimtv;

    @Element(name = "playerInfo")
    private PlayerInfo playerinfo;

    @Element(name = "leagueStandings")
    private LeagueStandings leaguestandings;

    @Element(name = "appTerms")
    private AppTerms appterms;

    public Head getHeader() {
        return header;
    }

    public void setHeader(Head header) {
        this.header = header;
    }

    public DimTv getDimtv() {
        return dimtv;
    }

    public void setDimtv(DimTv dimtv) {
        this.dimtv = dimtv;
    }

    public PlayerInfo getPlayerinfo() {
        return playerinfo;
    }

    public void setPlayerinfo(PlayerInfo playerinfo) {
        this.playerinfo = playerinfo;
    }

    public LeagueStandings getLeaguestandings() {
        return leaguestandings;
    }

    public void setLeaguestandings(LeagueStandings leaguestandings) {
        this.leaguestandings = leaguestandings;
    }

    public AppTerms getAppterms() {
        return appterms;
    }

    public void setAppterms(AppTerms appterms) {
        this.appterms = appterms;
    }

    public Homepage getHomepage() {
        return homepage;
    }


    public void setHomepage(Homepage homepage) {
        this.homepage = homepage;
    }
}
