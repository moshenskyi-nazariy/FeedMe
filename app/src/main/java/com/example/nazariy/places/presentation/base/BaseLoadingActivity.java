package com.example.nazariy.places.presentation.base;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public abstract class BaseLoadingActivity extends AppCompatActivity {
    protected ProgressBar loadingIndicator;

    public void showProgressBar() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        loadingIndicator.setVisibility(View.GONE);
    }
}
