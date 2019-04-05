package com.example.nazariy.places.presentation.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseRxViewModel extends ViewModel {
    protected CompositeDisposable compositeDisposable;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public BaseRxViewModel() {
        super();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
