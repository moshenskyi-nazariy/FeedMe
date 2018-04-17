package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;

import io.reactivex.Observable;

public interface DataSource {

    Observable<PlaceResult> getPlaces(String location,
                                           int radius,
                                           int minPrice,
                                           int maxPrice,
                                           boolean isOpened);

    Observable<PlaceResult> getPlace(String reference);

}
