package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.places.PlaceResult;

import io.reactivex.Observable;

public interface DataSource {

    Observable<PlaceResult> getPlaces(String location, int radius);

    Observable<PlaceDetailsResult> getPlaceDetails(String id);

}
