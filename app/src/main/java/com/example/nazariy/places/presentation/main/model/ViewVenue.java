package com.example.nazariy.places.presentation.main.model;

import com.example.nazariy.places.domain.entities.places.Category;
import com.example.nazariy.places.domain.entities.places.VenuePage;

import java.util.List;

public class ViewVenue {
    private String id;
    private String name;
    private ViewLocation location;
    private List<Category> categories = null;
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
}
