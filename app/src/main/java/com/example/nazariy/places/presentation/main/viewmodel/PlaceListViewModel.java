package com.example.nazariy.places.presentation.main.viewmodel;


import android.text.TextUtils;
import android.util.Log;

import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.base.BaseRxViewModel;
import com.example.nazariy.places.presentation.main.model.VenueMapper;
import com.example.nazariy.places.presentation.main.model.ViewLocation;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PlaceListViewModel extends BaseRxViewModel {
    private static final String TAG = "PlaceListViewModel";

    private final DataSource placesRepository;

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<List<ViewVenue>> venueList = new MutableLiveData<>();
    public MutableLiveData<Photos> photos = new MutableLiveData<>();

    public PlaceListViewModel(DataSource placesRepository) {
        // make Singleton to substitute usecases
        this.placesRepository = placesRepository;
    }

    public void getPlaces(String location, int radius) {
        isLoading.setValue(true);
        compositeDisposable.add(placesRepository.getPlaces(location, radius)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeResult -> placeResult.getMeta().getCode() < 400)
                .map(placeResult -> VenueMapper.venueToViewVenue(placeResult.getResponse().getVenues()))
                .flatMapIterable(list -> list)
                .filter(viewVenue -> {
                    ViewLocation viewLocation = viewVenue.getLocation();
                    boolean hasAddress = viewLocation != null && !TextUtils.isEmpty(viewLocation.getAddress());
                    return hasAddress && viewVenue.getName() != null;
                })
                .toList()
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
