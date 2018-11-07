package com.example.nazariy.places.presentation.main.presenter;


import android.util.Log;

import com.example.nazariy.places.data.datasource.DataSourceImpl;
import com.example.nazariy.places.presentation.base.BaseRxPresenter;
import com.example.nazariy.places.presentation.main.model.VenueMapper;
import com.example.nazariy.places.presentation.main.view.PlacesListMvpView;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PlaceListPresenter extends BaseRxPresenter<PlacesListMvpView>
        implements PlaceListMvpPresenter {
    private static final String TAG = "PlaceListPresenter";

    private final DataSourceImpl placesRepository;

    public PlaceListPresenter(DataSourceImpl placesRepository) {
        // make Singleton to substitute usecases
        this.placesRepository = placesRepository;
    }

    @Override
    public void getPlaces(String location, int radius) {
        compositeDisposable.add(placesRepository.getPlaces(location, radius)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeResult -> placeResult.getMeta().getCode() < 400)
                .map(placeResult -> placeResult.getResponse().getVenues())
                .map(VenueMapper::venueToViewVenue)
                .subscribe(venues -> ifViewAttached(view -> {
                            view.showProgressBar();
                            view.obtainResults(venues);
                        }),
                        error -> Log.d(TAG, "getPlaces: " + error.getMessage()),
                        () -> ifViewAttached(PlacesListMvpView::hideProgressBar)
                ));
    }
}
