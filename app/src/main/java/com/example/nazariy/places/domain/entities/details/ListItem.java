package com.example.nazariy.places.domain.entities.details;

public class ListItem {

    private String id;
    private int createdAt;
    private ListItemPhoto photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public ListItemPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(ListItemPhoto photo) {
        this.photo = photo;
    }

}
