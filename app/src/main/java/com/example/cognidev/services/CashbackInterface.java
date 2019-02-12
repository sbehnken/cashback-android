package com.example.cognidev.services;

import com.example.cognidev.model.CreateResponse;
import com.example.cognidev.model.VenueResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CashbackInterface {

    @POST("/users")

    Call<CreateResponse> getLoginResponse(@Body JsonObject object);


    @GET("/venues")

    Call<VenueResponse> getVenues(@Header("Token") String token, @Query("city") String city);

}

