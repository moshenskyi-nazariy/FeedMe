package com.example.nazariy.places.presentation.base;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseLoadingActivity extends AppCompatActivity {
    protected ProgressBar loadingIndicator;

    public void showProgressBar() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        loadingIndicator.setVisibility(View.GONE);
    }
}
