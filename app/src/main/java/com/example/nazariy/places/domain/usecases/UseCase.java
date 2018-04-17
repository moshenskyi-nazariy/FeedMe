package com.example.nazariy.places.domain.usecases;


import io.reactivex.Observable;

public abstract class UseCase<T> {

    public abstract Observable<T> createObservable();

}
