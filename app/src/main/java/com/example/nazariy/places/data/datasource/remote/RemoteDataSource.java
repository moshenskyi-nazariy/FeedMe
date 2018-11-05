package com.example.nazariy.places.data.datasource.remote;


import com.example.nazariy.places.BuildConfig;
import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.PlaceResult;
import com.example.nazariy.places.domain.interfaces.DataSource;

import java.util.HashMap;

import io.reactivex.Observable;

public class RemoteDataSource implements DataSource {
    private Api api;

    public RemoteDataSource(Api api) {
        this.api = api;
    }

    @Override
    public Observable<PlaceResult> getPlaces(String location, int radius) {
        HashMap<String, String> options = getClientOptions();
        options.put("query", "cafe");
        options.put("ll", location);
        options.put("radius", String.valueOf(radius));
        return api.getPlaces(options);
    }

    @Override
    public Observable<PlaceDetailsResult> getPlaceDetails(String id) {
        return api.getPlaceDetails(id, getClientOptions());
    }

    @Override
    public Observable<PhotoResult> getPhotos(String id) {
        return api.getPhotos(id, getClientOptions());
    }

    private HashMap<String, String> getClientOptions() {
        HashMap<String, String> options = new HashMap<>();
        options.put("client_id", BuildConfig.CLIENT_ID);
        options.put("client_secret", BuildConfig.CLIENT_SECRET);
        options.put("v", "20182509");
        return options;
    }

}
