package com.example.nazariy.places.presentation.base.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private ViewModelAbstractFactory factory;

    public ViewModelFactory(ViewModelAbstractFactory factory) {
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public ViewModel create(@NonNull Class modelClass) {
        return factory.createViewModel();
    }
}
