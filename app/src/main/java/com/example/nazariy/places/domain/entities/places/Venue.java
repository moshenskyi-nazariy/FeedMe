package com.example.nazariy.places.domain.entities.places;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Venue {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private Location location;
    @SerializedName("categories")
    private List<Category> categories;
    @SerializedName("venuePage")
    private VenuePage venuePage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public VenuePage getVenuePage() {
        return venuePage;
    }

    public void setVenuePage(VenuePage venuePage) {
        this.venuePage = venuePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        Venue venue = (Venue) o;
        return Objects.equals(id, venue.id) &&
                Objects.equals(name, venue.name) &&
                Objects.equals(location, venue.location) &&
                Objects.equals(categories, venue.categories) &&
                Objects.equals(venuePage, venue.venuePage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, location, categories, venuePage);
    }
}
