package com.example.nazariy.places.presentation.base.view_model;

import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.main.viewmodel.PlaceListViewModel;

import androidx.lifecycle.ViewModel;

public class PlaceListViewModelFactory implements ViewModelAbstractFactory {
    private DataSource dataSource;

    public PlaceListViewModelFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ViewModel createViewModel() {
        return new PlaceListViewModel(dataSource);
    }
}
