package com.example.nazariy.places.presentation.main.view;


import com.example.nazariy.places.domain.entities.place_result.Result;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

public interface PlacesListMvpView extends MvpView {

    void showProgressBar();

    void hideProgressBar();

    void obtainResults(List<Result> placeResult);

    void showMessage(String message);

}
