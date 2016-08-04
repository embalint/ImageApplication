package com.example.krunoslavtill.imageapplication.Threads;

import android.content.Context;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.Players;
import com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels.AllPlayerPictures;
import com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels.SinglePlayer;
import com.example.krunoslavtill.imageapplication.Utils.ImageLoaderConfig;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 03/08/16.
 */
public class PlayerPicturesDownloadThread {
    int width;
    Context applicationContext;
    List<SinglePlayer> allPlayersList;
    List<Players> listOfAllPlayersAtributes = new ArrayList<>();


    public PlayerPicturesDownloadThread(final int width, final Context applicationContext) {
        this.width = width;
        this.applicationContext = applicationContext;

        new Thread(new Runnable() {
            public void run() {

                ImageLoader imageLoader = ImageLoader.getInstance();


                allPlayersList = (List<SinglePlayer>) Registry.getInstance().get("allPlayersList");
                ImageLoaderConfig ilc = new ImageLoaderConfig(applicationContext);
                Registry.getInstance().set("ImageLoaderConfig", ilc);

                int position = 0;
                for (int i = 0; i < allPlayersList.size(); i++) {

                    Players player = new Players();
                    List<AllPlayerPictures> playerPicturesList = allPlayersList.get(i).getPlayerPictures().getAllPlayerPicturesList();

                    player.setPlayerName(allPlayersList.get(i).getPlayerName());
                    player.setPlayerNumber(Integer.parseInt(allPlayersList.get(i).getPlayerNumber()));


                    int smallestDifferance = 0;
                    // checking if width of picutre is acceptable for width of phone
                    for (int loop = 0; loop < playerPicturesList.size(); loop++) {


                        int currientWidth = Integer.parseInt(playerPicturesList.get(0).getPicWidth());
                        int startDifferance = Math.abs(width - currientWidth);
                        int dinamicWidth = Integer.parseInt(playerPicturesList.get(loop).getPicWidth());
                        int otherDifference = Math.abs(width - dinamicWidth);
                        if (startDifferance == otherDifference) {
                            smallestDifferance = startDifferance;
                        }
                        if (otherDifference < smallestDifferance) {
                            smallestDifferance = otherDifference;
                            position = loop;
                        }

                    }

                    //download of picturess
                    imageLoader.loadImage(playerPicturesList.get(position).getPicURL(), null);
                    player.setPlayerPicture(playerPicturesList.get(position).getPicURL());
                    // list of all player objects
                    listOfAllPlayersAtributes.add(player);

                }
                Registry.getInstance().set("Players", listOfAllPlayersAtributes);


            }
        }).start();

    }
}
