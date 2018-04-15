package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.SortType;
import com.example.nazariy.places.domain.entities.place_result.PlaceResult;

import io.reactivex.Observable;

public interface PlacesRepository {

    /**
     * If you don't need some of this parameters pass -1 for integer or false for boolean.
     *
     * @param radius - radius in which search for results
     * @param minPrice - minimum price in cafe or restaurant
     * @param maxPrice - maximum price in cafe or restaurant
     * @param isOpened - is cafe or restaurant opened
     * @return an Observable of places
     */
    Observable<PlaceResult> getPlaces(int radius, int minPrice, int maxPrice, boolean isOpened, @SortType String sortType);

    /**
     *
     * @param reference - reference to concrete place
     * @return an Observable with place description
     */
    Observable<PlaceResult> getPlace(String reference);

}
