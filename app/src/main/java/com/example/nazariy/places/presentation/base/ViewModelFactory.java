package com.example.nazariy.places.presentation.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.details.viewmodel.DetailsViewModel;
import com.example.nazariy.places.presentation.main.viewmodel.PlaceListViewModel;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private DataSource dataSource;

    public ViewModelFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public ViewModel create(@NonNull Class modelClass) {
        if (modelClass == DetailsViewModel.class) {
            return new DetailsViewModel(dataSource);
        } else {
            return new PlaceListViewModel(dataSource);
        }
    }
}
