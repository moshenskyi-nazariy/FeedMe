package com.example.nazariy.places.data.datasource;

import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.Venue;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.domain.interfaces.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DataSourceImpl implements DataSource {
    private Repository remoteDataSource;

    public DataSourceImpl(Repository repository) {
        remoteDataSource = repository;
    }

    @Override
    public Observable<List<Venue>> getPlaces(String location, int radius) {
        // check if cache empty
        // doOnNext -> saveToCache
        return remoteDataSource.getPlaces(location, radius)
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<PlaceDetailsResult> getPlaceDetails(String id) {
        return remoteDataSource.getPlaceDetails(id)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PhotoResult> getPhotos(String id) {
        return remoteDataSource.getPhotos(id)
                .subscribeOn(Schedulers.io());
    }

}
