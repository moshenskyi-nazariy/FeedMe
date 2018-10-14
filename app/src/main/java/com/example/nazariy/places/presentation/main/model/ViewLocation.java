package com.example.nazariy.places.presentation.main.model;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import java.util.List;
import java.util.Objects;

public class ViewLocation {
    private SpannableString address;
    private double lat;
    private double lng;
    private int distance;
    private List<String> formattedAddress = null;

    public SpannableString getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null) {
            this.address = new SpannableString(address);
            this.address.setSpan(new UnderlineSpan(), 0, address.length(), 0);
        } else {
            this.address = new SpannableString("");
        }
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewLocation)) return false;
        ViewLocation viewLocation = (ViewLocation) o;
        return Double.compare(viewLocation.lat, lat) == 0 &&
                Double.compare(viewLocation.lng, lng) == 0 &&
                distance == viewLocation.distance &&
                Objects.equals(address, viewLocation.address) &&
                Objects.equals(formattedAddress, viewLocation.formattedAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, lat, lng, distance, formattedAddress);
    }
}
