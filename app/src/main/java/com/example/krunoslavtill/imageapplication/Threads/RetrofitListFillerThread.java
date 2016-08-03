package com.example.krunoslavtill.imageapplication.Threads;

import android.content.Context;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.RetrofitModels.Homenews;
import com.example.krunoslavtill.imageapplication.RetrofitModels.Meritum;
import com.example.krunoslavtill.imageapplication.RetrofitModels.SinglePlayer;
import com.example.krunoslavtill.imageapplication.Models.ThreadModels.ImageThreadParameters;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 03/08/16.
 */
public class RetrofitListFillerThread {
    Meritum body;
    List<Homenews> homenewsList = new ArrayList<>();
    List<SinglePlayer> allPlayersList = new ArrayList<>();

    boolean threadFlag;
    Context applicationContext;
    int width;
    public RetrofitListFillerThread(final Meritum body, final boolean threadFlag, final Context applicationContext, final int width) {
        this.body=body;
        this.threadFlag=threadFlag;
        this.applicationContext=applicationContext;
        this.width=width;
        new Thread(new Runnable() {
            public void run() {

                Log.d("Players",body.getPlayerInfo().getSinglePlayerList().get(0).getPlayerName());
                homenewsList = body.getHomepage().getHomenewsList();
                allPlayersList=body.getPlayerInfo().getSinglePlayerList();


                Registry.getInstance().set("homeNewsList", homenewsList);
                Registry.getInstance().set("allPlayersList", allPlayersList);
                new PlayerPicturesDownloadThread(width,applicationContext);
                ImageThreadParameters itp = new ImageThreadParameters();
                itp.setWidth(width);
                itp.setHomenewsList(homenewsList);
                itp.setThreadFlag(threadFlag);
                itp.setAppContext(applicationContext);
                new ImageDownloadThread(itp);



            }
        }).start();
    }


}
