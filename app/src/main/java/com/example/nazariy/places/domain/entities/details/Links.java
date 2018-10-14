package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class Links {

    private int count;
    private List<PageItem> items = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
    }

}
