package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class GetPlaces extends UseCase<PlaceResult> {
    private PlacesRepository placesRepository;
    private int radius;
    private int minPrice;
    private int maxPrice;
    private boolean isOpened;

    public GetPlaces(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    private GetPlaces(PlacesRepository placesRepository, int radius, int minPrice, int maxPrice,
                     boolean isOpened) {
        this.placesRepository = placesRepository;
        this.radius = radius;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.isOpened = isOpened;
    }

    @Override
    public Observable<PlaceResult> createObservable() {
        return placesRepository.getPlaces(radius, minPrice, maxPrice, isOpened);
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

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public static class PlaceBuilder {
        private PlacesRepository placesRepository;
        private int radius;
        private int minPrice;
        private int maxPrice;
        private boolean isOpened;

        public GetPlaces createUseCase() {
            return new GetPlaces(placesRepository, radius, minPrice, maxPrice, isOpened);
        }

        public PlaceBuilder setPlacesRepository(PlacesRepository placesRepository) {
            this.placesRepository = placesRepository;
            return this;
        }


        public PlaceBuilder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public PlaceBuilder setMinPrice(int minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public PlaceBuilder setMaxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public PlaceBuilder setOpened(boolean opened) {
            isOpened = opened;
            return this;
        }
    }
}
