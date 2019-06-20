package com.example.nazariy.places.presentation.base.view_model;

import com.example.nazariy.places.domain.interfaces.DataSource;
import com.example.nazariy.places.presentation.details.viewmodel.DetailsViewModel;

import androidx.lifecycle.ViewModel;

public class DetailsViewModelFactory implements ViewModelAbstractFactory {
    private DataSource dataSource;

    public DetailsViewModelFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ViewModel createViewModel() {
        return new DetailsViewModel(dataSource);
    }
}
