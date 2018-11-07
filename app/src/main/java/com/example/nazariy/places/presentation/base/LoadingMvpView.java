package com.example.nazariy.places.presentation.base;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface LoadingMvpView extends MvpView {
    void showProgressBar();

    void hideProgressBar();
}
