package com.example.nazariy.places.presentation.main.model;

import com.example.nazariy.places.domain.entities.places.Location;
import com.example.nazariy.places.domain.entities.places.Venue;

import java.util.ArrayList;
import java.util.List;

public class VenueMapper {
    public static List<ViewVenue> venueToViewVenue(List<Venue> venues) {
        List<ViewVenue> viewVenues = new ArrayList<>();
        for (Venue venue : venues) {
            ViewVenue viewVenueItem = new ViewVenue();
            viewVenueItem.setCategories(venue.getCategories());
            viewVenueItem.setId(venue.getId());
            viewVenueItem.setName(venue.getName());
            viewVenueItem.setVenuePage(venue.getVenuePage());

            Location venueLocation = venue.getLocation();

            ViewLocation viewLocation = new ViewLocation();
            viewLocation.setAddress(venueLocation.getAddress());
            viewLocation.setDistance(venueLocation.getDistance());
            viewLocation.setFormattedAddress(venueLocation.getFormattedAddress());
            viewLocation.setLat(venueLocation.getLat());
            viewLocation.setLng(venueLocation.getLng());

            viewVenueItem.setLocation(viewLocation);

            viewVenues.add(viewVenueItem);
        }
        return viewVenues;
    }
}
