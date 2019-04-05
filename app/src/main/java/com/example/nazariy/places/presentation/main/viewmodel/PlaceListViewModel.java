package com.example.nazariy.places.presentation.main.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.base.BaseRxViewModel;
import com.example.nazariy.places.presentation.main.model.VenueMapper;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PlaceListViewModel extends BaseRxViewModel {
    private static final String TAG = "PlaceListViewModel";

    private final DataSource placesRepository;

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<List<ViewVenue>> venueList = new MutableLiveData<>();

    public PlaceListViewModel(DataSource placesRepository) {
        // make Singleton to substitute usecases
        this.placesRepository = placesRepository;
    }

    public void getPlaces(String location, int radius) {
        isLoading.setValue(true);
        compositeDisposable.add(placesRepository.getPlaces(location, radius)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeResult -> placeResult.getMeta().getCode() < 400)
                .map(placeResult -> placeResult.getResponse().getVenues())
                .map(VenueMapper::venueToViewVenue)
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(
                        venues -> venueList.setValue(venues),
                        error -> {
                            Log.d(TAG, "getPlaces: " + error.getMessage());
                            errorMessage.setValue(error.getMessage());
                        }
                ));
    }
}
