package com.example.nazariy.places.presentation.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.repository.PlacesRepositoryImpl;
import com.example.nazariy.places.domain.entities.place_result.PlaceResult;
import com.example.nazariy.places.domain.usecases.GetPlaces;
import com.example.nazariy.places.presentation.main.presenter.PlaceListMvpPresenter;
import com.example.nazariy.places.presentation.main.presenter.PlaceListPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class MainActivity extends MvpActivity<PlacesListMvpView, PlaceListMvpPresenter>
                                                        implements PlacesListMvpView {
    private ProgressBar loadingIndicator;
    private RecyclerView placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingIndicator = findViewById(R.id.main__loading_indicator);
        placeList = findViewById(R.id.main__place_list);
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
    public void obtainResults(PlaceResult placeResult) {

    }
}
