package com.example.nazariy.places.presentation.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.repository.PlacesRepositoryImpl;
import com.example.nazariy.places.domain.usecases.GetPlaces;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.main.presenter.PlaceListMvpPresenter;
import com.example.nazariy.places.presentation.main.presenter.PlaceListPresenter;
import com.example.nazariy.places.presentation.main.view.recyclerview.PlacesAdapter;
import com.example.nazariy.places.presentation.main.view.recyclerview.SpaceItemDecoration;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.List;

public class MainActivity extends MvpActivity<PlacesListMvpView, PlaceListMvpPresenter>
        implements PlacesListMvpView {
    private ProgressBar loadingIndicator;
    private PlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingIndicator = findViewById(R.id.main__loading_indicator);
        setupRecycler();

        getPresenter().getPlaces("-33.8670522,151.1957362", 5000, 0, 10000, true);
    }

    private void setupRecycler() {
        adapter = new PlacesAdapter();
        RecyclerView placeList = findViewById(R.id.main__place_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        placeList.setAdapter(adapter);
        placeList.setLayoutManager(layoutManager);
        placeList.addItemDecoration(new SpaceItemDecoration(8));
    }

    @NonNull
    @Override
    public PlaceListMvpPresenter createPresenter() {
        return new PlaceListPresenter(new GetPlaces(new PlacesRepositoryImpl()));
    }

    @Override
    public void showProgressBar() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void obtainResults(List<ViewVenue> placeResult) {
        adapter.update(placeResult);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
