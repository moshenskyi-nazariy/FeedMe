
package com.example.nazariy.places.domain.entities;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("crossStreet")
    @Expose
    private String crossStreet;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("labeledLatLngs")
    @Expose
    private List<LabeledLatLng> labeledLatLngs = null;
    @SerializedName("distance")
    @Expose
    private int distance;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("formattedAddress")
    @Expose
    private List<String> formattedAddress = null;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
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

    public List<LabeledLatLng> getLabeledLatLngs() {
        return labeledLatLngs;
    }

    public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 &&
                Double.compare(location.lng, lng) == 0 &&
                distance == location.distance &&
                Objects.equals(address, location.address) &&
                Objects.equals(crossStreet, location.crossStreet) &&
                Objects.equals(labeledLatLngs, location.labeledLatLngs) &&
                Objects.equals(postalCode, location.postalCode) &&
                Objects.equals(cc, location.cc) &&
                Objects.equals(city, location.city) &&
                Objects.equals(state, location.state) &&
                Objects.equals(country, location.country) &&
                Objects.equals(formattedAddress, location.formattedAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, crossStreet, lat, lng, labeledLatLngs, distance, postalCode, cc, city, state, country, formattedAddress);
    }
}
