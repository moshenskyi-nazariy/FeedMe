package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.interfaces.PlacesRepository;

public class GetPlaceDetails {
    private PlacesRepository placesRepository;

    public GetPlaceDetails(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

}
