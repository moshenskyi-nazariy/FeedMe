package com.example.nazariy.places.data.api;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {

    @GET("json")
    Observable<PlaceResult> getPlaces(@QueryMap Map<String, String> options);

    @GET("json")
    Observable<PlaceResult> getPlace(@Query("reference") String reference, @Query("reference") String key);

}
