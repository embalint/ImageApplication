package com.example.krunoslavtill.imageapplication.APIcalls;

import com.example.krunoslavtill.imageapplication.Models.Models.RetrofitModels.Meritum;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by krunoslavtill on 27/07/16.
 */
public interface GetImage {

    @GET("/dim/content/DIM_Borut.xml")
    Call<Meritum> getImages();
/*
    @GET("/s/fa1qpwn43jwij4i/testXML.xml?dl=0")
    Call<Meritum> getImages();
    */

}