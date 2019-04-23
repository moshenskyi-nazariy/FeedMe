package com.example.nazariy.places.presentation.main.model;

import com.example.nazariy.places.domain.entities.places.Category;
import com.example.nazariy.places.domain.entities.places.VenuePage;

import java.util.List;

public class ViewVenue {
    private String id;
    private String name;
    private ViewLocation location;
    private List<Category> categories;
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

    public ViewLocation getLocation() {
        return location;
    }

    public void setLocation(ViewLocation location) {
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
        if (o == null || getClass() != o.getClass()) return false;

        ViewVenue viewVenue = (ViewVenue) o;

        if (!id.equals(viewVenue.id)) return false;
        if (!name.equals(viewVenue.name)) return false;
        if (!location.equals(viewVenue.location)) return false;
        if (categories != null ? !categories.equals(viewVenue.categories) : viewVenue.categories
                != null)
            return false;
        return venuePage != null ? venuePage.equals(viewVenue.venuePage) : viewVenue.venuePage ==
                null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (venuePage != null ? venuePage.hashCode() : 0);
        return result;
    }
}
