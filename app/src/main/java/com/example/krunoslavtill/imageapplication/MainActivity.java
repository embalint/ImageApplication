package com.example.krunoslavtill.imageapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.krunoslavtill.imageapplication.APIcalls.GetImage;
import com.example.krunoslavtill.imageapplication.Models.Homenews;
import com.example.krunoslavtill.imageapplication.Models.Meritum;
import com.example.krunoslavtill.imageapplication.Models.PrevPicture;
import com.example.krunoslavtill.imageapplication.Utils.DataDownload;
import com.example.krunoslavtill.imageapplication.Utils.ImageLoaderConfig;
import com.example.krunoslavtill.imageapplication.Utils.ServiceGenerator;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Homenews> homenewsList= new ArrayList<>();
    Homenews a;
    int height,width;
    boolean threadFlag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        //ThreadParser tp = new ThreadParser();
        //tp.execute(i);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        getData();

    }

    public void getData() {

        try{
            GetImage taskService = ServiceGenerator.createService(GetImage.class);
            Call<Meritum> call = taskService.getImages();

            call.enqueue(new Callback<Meritum>() {
                @Override
                public void onResponse(Call<Meritum> call, Response<Meritum> response) {
                    if(call.isExecuted()){
                        threadFlag=true;
                        if (response.isSuccessful()) {

                            homenewsList =response.body().getHomepage().getHomenewsList();
                            Registry.getInstance().set("homeNewsList",homenewsList);
                            finishSplash();


                        } else {
                            Log.d("Error", response.errorBody().toString());

                        }

                    }
                    else {
                        Log.d("Error", response.errorBody().toString());

                    }
                }

                @Override
                public void onFailure(Call<Meritum> call, Throwable t) {
                    // something went completely south (like no internet connection)
                    Log.d("Error", "greskaa"+t.getMessage());

                }

            });



        }
        catch (Exception e){
            Log.d("Display",e.toString());

        }


    }

    public void finishSplash(){
        if(threadFlag){

            ImageLoaderConfig ilc = new ImageLoaderConfig(getApplicationContext());
            Registry.getInstance().set("ImageLoaderConfig",ilc);
            Log.d("Display","visina mobitela je "+ height+ " sirina mobitela je : "+ width);
            List<PrevPicture> prevPictureListURL=new ArrayList<>();
            List<String> newsURL=new ArrayList<>();
            List<Integer> bestDifferance=new ArrayList<>();
            int position=0;
            for (int i = 0;i<homenewsList.size();i++){

                List<PrevPicture> prevPictureList=homenewsList.get(i).getHomepictures().getPrevPictureList();
                newsURL.add(homenewsList.get(i).getNewsTitle());
                int smallestDifferance=0;
                // checking if width of picutre is acceptable for with of phone
                for (int loop=0;loop<prevPictureList.size();loop++){


                    int currientWidth= Integer.parseInt(prevPictureList.get(0).getPicWidth());
                    int startDifferance = Math.abs(width-currientWidth);
                    int dinamicWidth=Integer.parseInt(prevPictureList.get(loop).getPicWidth());
                    int otherDifference = Math.abs(width-dinamicWidth);
                    if(startDifferance==otherDifference){
                        smallestDifferance=startDifferance;
                    }
                    if(otherDifference<smallestDifferance){
                        smallestDifferance=otherDifference;
                        position=loop;
                    }

                }
                Glide.with(getApplicationContext()).load(prevPictureList.get(position).getPicURL()).downloadOnly(500,500);
                Log.d("Display", "broj ispisa" + i+ "pozicija"+position+" ima najmanju velicinu : " + String.valueOf(smallestDifferance));
                prevPictureListURL.add(prevPictureList.get(position));

                //Glide.with(getApplicationContext()).load(prevPictureList.get(0).getPicURL()).downloadOnly(500,500);


            }
            Registry.getInstance().set("pictures",prevPictureListURL);
            Registry.getInstance().set("newsURL",newsURL);

            Intent mainIntent = new Intent(MainActivity.this,HomePage.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();

        }

    }


}
