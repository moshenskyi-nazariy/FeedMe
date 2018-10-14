package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.places.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaceDetails {
    private PlacesRepository placesRepository;

    public GetPlaceDetails(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public Observable<PlaceDetailsResult> createObservable(String venueId) {
        return placesRepository.getPlaceDetails(venueId);
    }

}
