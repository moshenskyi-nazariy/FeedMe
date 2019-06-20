package com.example.nazariy.places.presentation.details.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nazariy.places.R;
import com.example.nazariy.places.data.datasource.DataSourceImpl;
import com.example.nazariy.places.data.repository.remote.RemoteRepository;
import com.example.nazariy.places.domain.entities.details.Stats;
import com.example.nazariy.places.domain.entities.details.Venue;
import com.example.nazariy.places.domain.entities.details.photos.Item;
import com.example.nazariy.places.domain.entities.details.photos.Photos;
import com.example.nazariy.places.presentation.base.BaseLoadingActivity;
import com.example.nazariy.places.presentation.base.view_model.DetailsViewModelFactory;
import com.example.nazariy.places.presentation.base.view_model.ViewModelFactory;
import com.example.nazariy.places.presentation.details.viewmodel.DetailsViewModel;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

public class DetailsActivity extends BaseLoadingActivity {
    public static final String VENUE_ID = "venue id";
    public static final String VENUE_NAME = "venue name";

    private RatingBar venueRatingBar;
    private TextView checkinCount;
    private TextView detailsFromOwner;
    private TextView popularHours;

    private ImageView placePhoto;
    private ImageView verificationIndicator;
    private String venueId;

    private DetailsViewModel detailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setupToolbar(extras);
            venueId = extras.getString(VENUE_ID);
        }

        initViews();

        setupViewModel();
    }

    private void setupToolbar(Bundle extras) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(extras.getString(VENUE_NAME, getResources().getString(R.string
                .app_name)));
        toolbarTitle.setSelected(true);
    }

    private void setupViewModel() {
        detailsViewModel = ViewModelProviders.of(this, new ViewModelFactory(new DetailsViewModelFactory(
                new DataSourceImpl(new RemoteRepository())))).get(DetailsViewModel.class);
        detailsViewModel.getPlaceDetails(venueId);

        detailsViewModel.photos.observe(this, this::obtainPhotos);
        detailsViewModel.errorMessage.observe(this, this::showMessage);
        detailsViewModel.venue.observe(this, this::obtainDetails);

        detailsViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading != null && isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });
    }

    private void initViews() {
        loadingIndicator = findViewById(R.id.details__loading_indicator);
        venueRatingBar = findViewById(R.id.details__ratingBar);
        checkinCount = findViewById(R.id.details__checkin_count);
        detailsFromOwner = findViewById(R.id.details__detail_from_owner);
        popularHours = findViewById(R.id.details__popular_hours);
        placePhoto = findViewById(R.id.details__place_photo);
        verificationIndicator = findViewById(R.id.details__verification_indicator);
    }

    public void obtainDetails(Venue placeDetails) {
        if (placeDetails != null) {
            updateUi(placeDetails);
        }
    }

    public void obtainPhotos(Photos photos) {
        Item photo = photos.getItems().get(0);
        Glide.with(this)
                .load(photo.getPrefix() + photo.getWidth() + photo.getHeight() + photo.getSuffix())
                .into(placePhoto);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateUi(Venue placeDetails) {
        detailsViewModel.getPhotos(venueId);
        addVerificationStatus(placeDetails);

        addRating(placeDetails);

        addCheckins(placeDetails);

        addDescription(placeDetails);

        addPopularHours(placeDetails);
    }

    private void addVerificationStatus(Venue placeDetails) {
        if (placeDetails.isVerified()) {
            verificationIndicator.setVisibility(View.VISIBLE);
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
