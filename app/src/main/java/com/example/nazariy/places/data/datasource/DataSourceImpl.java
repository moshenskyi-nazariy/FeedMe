package com.example.nazariy.places.data.datasource;

import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.Category;
import com.example.nazariy.places.domain.entities.places.Venue;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.domain.interfaces.Repository;

import java.util.List;

import io.reactivex.Observable;

public class DataSourceImpl implements DataSource {
    // TODO: 15.04.19 Implement DB interaction
    private Repository remoteDataSource;

    public DataSourceImpl(Repository repository) {
        remoteDataSource = repository;
    }

    @Override
    public Observable<List<Venue>> getPlaces(String location, int radius, String query) {
        return remoteDataSource.getPlaces(location, radius, query);
    }


    @Override
    public Observable<com.example.nazariy.places.domain.entities.details.Venue> getPlaceDetails(String id) {
        return remoteDataSource.getPlaceDetails(id);
    }

    @Override
    public Observable<PhotoResult> getPhotos(String id) {
        return remoteDataSource.getPhotos(id);
    }

}
