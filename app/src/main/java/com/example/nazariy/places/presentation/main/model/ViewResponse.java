package com.example.nazariy.places.presentation.main.model;

import com.example.nazariy.places.domain.entities.Venue;

import java.util.List;
import java.util.Objects;

public class ViewResponse {
    private List<Venue> venues = null;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewResponse)) return false;
        ViewResponse that = (ViewResponse) o;
        return Objects.equals(venues, that.venues);
    }

    @Override
    public int hashCode() {

        return Objects.hash(venues);
    }
}
