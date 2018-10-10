package com.example.nazariy.places.data.datasource.remote;


import com.example.nazariy.places.BuildConfig;
import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.domain.entities.PlaceResult;
import com.example.nazariy.places.domain.interfaces.DataSource;

import java.util.HashMap;

import io.reactivex.Observable;

public class RemoteDataSource implements DataSource {
    private Api api;
    private String key;

    public RemoteDataSource(Api api, String key) {
        this.api = api;
        this.key = key;
    }

    @Override
    public Observable<PlaceResult> getPlaces(String location, int radius) {
        HashMap<String, String> options = new HashMap<>();
        options.put("client_id", BuildConfig.CLIENT_ID);
        options.put("client_secret", BuildConfig.CLIENT_SECRET);
        options.put("v", "20182509");
        options.put("query", "cafe");
        options.put("ll", location);
        options.put("radius", String.valueOf(radius));
        return api.getPlaces(options);
    }

}
