package com.example.nazariy.places.presentation.main.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.datasource.DataSourceImpl;
import com.example.nazariy.places.presentation.base.BaseLoadingActivity;
import com.example.nazariy.places.presentation.base.ViewModelFactory;
import com.example.nazariy.places.presentation.details.view.DetailsActivity;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.main.utils.LocationUtils;
import com.example.nazariy.places.presentation.main.view.delegate.MainRecyclerDelegate;
import com.example.nazariy.places.presentation.main.view.recyclerview.PlacesAdapter;
import com.example.nazariy.places.presentation.main.view.recyclerview.VenueListener;
import com.example.nazariy.places.presentation.main.viewmodel.PlaceListViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends BaseLoadingActivity implements LocationListener,
        VenueListener {

    private static final int ALL_PERMISSIONS_RESULT = 777;

    private MainRecyclerDelegate recyclerDelegate;

    private PlaceListViewModel placeListViewModel;

    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        setupRecyclerDelegate();

        loadingIndicator = findViewById(R.id.details__loading_indicator);
        setupRecycler();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        setupViewModel();
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationUtils.requestLocationPermissions(this, ALL_PERMISSIONS_RESULT);
        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this::onLocationChanged);
            fusedLocationProviderClient.requestLocationUpdates(new LocationRequest(),
                    new LocationCallback(), getMainLooper());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener
                    (this::onLocationChanged);
        }
    }

    private void setupViewModel() {
        placeListViewModel = ViewModelProviders.of(this, new ViewModelFactory(new DataSourceImpl()))
                .get(PlaceListViewModel.class);

        placeListViewModel.errorMessage.observe(this, this::showMessage);
        placeListViewModel.venueList.observe(this, this::obtainResults);
    }

    private void setupRecyclerDelegate() {
        recyclerDelegate = new MainRecyclerDelegate();
        recyclerDelegate.bind(this);
    }

    private void setupRecycler() {
        recyclerDelegate.setupRecycler(findViewById(R.id.main__place_list), new PlacesAdapter
                (this));
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

    @Override
    public void onLocationChanged(Location location) {
        String locationData = String.format(Locale.US, "%f, %f", location.getLatitude(),
                location.getLongitude());

        placeListViewModel.getPlaces(locationData, 5000);
    }

    @Override
    public void onEstablishmentClick(String venueId, String venueName, View sharedElement) {
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra(DetailsActivity.VENUE_ID, venueId);
        detailsIntent.putExtra(DetailsActivity.VENUE_NAME, venueName);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(sharedElement, (int) sharedElement.getX(), (int) sharedElement.getY(),
                        sharedElement.getWidth(), sharedElement.getHeight());
        startActivity(detailsIntent, options.toBundle());
    }
}
