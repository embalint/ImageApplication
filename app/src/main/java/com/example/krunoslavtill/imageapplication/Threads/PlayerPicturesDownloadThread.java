package com.example.krunoslavtill.imageapplication.Threads;

import android.content.Context;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.RetrofitModels.AllPlayerPictures;
import com.example.krunoslavtill.imageapplication.RetrofitModels.PrevPicture;
import com.example.krunoslavtill.imageapplication.RetrofitModels.SinglePlayer;
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
    public PlayerPicturesDownloadThread(final int width, final Context applicationContext) {
        this.width=width;
        this.applicationContext=applicationContext;

        new Thread(new Runnable() {
            public void run() {

                ImageLoader imageLoader = ImageLoader.getInstance();


                    allPlayersList= (List<SinglePlayer>) Registry.getInstance().get("allPlayersList");
                    ImageLoaderConfig ilc = new ImageLoaderConfig(applicationContext);
                    Registry.getInstance().set("ImageLoaderConfig", ilc);

                    List<AllPlayerPictures> allPlayerPicturesList = new ArrayList<>();
                    List<String> playersNames = new ArrayList<>();

                    int position = 0;
                    for (int i = 0; i < allPlayersList.size(); i++) {

                        List<AllPlayerPictures> playerPicturesList = allPlayersList.get(i).getPlayerPictures().getAllPlayerPicturesList();
                        playersNames.add(allPlayersList.get(i).getPlayerName());
                        int smallestDifferance = 0;
                        // checking if width of picutre is acceptable for with of phone
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
                        //Glide.with(getApplicationContext()).load(prevPictureList.get(position).getPicURL()).downloadOnly(500,500);
                        imageLoader.loadImage(playerPicturesList.get(position).getPicURL(), null);
                        Log.d("Display", "broj ispisa" + i + "pozicija" + position + " ima najmanju velicinu : " + String.valueOf(smallestDifferance));
                        allPlayerPicturesList.add(playerPicturesList.get(position));


                    }
                    Registry.getInstance().set("playerPictures", allPlayerPicturesList);
                    Registry.getInstance().set("playerNames", playersNames);




                //long endtime = System.currentTimeMillis()-startTime;
                //Log.d("TimeApp","time of thread is : "+ endtime);

            }
        }).start();

    }
}
