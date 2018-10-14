package com.example.nazariy.places.presentation.details.presenter;

import com.example.nazariy.places.presentation.details.view.DetailsMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface DetailsMvpPresenter extends MvpPresenter<DetailsMvpView> {
    void getPlaceDetails(String id);
}
