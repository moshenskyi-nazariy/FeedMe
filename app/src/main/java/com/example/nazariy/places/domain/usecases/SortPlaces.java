package com.example.nazariy.places.domain.usecases;


import com.example.nazariy.places.domain.entities.SortType;
import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;

public class SortPlaces extends UseCase<PlaceResult> {
    private PlacesRepository repository;

    @SortType
    private String sortType;

    public SortPlaces(PlacesRepository repository, @SortType String sortType) {
        this.repository = repository;
        this.sortType = sortType;
    }

    @Override
    public Observable<PlaceResult> createObservable() {
        return repository.sortBy(sortType);
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
