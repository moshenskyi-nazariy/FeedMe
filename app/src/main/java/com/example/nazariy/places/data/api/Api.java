package com.example.nazariy.places.data.api;


import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.places.PlaceResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Api {

    @GET("venues/search")
    Observable<PlaceResult> getPlaces(@QueryMap Map<String, String> options);

    @GET("venues/{venueId}")
    Observable<PlaceDetailsResult> getPlaceDetails(@Path("venueId") String venueId,
                                                   @QueryMap Map<String, String> options);

}
