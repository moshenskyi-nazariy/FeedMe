package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaces {
    private PlacesRepository placesRepository;

    public GetPlaces(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public Observable<PlaceResult> createObservable(String location,
                                                    int radius,
                                                    int minPrice,
                                                    int maxPrice,
                                                    boolean isOpened) {
        return placesRepository.getPlaces(location, radius, minPrice, maxPrice, isOpened);
    }

}
