package com.example.nazariy.places.presentation;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.main.utils.SharedPrefUtils;


public class FeedMeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        updateSharedPreferences();
    }

    public void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                SharedPrefUtils.generateSharedPrefName(this), Context.MODE_PRIVATE);

        boolean isSortingTypeExists = sharedPreferences.contains(getString(R.string.sorting_type));
        if (!isSortingTypeExists) {
            sharedPreferences
                    .edit()
                    .putString(getString(R.string.sorting_type), getDefaultSortingType())
                    .apply();
        }
    }

    private String getDefaultSortingType() {
        return getResources().getStringArray(R.array.available_sorting_types)[0];
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateSharedPreferences();
    }
}
