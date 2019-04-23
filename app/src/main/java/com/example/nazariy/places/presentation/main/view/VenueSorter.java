package com.example.nazariy.places.presentation.main.view;

import android.content.Context;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.base.ISorter;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.Collections;
import java.util.List;

public class VenueSorter implements ISorter<ViewVenue> {
    private final String sortByNameAsc;
    private final String sortByNameDesc;
    private final String sortByDistanceNearest;

    VenueSorter(Context context) {
        sortByNameAsc = context.getResources().getString(R.string.sort_by_name_asc);
        sortByNameDesc = context.getResources().getString(R.string.sort_by_name_desc);
        sortByDistanceNearest = context.getResources().getString(R.string.sort_by_distance);
    }

    @Override
    public List<ViewVenue> sort(String sortingType, List<ViewVenue> venueList) {
        if (sortByNameAsc.equals(sortingType)) {
            Collections.sort(venueList, (firstVenue, secondVenue) -> firstVenue.getName()
                    .compareToIgnoreCase(secondVenue.getName()));
        } else if (sortByNameDesc.equals(sortingType)) {
            Collections.sort(venueList, (firstVenue, secondVenue) -> -(firstVenue.getName()
                    .compareToIgnoreCase(secondVenue.getName())));
        } else if (sortByDistanceNearest.equals(sortingType)) {
            Collections.sort(venueList, (firstVenue, secondVenue) ->
                    firstVenue.getLocation().getDistance() - secondVenue.getLocation().getDistance());
        } else {
            Collections.sort(venueList, (firstVenue, secondVenue) ->
                    secondVenue.getLocation().getDistance() - firstVenue.getLocation().getDistance());
        }
        return venueList;
    }
}
