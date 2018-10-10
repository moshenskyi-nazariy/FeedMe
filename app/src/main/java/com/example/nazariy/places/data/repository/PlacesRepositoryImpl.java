package com.example.nazariy.places.data.repository;

import com.example.nazariy.places.BuildConfig;
import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.data.datasource.remote.RemoteDataSource;
import com.example.nazariy.places.domain.entities.PlaceResult;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesRepositoryImpl implements PlacesRepository {
    private RemoteDataSource remoteDataSource;

    public PlacesRepositoryImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        remoteDataSource = new RemoteDataSource(api, BuildConfig.KEY);
    }

    @Override
    public Observable<PlaceResult> getPlaces(String location, int radius) {
        // check if cache empty
        // doOnNext -> saveToCache
        return remoteDataSource.getPlaces(location, radius)
                .subscribeOn(Schedulers.io());
    }

}
