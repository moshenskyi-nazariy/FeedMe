package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaceDetails extends UseCase<PlaceResult> {
    private PlacesRepository placesRepository;
    private String reference;

    public GetPlaceDetails(PlacesRepository placesRepository, String reference, String key) {
        this.placesRepository = placesRepository;
        this.reference = reference;
    }

    @Override
    public Observable<PlaceResult> createObservable() {
        return placesRepository.getPlace(reference);
    }
}
