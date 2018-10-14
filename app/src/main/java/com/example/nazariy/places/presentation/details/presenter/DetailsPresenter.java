package com.example.nazariy.places.presentation.details.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nazariy.places.domain.usecases.GetPlaceDetails;
import com.example.nazariy.places.presentation.details.view.DetailsMvpView;
import com.example.nazariy.places.presentation.main.view.PlacesListMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter extends MvpBasePresenter<DetailsMvpView> implements DetailsMvpPresenter {
    private CompositeDisposable compositeDisposable;
    private GetPlaceDetails getPlaceDetailsUseCase;
    private static final String TAG = "DetailsPresenter";

    public DetailsPresenter(GetPlaceDetails getPlaceDetailsUseCase) {
        this.getPlaceDetailsUseCase = getPlaceDetailsUseCase;
    }

    @Override
    public void getPlaceDetails(String id) {
        compositeDisposable.add(getPlaceDetailsUseCase.createObservable(id)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeDetailsResult -> placeDetailsResult.getMeta().getCode() < 400)
                .subscribe(placeDetailsResult -> ifViewAttached(view -> {
                            view.showProgressBar();
                            view.obtainResult(placeDetailsResult);
                        }),
                        error -> Log.d(TAG, "getPlaces: " + error.getMessage()),
                        () -> ifViewAttached(DetailsMvpView::hideProgressBar)
                ));
    }

    @Override
    public void attachView(@NonNull DetailsMvpView view) {
        super.attachView(view);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

}
