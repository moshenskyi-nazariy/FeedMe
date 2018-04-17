package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;

import io.reactivex.Observable;

public interface PlacesRepository {

    /**
     * If you don't need some of this parameters pass -1 for integer or false for boolean.
     *
     * @param location - user location in format Double,Double without spaces after comma
     * @param radius - radius in which search for results
     * @param minPrice - minimum price in cafe or restaurant
     * @param maxPrice - maximum price in cafe or restaurant
     * @return an Observable of places
     */
    Observable<PlaceResult> getPlaces(String location, int radius, int minPrice,
                                           int maxPrice, boolean isOpened);

    /**
     *
     * @param reference - reference to concrete place
     * @return an Observable with place description
     */
    Observable<PlaceResult> getPlace(String reference);

}
