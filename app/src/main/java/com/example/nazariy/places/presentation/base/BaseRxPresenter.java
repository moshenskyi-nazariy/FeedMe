package com.example.nazariy.places.presentation.base;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

public class BaseRxPresenter<V extends MvpView> extends MvpBasePresenter<V> {
    protected CompositeDisposable compositeDisposable;

    @Override
    public void attachView(@NonNull V view) {
        super.attachView(view);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

}
