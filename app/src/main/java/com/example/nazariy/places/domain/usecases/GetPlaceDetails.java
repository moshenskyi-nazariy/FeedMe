package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaceDetails {
    private PlacesRepository placesRepository;

    public GetPlaceDetails(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public Observable<PlaceResult> createObservable(String reference) {
        return placesRepository.getPlace(reference);
    }
}
