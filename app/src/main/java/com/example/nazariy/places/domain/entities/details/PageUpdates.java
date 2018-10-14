package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class PageUpdates {

    private int count;
    private List<Object> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

}
