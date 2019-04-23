package com.example.nazariy.places.presentation.main.view.recyclerview.venues;

import android.os.Bundle;

import com.example.nazariy.places.presentation.main.model.ViewVenue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class VenueListDiffCallback extends DiffUtil.ItemCallback<ViewVenue> {
    static final String KEY_CATEGORIES = "categories";
    static final String KEY_NAME = "name";
    static final String KEY_LOCATION = "location";
    static final String KEY_DISTANCE = "distance";

    @Nullable
    @Override
    public Object getChangePayload(@NonNull ViewVenue oldVenue, @NonNull ViewVenue newVenue) {
        Bundle diffBundle = new Bundle();
        if (newVenue.getCategories().size() != oldVenue.getCategories().size()) {
            diffBundle.putInt(KEY_CATEGORIES, newVenue.getCategories().size());
        }
        if (!newVenue.getName().equals(oldVenue.getName())) {
            diffBundle.putString(KEY_NAME, newVenue.getName());
        }
        if (newVenue.getLocation().getDistance() != oldVenue.getLocation().getDistance()) {
            diffBundle.putInt(KEY_DISTANCE, newVenue.getLocation().getDistance());
        }
        if (diffBundle.size() == 0) return null;
        return diffBundle;
    }

    @Override
    public boolean areItemsTheSame(@NonNull ViewVenue oldItem, @NonNull ViewVenue newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ViewVenue oldItem, @NonNull ViewVenue newItem) {
        return oldItem.equals(newItem);
    }
}
