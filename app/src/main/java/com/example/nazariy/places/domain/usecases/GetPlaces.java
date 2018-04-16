package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaces extends UseCase<PlaceResult> {
    private String location;
    private String key;
    private PlacesRepository placesRepository;
    private int radius;
    private int minPrice;
    private int maxPrice;

    public GetPlaces(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    private GetPlaces(PlacesRepository placesRepository,
                      String location,
                      int radius,
                      int minPrice,
                      int maxPrice,
                      String key) {
        this.placesRepository = placesRepository;
        this.location = location;
        this.radius = radius;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.key = key;
    }

    @Override
    public Observable<PlaceResult> createObservable() {
        return placesRepository.getPlaces(location, radius, minPrice, maxPrice, key);
    }

    public PlacesRepository getPlacesRepository() {
        return placesRepository;
    }

    public void setPlacesRepository(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

}
