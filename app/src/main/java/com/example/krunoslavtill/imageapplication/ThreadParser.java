package com.example.krunoslavtill.imageapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.krunoslavtill.imageapplication.APIcalls.GetImage;
import com.example.krunoslavtill.imageapplication.Models.Homenews;
import com.example.krunoslavtill.imageapplication.Models.Meritum;
import com.example.krunoslavtill.imageapplication.Utils.ServiceGenerator;

import java.util.List;

import retrofit2.Call;

/**
 * Created by krunoslavtill on 27/07/16.
 */
// prvi argument je kad pozivam metodu, drugi je sto vraca doInBackground i treci je ono sto vraca na postexecute

public class ThreadParser extends AsyncTask<Integer,List<Homenews>,List<Homenews>> {
    @Override
    protected List<Homenews> doInBackground(Integer... integers) {

        try{
            GetImage taskService = ServiceGenerator.createService(GetImage.class);
            Call<Meritum> call = taskService.getImages();
            /*
            call.enqueue(new Callback<Meritum>() {
                @Override
                public void onResponse(Call<Meritum> call, Response<Meritum> response) {
                    if (response.isSuccessful()) {
                        Log.d("emil","jees");
                        List<Homenews> homenewsList =response.body().getHomepage().getHomenewsList();

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
        */
            List<Homenews> homenewsList=call.execute().body().getHomepage().getHomenewsList();
            Log.d("emil", String.valueOf(homenewsList.size()));
            //return call.execute().body().getHomepage().getHomenewsList();
            return homenewsList;
        }
        catch (Exception e){
            Log.d("Display",e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Homenews> homenewses) {
        super.onPostExecute(homenewses);
    }
}
