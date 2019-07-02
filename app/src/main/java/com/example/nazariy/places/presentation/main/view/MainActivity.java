package com.example.nazariy.places.presentation.main.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nazariy.places.R;
import com.example.nazariy.places.data.datasource.DataSourceImpl;
import com.example.nazariy.places.data.repository.remote.RemoteRepository;
import com.example.nazariy.places.domain.entities.places.Category;
import com.example.nazariy.places.presentation.base.BaseLoadingActivity;
import com.example.nazariy.places.presentation.base.ISorter;
import com.example.nazariy.places.presentation.base.view_model.PlaceListViewModelFactory;
import com.example.nazariy.places.presentation.base.view_model.ViewModelFactory;
import com.example.nazariy.places.presentation.details.view.DetailsActivity;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.main.utils.LocationUtils;
import com.example.nazariy.places.presentation.main.view.delegate.MainRecyclerDelegate;
import com.example.nazariy.places.presentation.main.view.recyclerview.venues.PlacesAdapter;
import com.example.nazariy.places.presentation.main.view.recyclerview.venues.VenueListDiffCallback;
import com.example.nazariy.places.presentation.main.view.recyclerview.venues.VenueListener;
import com.example.nazariy.places.presentation.main.viewmodel.PlaceListViewModel;
import com.example.nazariy.places.presentation.sign_in.google.GoogleSignInMethod;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import static com.example.nazariy.places.presentation.sign_in.SignInActivity.GOOGLE_REQUEST_CODE;

public class MainActivity extends BaseLoadingActivity implements LocationListener,
        VenueListener, FilterDialog.OnCompleteListener {

    private static final int ALL_PERMISSIONS_RESULT = 777;
    public static final String PROFILE_NAME = "Name";

    private MainRecyclerDelegate recyclerDelegate;

    private PlaceListViewModel placeListViewModel;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private List<ViewVenue> venues;

    private ISorter<ViewVenue> venueSorter;
    private TextView subtitle;
    private String userName;
    private TextView loginButton;
    private TextView logoutButton;

    private TextView searchButton;

    private GoogleSignInMethod googleSignInMethod;
    private LinearLayoutCompat searchContent;

    public static void start(Context context, String userName) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(PROFILE_NAME, userName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleSignInMethod = new GoogleSignInMethod(this);
        googleSignInMethod.init();

        userName = getIntent().getStringExtra(PROFILE_NAME);
        updateBackdropBackLayer();

        setupToolbar();

        setupRecyclerDelegate();

        venueSorter = new VenueSorter(getApplicationContext());

        setupUi();
        setupRecycler();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        setupViewModel();
    }

    private void updateBackdropBackLayer() {
        logoutButton = findViewById(R.id.backdrop_log_out);
        loginButton = findViewById(R.id.backdrop_log_in);
        if (googleSignInMethod.isSignedIn()) {
            loginButton.setVisibility(View.GONE);
        } else {
            logoutButton.setVisibility(View.GONE);
        }
    }

    private void setupUi() {
        loadingIndicator = findViewById(R.id.details__loading_indicator);
        subtitle = findViewById(R.id.subtitle);
        searchContent = findViewById(R.id.search_content);

        TextView searchItem = findViewById(R.id.backdrop_search_item);
        searchItem.setOnClickListener(searchItemView -> {
            if (searchItemView.isSelected()) {
                searchItemView.setSelected(false);
                searchContent.setVisibility(View.VISIBLE);
                placeListViewModel.getAllCategories();
            }
            else {
                searchItemView.setSelected(true);
                searchContent.setVisibility(View.GONE);
            }
        });

        loginButton.setOnClickListener(view -> startActivityForResult(googleSignInMethod.getSignIntent(),
                GOOGLE_REQUEST_CODE));

        logoutButton.setOnClickListener(view -> googleSignInMethod.signOut().addOnSuccessListener(this,
                taskResult -> {
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                }));
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                findViewById(R.id.backdrop),
                findViewById(R.id.backdrop_back_layer),
                new AccelerateDecelerateInterpolator(),
                getDrawable(R.drawable.ic_open_backdrop_24dp),
                getDrawable(R.drawable.ic_arrow_back)
        ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_REQUEST_CODE && resultCode == RESULT_OK) {
            MainActivity.start(this, googleSignInMethod.getName());
        }
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationUtils.requestLocationPermissions(this, ALL_PERMISSIONS_RESULT);
        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener
                    (this::onLocationChanged);
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
        placeListViewModel = ViewModelProviders.of(this, new ViewModelFactory(new
                PlaceListViewModelFactory(
                new DataSourceImpl(new RemoteRepository())))).get(PlaceListViewModel.class);

        placeListViewModel.errorMessage.observe(this, this::showMessage);
        placeListViewModel.venueList.observe(this, this::obtainResults);
        placeListViewModel.categories.observe(this, this::obtainCategories);
    }

    private void obtainCategories(List<Category> categories) {

    }

    private void setupRecyclerDelegate() {
        recyclerDelegate = new MainRecyclerDelegate(findViewById(R.id.main__place_list));
        recyclerDelegate.bind(this);
    }

    private void setupRecycler() {
        recyclerDelegate.setupRecycler(new PlacesAdapter(this, new VenueListDiffCallback()));
    }

    public void obtainResults(List<ViewVenue> placeResult) {
        recyclerDelegate.obtainResults(placeResult);
        venues = placeResult;
        subtitle.setText(getResources().getQuantityString(R.plurals.backdrop_subtitle, venues
                .size(), venues.size()));
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

        placeListViewModel.getPlaces(locationData, 5000, "cafe");
    }

    @Override
    public void onEstablishmentClick(String venueId, String venueName, View sharedElement) {
        Intent detailsIntent = new Intent(this, DetailsActivity.class);
        detailsIntent.putExtra(DetailsActivity.VENUE_ID, venueId);
        detailsIntent.putExtra(DetailsActivity.VENUE_NAME, venueName);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeScaleUpAnimation(sharedElement, (int) sharedElement.getX(), (int)
                                sharedElement.getY(),
                        sharedElement.getWidth(), sharedElement.getHeight());
        startActivity(detailsIntent, options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_filter) {
            FilterDialog filterDialog = new FilterDialog();
            filterDialog.show(getSupportFragmentManager(), null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFilterPicked(String sortingType) {
        if (venues.size() > 0) {
            venues = venueSorter.sort(sortingType, venues);
            recyclerDelegate.swapLists(venues);
        }
    }
}
