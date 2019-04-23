package com.example.nazariy.places.domain.interfaces;


import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.Venue;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {

    Observable<List<Venue>> getPlaces(String location, int radius, String query);

    Observable<com.example.nazariy.places.domain.entities.details.Venue> getPlaceDetails(String id);

    Observable<PhotoResult> getPhotos(String id);

}
