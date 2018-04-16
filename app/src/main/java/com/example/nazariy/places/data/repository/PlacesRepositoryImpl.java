package com.example.nazariy.places.data.repository;

import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.data.datasource.remote.RemoteDataSource;
import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;
import retrofit2.Call;

public class PlacesRepositoryImpl implements PlacesRepository {
    private RemoteDataSource remoteDataSource;

    public PlacesRepositoryImpl(Api api, String key) {
        remoteDataSource = new RemoteDataSource(api, key);
    }

    @Override
    public Call<Observable<PlaceResult>> getPlaces(String location, int radius, int minPrice, int maxPrice, boolean isOpened) {
        // check if cache empty
        // doOnNext -> saveToCache
        return remoteDataSource.getPlaces(location, radius, minPrice, maxPrice, isOpened);
    }

    @Override
    public Call<Observable<PlaceResult>> getPlace(String reference) {
        return remoteDataSource.getPlace(reference);
    }
}
