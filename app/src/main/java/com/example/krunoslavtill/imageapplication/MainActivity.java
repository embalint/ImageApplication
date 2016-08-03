package com.example.krunoslavtill.imageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.APIcalls.GetImage;
import com.example.krunoslavtill.imageapplication.RetrofitModels.Homenews;
import com.example.krunoslavtill.imageapplication.RetrofitModels.Meritum;
import com.example.krunoslavtill.imageapplication.Threads.RetrofitListFillerThread;
import com.example.krunoslavtill.imageapplication.Utils.HttpErrorHandler;
import com.example.krunoslavtill.imageapplication.Utils.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Homenews a;
    int height, width;
    boolean threadFlag = false;
    long startTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;


        Runtime.getRuntime().gc();
        startTime = System.currentTimeMillis();


        getData();

    }

    public void getData() {

        try {
            GetImage taskService = ServiceGenerator.createService(GetImage.class);
            Call<Meritum> call = taskService.getImages();

            double retStart = (System.currentTimeMillis() - startTime);
            Log.d("TimeApp", "Starting retrofit " + retStart + " mili secs");
            call.enqueue(new Callback<Meritum>() {
                @Override
                public void onResponse(Call<Meritum> call, Response<Meritum> response) {


                    if (response.isSuccessful()) {


                        double retStart = (System.currentTimeMillis() - startTime);
                        Log.d("TimeApp", "Finish retrofit " + retStart + " mili secs");
                        threadFlag = true;
                        new RetrofitListFillerThread(response.body(),threadFlag,getApplicationContext(),width);

                        //

                        finishSplash();


                    } else {
                        Log.d("Error1", response.errorBody().toString());
                        new HttpErrorHandler(getApplicationContext(), response.code());
                    }
                }

                @Override
                public void onFailure(Call<Meritum> call, Throwable t) {

                    new HttpErrorHandler(getApplicationContext(), t,MainActivity.this);
                    Log.d("Error1", t.getMessage());
                }

            });

        } catch (Exception e) {
            Log.d("Error1", e.toString());

        }


    }



    public void finishSplash() {
        /*
        if (threadFlag) {

            ImageLoaderConfig ilc = new ImageLoaderConfig(getApplicationContext());
            Registry.getInstance().set("ImageLoaderConfig", ilc);
            Log.d("Display", "visina mobitela je " + height + " sirina mobitela je : " + width);
            List<PrevPicture> prevPictureListURL = new ArrayList<>();
            List<String> newsURL = new ArrayList<>();
            List<Integer> bestDifferance = new ArrayList<>();
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
            */
        Intent mainIntent = new Intent(MainActivity.this, HomePage.class);
        MainActivity.this.startActivity(mainIntent);
        MainActivity.this.finish();

        //}

    }


}
