package com.example.nazariy.places.presentation.main.view.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.List;

public class VenueListDiffCallback extends DiffUtil.Callback {
    static final String KEY_CATEGORIES = "categories";
    static final String KEY_NAME = "name";
    static final String KEY_LOCATION = "location";
    static final String KEY_DISTANCE = "distance";


    private final List<ViewVenue> mOldList;
    private final List<ViewVenue> mNewList;

    VenueListDiffCallback(List<ViewVenue> oldList, List<ViewVenue> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList != null ? mOldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewList != null ? mNewList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).getId().equals(mOldList.get(oldItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ViewVenue newVenue = mNewList.get(newItemPosition);
        ViewVenue oldVenue = mOldList.get(oldItemPosition);
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

}
