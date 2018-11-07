package com.example.nazariy.places.presentation.details.view;

import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface DetailsMvpView extends MvpView {

    void obtainDetails(Venue result);

    void obtainPhotos(Photos photos);

    void showMessage(String message);
}
