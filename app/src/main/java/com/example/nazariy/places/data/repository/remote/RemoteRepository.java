package com.example.nazariy.places.data.repository.remote;


import com.example.nazariy.places.BuildConfig;
import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.places.Category;
import com.example.nazariy.places.domain.entities.places.PlaceResult;
import com.example.nazariy.places.domain.entities.places.Venue;
import com.example.nazariy.places.domain.interfaces.Repository;
import com.example.nazariy.places.domain.utils.RxTransformers;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteRepository implements Repository {
    private Api api;
    private static final OkHttpClient okhttpClient;

    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        okhttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    public RemoteRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    @Override
    public Observable<List<Venue>> getPlaces(String location, int radius, String... query) {
        HashMap<String, String> options = getClientOptions();
        options.put("query", prepareQuery(query));
        options.put("ll", location);
        options.put("radius", String.valueOf(radius));
        return api.getPlaces(options)
                .compose(RxTransformers.subscribeAndFilter(PlaceResult.class))
                .map(placeResult -> placeResult.getResponse().getVenues());
    }

    private String prepareQuery(String[] queryParams) {
        StringBuilder query = new StringBuilder();
        for (String item : queryParams) {
            query.append(item).append(",");
        }
        return query.delete(query.length() - 1, query.length()).toString();
    }

    @Override
    public Observable<com.example.nazariy.places.domain.entities.details.Venue> getPlaceDetails(String id) {
        return api.getPlaceDetails(id, getClientOptions())
                .compose(RxTransformers.subscribeAndFilter(PlaceDetailsResult.class))
                .map(placeResult -> placeResult.getResponse().getVenue());
    }

    @Override
    public Observable<PhotoResult> getPhotos(String id) {
        return api.getPhotos(id, getClientOptions())
                .compose(RxTransformers.subscribeAndFilter(PhotoResult.class));
    }

    private HashMap<String, String> getClientOptions() {
        HashMap<String, String> options = new HashMap<>();
        options.put("client_id", BuildConfig.CLIENT_ID);
        options.put("client_secret", BuildConfig.CLIENT_SECRET);
        options.put("v", "20182509");
        return options;
    }



}
