package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class ListItems {

    private int count;
    private List<ListItem> items = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(List<ListItem> items) {
        this.items = items;
    }

}
