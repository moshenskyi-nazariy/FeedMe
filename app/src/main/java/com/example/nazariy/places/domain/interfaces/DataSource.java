package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.PlaceResult;

import io.reactivex.Observable;

public interface DataSource {

    Observable<PlaceResult> getPlaces(String location, int radius);

}
