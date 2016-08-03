package com.example.krunoslavtill.imageapplication.Threads;

import android.content.Context;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.RetrofitModels.Homenews;
import com.example.krunoslavtill.imageapplication.Models.ThreadModels.ImageThreadParameters;
import com.example.krunoslavtill.imageapplication.RetrofitModels.PrevPicture;
import com.example.krunoslavtill.imageapplication.Utils.ImageLoaderConfig;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 02/08/16.
 */
public class ImageDownloadThread  {
    ImageThreadParameters itp;
    public ImageDownloadThread(final ImageThreadParameters itp) {
        this.itp=itp;
        final long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            public void run() {

                Log.d("Messages", "usao u dretvu async");
                ImageLoader imageLoader = ImageLoader.getInstance();
                boolean threadFlag = itp.isThreadFlag();
                int width = itp.getWidth();
                Context appContext = itp.getAppContext();
                List<Homenews> homenewsList = itp.getHomenewsList();
                if (threadFlag) {

                    ImageLoaderConfig ilc = new ImageLoaderConfig(appContext);
                    Registry.getInstance().set("ImageLoaderConfig", ilc);
                    List<PrevPicture> prevPictureListURL = new ArrayList<>();
                    List<String> newsURL = new ArrayList<>();

                    int position = 0;
                    for (int i = 0; i < homenewsList.size(); i++) {

                        List<PrevPicture> prevPictureList = homenewsList.get(i).getHomepictures().getPrevPictureList();
                        newsURL.add(homenewsList.get(i).getNewsTitle());
                        int smallestDifferance = 0;
                        // checking if width of picutre is acceptable for with of phone
                        for (int loop = 0; loop < prevPictureList.size(); loop++) {


                            int currientWidth = Integer.parseInt(prevPictureList.get(0).getPicWidth());
                            int startDifferance = Math.abs(width - currientWidth);
                            int dinamicWidth = Integer.parseInt(prevPictureList.get(loop).getPicWidth());
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
                        imageLoader.loadImage(prevPictureList.get(position).getPicURL(), null);
                        Log.d("Display", "broj ispisa" + i + "pozicija" + position + " ima najmanju velicinu : " + String.valueOf(smallestDifferance));
                        prevPictureListURL.add(prevPictureList.get(position));


                    }
                    Registry.getInstance().set("pictures", prevPictureListURL);
                    Registry.getInstance().set("newsURL", newsURL);



                }
                long endtime = System.currentTimeMillis()-startTime;
                Log.d("TimeApp","time of thread is : "+ endtime);

            }
        }).start();
    }


}
