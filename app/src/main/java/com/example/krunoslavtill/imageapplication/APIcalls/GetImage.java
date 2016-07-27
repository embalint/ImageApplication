package com.example.krunoslavtill.imageapplication.APIcalls;

import com.example.krunoslavtill.imageapplication.Models.Homepage;
import com.example.krunoslavtill.imageapplication.Models.Meritum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by krunoslavtill on 27/07/16.
 */
public interface GetImage {
    @GET("/dim/content/DIM_Borut.xml")
    Call<Meritum> getImages();

}
