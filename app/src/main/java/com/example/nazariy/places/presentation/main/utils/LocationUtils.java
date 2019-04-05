package com.example.nazariy.places.presentation.main.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

public class LocationUtils {
    public static void requestLocationPermissions(Activity context, int permissionResult) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                Manifest.permission.ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            new AlertDialog.Builder(context).
                    setMessage("These permissions are mandatory to get your " +
                            "location. You need to allow them.").
                    setPositiveButton("OK", (dialogInterface, i) ->
                            ActivityCompat.requestPermissions(context,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION},
                                    permissionResult))
                    .setNegativeButton("Cancel", null).create().show();
        } else {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    permissionResult);
        }
    }
}
