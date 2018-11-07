package com.example.nazariy.places.presentation.base;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class BaseLoadingActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> implements LoadingMvpView {
    protected ProgressBar loadingIndicator;

    @NonNull
    @Override
    public abstract P createPresenter();

    @Override
    public void showProgressBar() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loadingIndicator.setVisibility(View.GONE);
    }
}
