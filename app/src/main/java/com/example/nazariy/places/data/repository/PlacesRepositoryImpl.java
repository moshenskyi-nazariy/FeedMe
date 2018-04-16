package com.example.nazariy.places.data.repository;


import com.example.nazariy.places.domain.entities.SortType;
import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class PlacesRepositoryImpl implements PlacesRepository {

    @Override
    public Observable<PlaceResult> getPlaces(int radius, int minPrice, int maxPrice, boolean isOpened,
                                             @SortType String sortType) {
        return null;
    }

    @Override
    public Observable<PlaceResult> getPlace(String reference) {
        return null;
    }
}
