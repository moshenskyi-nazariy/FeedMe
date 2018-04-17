package com.example.nazariy.places.presentation.main.presenter;


import android.support.annotation.NonNull;

import com.example.nazariy.places.domain.interfaces.PlacesRepository;
import com.example.nazariy.places.presentation.main.view.PlacesListMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PlaceListPresenter extends MvpBasePresenter<PlacesListMvpView> implements PlaceListMvpPresenter {
    private CompositeDisposable compositeDisposable;
    private PlacesRepository repository;

    public PlaceListPresenter(PlacesRepository repository) {
        this.repository = repository;
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
    public void getPlaces(String location, int radius, int minPrice, int maxPrice, boolean isOpened) {
        compositeDisposable.add(repository.getPlaces(location, radius, minPrice, maxPrice, isOpened)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    placeResult -> ifViewAttached(view -> {
                        view.showProgressBar();
                        view.obtainResults(placeResult);
                    }), error-> {

                    }, () -> ifViewAttached(PlacesListMvpView::hideProgressBar))
                );
    }
}
