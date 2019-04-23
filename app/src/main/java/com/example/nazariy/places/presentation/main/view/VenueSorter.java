package com.example.nazariy.places.presentation.main.view;

import com.example.nazariy.places.presentation.base.ISorter;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.Collections;
import java.util.List;

public class VenueSorter implements ISorter<ViewVenue> {
    private static final String SORT_BY_NAME_ASC = "By name(asc)";
    private static final String SORT_BY_NAME_DESC = "By name(desc)";
    private static final String SORT_BY_DISTANCE_NEAREST = "By distance(nearest first)";

    @Override
    public List<ViewVenue> sort(String sortingType, List<ViewVenue> venueList) {
        if (SORT_BY_NAME_ASC.equals(sortingType)) {
            Collections.sort(venueList, (firstVenue, secondVenue) -> firstVenue.getName()
                    .compareToIgnoreCase(secondVenue.getName()));
        } else if (SORT_BY_NAME_DESC.equals(sortingType)) {
            Collections.sort(venueList, (firstVenue, secondVenue) -> -(firstVenue.getName()
                    .compareToIgnoreCase(secondVenue.getName())));
        } else if (SORT_BY_DISTANCE_NEAREST.equals(sortingType)) {
            Collections.sort(venueList, (firstVenue, secondVenue) ->
                    firstVenue.getLocation().getDistance() - secondVenue.getLocation().getDistance());
        } else {
            Collections.sort(venueList, (firstVenue, secondVenue) ->
                    secondVenue.getLocation().getDistance() - firstVenue.getLocation().getDistance());
        }
        return venueList;
    }
}
