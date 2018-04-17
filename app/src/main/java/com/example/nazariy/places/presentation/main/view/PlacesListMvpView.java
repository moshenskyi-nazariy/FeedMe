package com.example.nazariy.places.presentation.main.view;


import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface PlacesListMvpView extends MvpView {

    void showProgressBar();

    void hideProgressBar();

    void obtainResults(PlaceResult placeResult);

}
