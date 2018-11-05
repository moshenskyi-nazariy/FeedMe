package com.example.nazariy.places.presentation.details.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nazariy.places.R;
import com.example.nazariy.places.data.repository.PlacesRepositoryImpl;
import com.example.nazariy.places.domain.entities.details.Stats;
import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.entities.details.photos.Item;
import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.presentation.details.presenter.DetailsMvpPresenter;
import com.example.nazariy.places.presentation.details.presenter.DetailsPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class DetailsActivity extends MvpActivity<DetailsMvpView, DetailsMvpPresenter>
        implements DetailsMvpView {
    private static final String VENUE_ID = "venue id";

    private ProgressBar loadingIndicator;
    private RatingBar venueRatingBar;
    private TextView checkinCount;
    private TextView detailsFromOwner;
    private TextView popularHours;

    private ImageView placePhoto;
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

        initViews();

        getPresenter().getPlaceDetails(venueId);
    }

    @NonNull
    @Override
    public DetailsMvpPresenter createPresenter() {
        return new DetailsPresenter(new PlacesRepositoryImpl());
    }

    private void initViews() {
        loadingIndicator = findViewById(R.id.details__loading_indicator);
        venueRatingBar = findViewById(R.id.details__ratingBar);
        checkinCount = findViewById(R.id.details__checkin_count);
        detailsFromOwner = findViewById(R.id.details__detail_from_owner);
        popularHours = findViewById(R.id.details__popular_hours);
        placePhoto = findViewById(R.id.details__place_photo);
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
    public void obtainDetails(Venue placeDetails) {
        if (placeDetails != null) {
            updateUi(placeDetails);
        }
    }

    @Override
    public void obtainPhotos(Photos photos) {
        Item photo = photos.getItems().get(0);
        Glide.with(this)
                .load(photo.getPrefix() + photo.getWidth() + photo.getHeight() + photo.getSuffix())
                .into(placePhoto);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateUi(Venue placeDetails) {

        getPresenter().getPhotos(venueId);

        addRating(placeDetails);

        addCheckins(placeDetails);

        addDescription(placeDetails);

        addPopularHours(placeDetails);
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

    private void addCheckins(Venue placeDetails) {
        Stats stats = placeDetails.getStats();
        if (stats != null) {
            int checkinsCount = stats.getCheckinsCount();
            checkinCount.setText(getString(
                    R.string.checkin_count_placeholder,
                    checkinsCount));
        }
    }
}
