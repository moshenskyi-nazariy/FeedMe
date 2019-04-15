package com.example.nazariy.places.domain.utils;

import com.example.nazariy.places.domain.entities.base.BasePlaceResult;

import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;

public class RxTransformers {
    public static <T extends BasePlaceResult> ObservableTransformer<BasePlaceResult, T> subscribeAndFilter(
            Class<T> classType) {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .filter(placeResults1 -> placeResults1.getMeta().getCode() < 400)
                .cast(classType);
    }
}
