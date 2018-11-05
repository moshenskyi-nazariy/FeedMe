package com.example.nazariy.places.presentation.details.presenter;

import android.support.annotation.NonNull;

import com.example.nazariy.places.domain.entities.details.Response;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResponse;
import com.example.nazariy.places.domain.interfaces.PlacesRepository;
import com.example.nazariy.places.presentation.details.view.DetailsMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter extends MvpBasePresenter<DetailsMvpView> implements DetailsMvpPresenter {
    private static final String TAG = "DetailsPresenter";
    private CompositeDisposable compositeDisposable;

    private PlacesRepository repository;

    public DetailsPresenter(PlacesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getPlaceDetails(String id) {
        compositeDisposable.add(repository.getPlaceDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeDetailsResult -> placeDetailsResult.getMeta().getCode() < 400)
                .subscribe(placeDetailsResult -> ifViewAttached(view -> {
                            view.showProgressBar();
                            Response response = placeDetailsResult.getResponse();
                            view.obtainDetails(response.getVenue());
                        }),
                        error -> ifViewAttached(view -> view.showMessage(error.getMessage())),
                        () -> ifViewAttached(DetailsMvpView::hideProgressBar)
                ));
    }

    @Override
    public void getPhotos(String id) {
        compositeDisposable.add(repository.getPhotos(id)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeDetailsResult -> placeDetailsResult.getMeta().getCode() < 400)
                .subscribe(placeDetailsResult -> ifViewAttached(view -> {
                            view.showProgressBar();
                            PhotoResponse response = placeDetailsResult.getResponse();
                            if (response != null && response.getPhotos().getCount() > 0)
                                view.obtainPhotos(response.getPhotos());
                            else view.showMessage("Nothing found");
                        }),
                        error -> ifViewAttached(view -> view.showMessage(error.getMessage())),
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
