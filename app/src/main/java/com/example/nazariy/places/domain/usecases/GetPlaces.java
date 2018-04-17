package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaces extends UseCase<PlaceResult> {
    private PlacesRepository placesRepository;
    private String location;
    private int radius;
    private int minPrice;
    private int maxPrice;
    private boolean isOpened;

    public GetPlaces(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    private GetPlaces(PlacesRepository placesRepository,
                      String location,
                      int radius,
                      int minPrice,
                      int maxPrice,
                      boolean isOpened) {
        this.placesRepository = placesRepository;
        this.location = location;
        this.radius = radius;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isOpened = isOpened;
    }

    @Override
    public Observable<PlaceResult> createObservable() {
        return placesRepository.getPlaces(location, radius, minPrice, maxPrice, isOpened);
    }

}
