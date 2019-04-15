package com.example.nazariy.places.data.repository.remote;


import com.example.nazariy.places.BuildConfig;
import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.Venue;
import com.example.nazariy.places.domain.interfaces.Repository;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteRepository implements Repository {
    private Api api;

    public RemoteRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    @Override
    public Observable<List<Venue>> getPlaces(String location, int radius) {
        HashMap<String, String> options = getClientOptions();
        options.put("query", "cafe");
        options.put("ll", location);
        options.put("radius", String.valueOf(radius));
        return api.getPlaces(options)
                .subscribeOn(Schedulers.io())
                .filter(placeResult -> placeResult.getMeta().getCode() < 400)
                .map(placeResult -> placeResult.getResponse().getVenues());
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
