package com.example.nazariy.places.presentation.details.viewmodel;

import com.example.nazariy.places.domain.entities.details.Response;
import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResponse;
import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.base.BaseRxViewModel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailsViewModel extends BaseRxViewModel {

    private DataSource dataSource;

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Venue> venue = new MutableLiveData<>();
    public MutableLiveData<Photos> photos = new MutableLiveData<>();

    public DetailsViewModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void getPlaceDetails(String id) {
        isLoading.setValue(true);
        compositeDisposable.add(dataSource.getPlaceDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeDetailsResult -> placeDetailsResult.getMeta().getCode() < 400)
                .subscribe(placeDetailsResult -> {
                            Response response = placeDetailsResult.getResponse();
                            venue.setValue(response.getVenue());
                            isLoading.setValue(false);
                        },
                        error -> {
                            errorMessage.setValue(error.getMessage());
                            isLoading.setValue(false);
                        }
                ));
    }

    public void getPhotos(String id) {
        isLoading.setValue(true);
        compositeDisposable.add(dataSource.getPhotos(id)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(placeDetailsResult -> placeDetailsResult.getMeta().getCode() < 400)
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(placeDetailsResult -> {
                            PhotoResponse response = placeDetailsResult.getResponse();
                            if (response != null && response.getPhotos().getCount() > 0)
                                photos.setValue(response.getPhotos());
                            else errorMessage.setValue("Nothing found");
                        },
                        error -> errorMessage.setValue(error.getMessage())
                ));
    }

}
