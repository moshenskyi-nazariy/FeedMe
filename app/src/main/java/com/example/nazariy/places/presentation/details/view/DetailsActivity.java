package com.example.nazariy.places.presentation.details.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.repository.PlacesRepositoryImpl;
import com.example.nazariy.places.domain.entities.details.Hours;
import com.example.nazariy.places.domain.entities.details.Stats;
import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.usecases.GetPlaceDetails;
import com.example.nazariy.places.presentation.details.presenter.DetailsMvpPresenter;
import com.example.nazariy.places.presentation.details.presenter.DetailsPresenter;

public class DetailsActivity extends AppCompatActivity implements DetailsMvpView {
    private static final String VENUE_ID = "venue id";

    private DetailsMvpPresenter presenter;
    private String venueId;

    private ProgressBar loadingIndicator;
    private RatingBar venueRatingBar;
    private TextView openingHours;
    private TextView checkinCount;
    private TextView detailsFromOwner;
    private TextView popularHours;

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

        initViews();

        presenter = new DetailsPresenter(new GetPlaceDetails(new PlacesRepositoryImpl()));
        presenter.attachView(this);
        presenter.getPlaceDetails(venueId);
    }

    private void initViews() {
        loadingIndicator = findViewById(R.id.details__loading_indicator);
        venueRatingBar = findViewById(R.id.details__ratingBar);
        openingHours = findViewById(R.id.details__opening_hours);
        checkinCount = findViewById(R.id.details__checkin_count);
        detailsFromOwner = findViewById(R.id.details__detail_from_owner);
        popularHours = findViewById(R.id.details__popular_hours);
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
    public void obtainResult(Venue placeDetails) {
        if (placeDetails != null) {
            addRating(placeDetails);

            Hours hours = placeDetails.getHours();
            // add opening hours

            addOpeningHours(placeDetails);

            addDescription(placeDetails);

            addPopularHours(placeDetails);
        }
    }

    private void addPopularHours(Venue placeDetails) {
        String renderedTime = placeDetails.getPopular()
                .getTimeframes().get(0)
                .getOpen().get(0).getRenderedTime();
        popularHours.setText(getString(
                R.string.popular_hours_placeholder,
                renderedTime));
    }

    private void addRating(Venue placeDetails) {
        double rating = placeDetails.getRating();
        venueRatingBar.setRating((float) rating);
    }

    private void addDescription(Venue placeDetails) {
        String description = placeDetails.getDescription();
        if (description != null)
            detailsFromOwner.setText(description);
    }

    private void addOpeningHours(Venue placeDetails) {
        Stats stats = placeDetails.getStats();
        if (stats != null) {
            int checkinsCount = stats.getCheckinsCount();
            checkinCount.setText(getString(
                    R.string.checkin_count_placeholder,
                    checkinsCount));
        }
    }
}
