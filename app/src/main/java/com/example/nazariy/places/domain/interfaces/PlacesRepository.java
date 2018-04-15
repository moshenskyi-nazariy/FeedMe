package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.entities.SortType;

import io.reactivex.Observable;

public interface PlacesRepository {

    Observable<PlaceResult> getPlaces();

    Observable<PlaceResult> getPlaces(int radius, int minPrice, int maxPrice, boolean isOpened);

    Observable<PlaceResult> sortBy(@SortType String sortType);

    Observable<PlaceResult> getPlace(String reference);

}
