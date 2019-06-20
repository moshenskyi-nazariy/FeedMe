package com.example.nazariy.places.presentation.details.viewmodel;

import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResponse;
import com.example.nazariy.places.domain.entities.details.photos.PhotoResult;
import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.base.view_model.BaseRxViewModel;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailsViewModel extends BaseRxViewModel {

    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Venue> venue = new MutableLiveData<>();
    public MutableLiveData<Photos> photos = new MutableLiveData<>();
    private DataSource dataSource;

    public DetailsViewModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void getPlaceDetails(String id) {
        isLoading.setValue(true);
        compositeDisposable.add(dataSource.getPlaceDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::obtainVenue, this::handleVenueLoadingError));
    }

    private void handleVenueLoadingError(Throwable error) {
        errorMessage.setValue(error.getMessage());
        isLoading.setValue(false);
    }

    private void obtainVenue(Venue resultVenue) {
        venue.setValue(resultVenue);
        isLoading.setValue(false);
    }

    public void getPhotos(String id) {
        isLoading.setValue(true);
        compositeDisposable.add(dataSource.getPhotos(id)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(this::obtainPhoto, error -> errorMessage.setValue(error.getMessage())));
    }

    private void obtainPhoto(PhotoResult placeDetailsResult) {
        PhotoResponse response = placeDetailsResult.getResponse();
        if (response != null && response.getPhotos().getCount() > 0)
            photos.setValue(response.getPhotos());
        else errorMessage.setValue("Nothing found");
    }

}
