package com.example.nazariy.places.presentation.details.presenter;

import com.example.nazariy.places.domain.entities.details.Response;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResponse;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.base.BaseRxPresenter;
import com.example.nazariy.places.presentation.details.view.DetailsMvpView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailsPresenter extends BaseRxPresenter<DetailsMvpView>
        implements DetailsMvpPresenter {

    private DataSource repository;

    public DetailsPresenter(DataSource repository) {
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

}
