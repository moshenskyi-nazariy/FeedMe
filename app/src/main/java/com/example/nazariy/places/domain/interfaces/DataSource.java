package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;

import io.reactivex.Observable;
import retrofit2.Call;

public interface DataSource {

    Call<Observable<PlaceResult>> getPlaces(String location,
                                           int radius,
                                           int minPrice,
                                           int maxPrice,
                                           boolean isOpened);

    Call<Observable<PlaceResult>> getPlace(String reference);

}
