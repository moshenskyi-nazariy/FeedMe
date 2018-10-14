package com.example.nazariy.places.presentation.details.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.repository.PlacesRepositoryImpl;
import com.example.nazariy.places.domain.entities.details.PlaceDetailsResult;
import com.example.nazariy.places.domain.usecases.GetPlaceDetails;
import com.example.nazariy.places.presentation.details.presenter.DetailsMvpPresenter;
import com.example.nazariy.places.presentation.details.presenter.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity implements DetailsMvpView {
    private static final String VENUE_ID = "venue id";

    private DetailsMvpPresenter presenter;
    private String venueId;

    public static void start(Context context, String venueId) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra(VENUE_ID, venueId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        venueId = getIntent().getStringExtra(VENUE_ID);

        presenter = new DetailsPresenter(new GetPlaceDetails(new PlacesRepositoryImpl()));
        presenter.attachView(this);
        presenter.getPlaceDetails(venueId);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void obtainResult(PlaceDetailsResult result) {

    }
}
