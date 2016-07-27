package com.example.krunoslavtill.imageapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.APIcalls.GetImage;
import com.example.krunoslavtill.imageapplication.Models.Homepage;
import com.example.krunoslavtill.imageapplication.Models.Meritum;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by krunoslavtill on 27/07/16.
 */
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getData();
    }

    private void getData() {
        try{
            GetImage taskService = ServiceGenerator.createService(GetImage.class);
            Call<Meritum> call = taskService.getImages();
            call.enqueue(new Callback<Meritum>() {
                @Override
                public void onResponse(Call<Meritum> call, Response<Meritum> response) {
                    if (response.isSuccessful()) {
                        Log.d("emil","jees");
                        String url =response.body().getHeader().getTwitter();
                        Log.d("emil", url);
                    } else {
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
}
