package com.example.nazariy.places.presentation.main.presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.nazariy.places.domain.entities.places.Location;
import com.example.nazariy.places.domain.entities.places.Venue;
import com.example.nazariy.places.domain.usecases.GetPlaces;
import com.example.nazariy.places.presentation.main.model.ViewLocation;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.main.view.PlacesListMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

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
    public void getPlaces(String location, int radius) {
        compositeDisposable.add(getPlacesUseCase.createObservable(location, radius)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeResult -> placeResult.getMeta().getCode() < 400)
                .map(placeResult -> placeResult.getResponse().getVenues())
                .map(venues -> {
                    List<ViewVenue> viewVenues = new ArrayList<>();
                    for (Venue venue : venues) {
                        ViewVenue viewVenueItem = new ViewVenue();
                        viewVenueItem.setCategories(venue.getCategories());
                        viewVenueItem.setId(venue.getId());
                        viewVenueItem.setName(venue.getName());
                        viewVenueItem.setVenuePage(venue.getVenuePage());

                        Location venueLocation = venue.getLocation();

                        ViewLocation viewLocation = new ViewLocation();
                        viewLocation.setAddress(venueLocation.getAddress());
                        viewLocation.setDistance(venueLocation.getDistance());
                        viewLocation.setFormattedAddress(venueLocation.getFormattedAddress());
                        viewLocation.setLat(venueLocation.getLat());
                        viewLocation.setLng(venueLocation.getLng());

                        viewVenueItem.setLocation(viewLocation);

                        viewVenues.add(viewVenueItem);
                    }
                    return viewVenues;
                })
                .subscribe(venues -> ifViewAttached(view -> {
                            view.showProgressBar();
                            view.obtainResults(venues);
                        }),
                        error -> Log.d(TAG, "getPlaces: " + error.getMessage()),
                        () -> ifViewAttached(PlacesListMvpView::hideProgressBar)
                ));
    }
}
