package com.example.nazariy.places.presentation.main.viewmodel;


import android.text.TextUtils;
import android.util.Log;

import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.domain.entities.places.Category;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.base.view_model.BaseRxViewModel;
import com.example.nazariy.places.presentation.main.model.VenueMapper;
import com.example.nazariy.places.presentation.main.model.ViewLocation;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PlaceListViewModel extends BaseRxViewModel {
    private static final String TAG = "PlaceListViewModel";

    private final DataSource placesRepository;

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<List<ViewVenue>> venueList = new MutableLiveData<>();
    public MutableLiveData<Photos> photos = new MutableLiveData<>();
    public MutableLiveData<List<Category>> categories = new MutableLiveData<>();

    public PlaceListViewModel(DataSource placesRepository) {
        this.placesRepository = placesRepository;
    }

    public void getPlaces(String location, int radius, String query) {
        isLoading.setValue(true);
        compositeDisposable.add(placesRepository.getPlaces(location, radius, query)
                .map(VenueMapper::venueToViewVenue)
                .compose(filterVenues())
                .cache()
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(
                        venues -> venueList.setValue(venues),
                        error -> {
                            Log.d(TAG, "getPlaces: " + error.getMessage());
                            errorMessage.setValue(error.getMessage());
                        }
                ));
    }

    public void getAllCategories() {
        isLoading.setValue(true);
        compositeDisposable.add(placesRepository.getAllCategories()
                .flatMapIterable(list -> list)
                .filter(category -> !TextUtils.isEmpty(category.getName()))
                .toList()
                .cache()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(categoryList -> categories.setValue(categoryList),
                        error -> errorMessage.setValue(error.getMessage())));
    }

    private boolean validateViewVenue(ViewVenue viewVenue) {
        ViewLocation viewLocation = viewVenue.getLocation();
        boolean hasAddress = viewLocation != null && !TextUtils.isEmpty(viewLocation.getAddress());
        return hasAddress && viewVenue.getName() != null;
    }

    private ObservableTransformer<List<ViewVenue>, List<ViewVenue>> filterVenues() {
        return upstream -> upstream
                .flatMapIterable(list -> list)
                .filter(this::validateViewVenue)
                .toList()
                .toObservable();
    }

}
