package com.example.nazariy.places.presentation.details.view;

import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.presentation.base.LoadingMvpView;

public interface DetailsMvpView extends LoadingMvpView {

    void obtainDetails(Venue result);

    void obtainPhotos(Photos photos);

    void showMessage(String message);
}
