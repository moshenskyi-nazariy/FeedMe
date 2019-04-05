package com.example.nazariy.places.presentation.main.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import android.widget.Toast;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.datasource.DataSourceImpl;
import com.example.nazariy.places.presentation.base.BaseLoadingActivity;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.base.ViewModelFactory;
import com.example.nazariy.places.presentation.main.viewmodel.PlaceListViewModel;
import com.example.nazariy.places.presentation.main.view.delegate.MainRecyclerDelegate;

import java.util.List;

public class MainActivity extends BaseLoadingActivity {

    private MainRecyclerDelegate recyclerDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerDelegate();

        loadingIndicator = findViewById(R.id.details__loading_indicator);
        setupRecycler();

        setupViewModel();
    }

    private void setupViewModel() {
        PlaceListViewModel placeListViewModel = ViewModelProviders.of(this, new ViewModelFactory(new DataSourceImpl()))
                .get(PlaceListViewModel.class);
        placeListViewModel.getPlaces("-33.8670522,151.1957362", 5000);

        placeListViewModel.errorMessage.observe(this, this::showMessage);
        placeListViewModel.venueList.observe(this, this::obtainResults);
    }

    private void setupRecyclerDelegate() {
        recyclerDelegate = new MainRecyclerDelegate();
        recyclerDelegate.bind(this);
    }

    private void setupRecycler() {
        recyclerDelegate.setupRecycler(findViewById(R.id.main__place_list));
    }

    public void obtainResults(List<ViewVenue> placeResult) {
        recyclerDelegate.obtainResults(placeResult);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerDelegate.unbind();
    }
}
