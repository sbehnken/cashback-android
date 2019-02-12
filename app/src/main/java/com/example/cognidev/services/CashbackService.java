package com.example.cognidev.services;

import com.example.cognidev.model.CreateResponse;
import com.example.cognidev.model.VenueResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CashbackService {

    private final CashbackInterface cashbackInterface;

    public CashbackService() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cashback-explorer-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        cashbackInterface = retrofit.create(CashbackInterface.class);
    }

    public Call<CreateResponse> getLoginResponse(JsonObject object) {
        return cashbackInterface.getLoginResponse(object);
    }

    public Call<VenueResponse> getVenuesForLocation(String token, String city) {
        return cashbackInterface.getVenues(token, city);
    }
}

