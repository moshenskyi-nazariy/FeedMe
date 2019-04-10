package com.example.nazariy.places.presentation.main.view.recyclerview;

import android.view.View;

public interface VenueListener {
    void onEstablishmentClick(String venueId, String venueName, View sharedElement);
}