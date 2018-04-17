package com.example.nazariy.places.presentation.main.presenter;


import com.example.nazariy.places.presentation.main.view.PlacesListMvpView;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

public interface PlaceListMvpPresenter extends MvpPresenter<PlacesListMvpView> {
    void getPlaces(String location, int radius, int minPrice, int maxPrice, boolean isOpened);
}
