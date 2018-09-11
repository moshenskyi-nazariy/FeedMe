package com.example.nazariy.places.presentation.main.presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nazariy.places.domain.entities.StatusCode;
import com.example.nazariy.places.domain.usecases.GetPlaces;
import com.example.nazariy.places.presentation.main.view.PlacesListMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PlaceListPresenter extends MvpBasePresenter<PlacesListMvpView> implements PlaceListMvpPresenter {
    private static final String TAG = "PlaceListPresenter";
    private CompositeDisposable compositeDisposable;
    private GetPlaces getPlacesUseCase;

    public PlaceListPresenter(GetPlaces getPlacesUseCase) {
        // make Singleton to substitute usecases
        this.getPlacesUseCase = getPlacesUseCase;
    }

    @Override
    public void attachView(@NonNull PlacesListMvpView view) {
        super.attachView(view);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

    @Override
    public void getPlaces(String location,
                          int radius,
                          int minPrice,
                          int maxPrice,
                          boolean isOpened) {
        compositeDisposable.add(getPlacesUseCase.createObservable(location, radius, minPrice, maxPrice, isOpened)
                .filter(placeResult -> {
                    boolean shouldGoOn = placeResult.getStatus().equals(StatusCode.OK);
                    if (!shouldGoOn)
                        ifViewAttached(view -> view.showMessage(placeResult.getStatus()));
                    return shouldGoOn;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(placeResult -> ifViewAttached(view -> {
                            view.showProgressBar();
                            view.obtainResults(placeResult.getResults());
                        }),
                        error -> Log.d(TAG, "getPlaces: " + error.getMessage()),
                        () -> ifViewAttached(PlacesListMvpView::hideProgressBar)
                ));
    }
}
