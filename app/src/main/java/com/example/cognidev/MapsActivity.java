package com.example.cognidev;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import com.example.cognidev.model.Venue;
import com.example.cognidev.model.VenueResponse;
import com.example.cognidev.services.CashbackService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    final public void onMapReady(final GoogleMap googleMap) {
        LatLng newYork = new LatLng(40.7128, -73.935242);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(newYork));

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        final String token = sharedPref.getString("token", "");

        final CashbackService cashbackService = new CashbackService();
        Call<VenueResponse> call = cashbackService.getVenuesForLocation(token, "New York");

        call.enqueue(new Callback<VenueResponse>() {
            @Override
            public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
                List<Venue> venues = response.body().getVenues();

                for(int i = 0; i < venues.size(); i++) {

                    Double latitude = venues.get(i).getLat();
                    Double longitude = venues.get(i).getLong();

                    LatLng location = new LatLng(latitude, longitude);
                    String cashback = Double.toString(venues.get(i).getCashback());

                    googleMap.addMarker(new MarkerOptions().position(location).title(cashback));
                }
            }

            @Override
            public void onFailure(Call<VenueResponse> call, Throwable t) {

            }
        });
    }
}
