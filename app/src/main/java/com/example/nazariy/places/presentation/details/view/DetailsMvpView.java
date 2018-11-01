package com.example.nazariy.places.presentation.details.view;

import com.example.nazariy.places.domain.entities.details.Venue;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface DetailsMvpView extends MvpView {
    void showProgressBar();

    void hideProgressBar();

    void obtainResult(Venue result);
}
