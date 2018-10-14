package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.places.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaces {
    private PlacesRepository placesRepository;

    public GetPlaces(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public Observable<PlaceResult> createObservable(String location,
                                                    int radius) {
        return placesRepository.getPlaces(location, radius);
    }

}
