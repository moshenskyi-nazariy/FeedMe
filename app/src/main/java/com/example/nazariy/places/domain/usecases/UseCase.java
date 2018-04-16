package com.example.nazariy.places.domain.usecases;


import io.reactivex.Observable;
import retrofit2.Call;

public abstract class UseCase<T> {

    public abstract Call<Observable<T>> createObservable();

}
