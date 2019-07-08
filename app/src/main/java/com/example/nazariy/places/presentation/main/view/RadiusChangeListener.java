package com.example.nazariy.places.presentation.main.view;

import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nazariy.places.presentation.main.view.utils.ChipUtils;
import com.example.nazariy.places.presentation.main.viewmodel.PlaceListViewModel;
import com.google.android.material.chip.ChipGroup;

public class RadiusChangeListener implements SeekBar.OnSeekBarChangeListener {
    private TextView radiusValue;
    private String locationData;
    private ChipGroup categoryChipGroup;
    private PlaceListViewModel placeListViewModel;

    public RadiusChangeListener(TextView radiusValue, String locationData, ChipGroup
            categoryChipGroup, PlaceListViewModel placeListViewModel) {
        this.radiusValue = radiusValue;
        this.locationData = locationData;
        this.categoryChipGroup = categoryChipGroup;
        this.placeListViewModel = placeListViewModel;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        radiusValue.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        placeListViewModel.getPlaces(locationData, Integer.valueOf(radiusValue.getText().toString()),
                ChipUtils.getChildren(categoryChipGroup));
    }
}
