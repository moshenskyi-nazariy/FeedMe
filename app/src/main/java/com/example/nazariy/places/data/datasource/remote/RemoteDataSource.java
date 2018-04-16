package com.example.nazariy.places.data.datasource.remote;


import com.example.nazariy.places.data.api.Api;
import com.example.nazariy.places.domain.entities.PlaceType;
import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.interfaces.DataSource;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;

public class RemoteDataSource implements DataSource {
    private Api api;
    private String key;

    public RemoteDataSource(Api api, String key) {
        this.api = api;
        this.key = key;
    }

    @Override
    public Call<Observable<PlaceResult>> getPlaces(String location, int radius, int minPrice,
                                                   int maxPrice,
                                                   boolean isOpened) {
        Map<String, String> data = new HashMap<>();
        data.put("location", location);
        data.put("radius", String.valueOf(radius));
        data.put("type", PlaceType.CAFE + "|" + PlaceType.RESTAURANT);
        data.put("minprice", String.valueOf(minPrice));
        data.put("maxprice", String.valueOf(maxPrice));
        data.put("opennow", String.valueOf(isOpened));
        data.put("key", key);
        return api.getPlaces(data);
    }

    @Override
    public Call<Observable<PlaceResult>> getPlace(String reference) {
        return api.getPlace(reference, key);
    }
}
