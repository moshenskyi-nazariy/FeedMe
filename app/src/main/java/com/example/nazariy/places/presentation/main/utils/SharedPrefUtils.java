package com.example.nazariy.places.presentation.main.utils;

import android.content.Context;

import com.example.nazariy.places.R;

public class SharedPrefUtils {
    public static String generateSharedPrefName(Context context) {
        return context.getString(R.string.preference_file_key) + context.getResources().getConfiguration().locale;
    }
}
