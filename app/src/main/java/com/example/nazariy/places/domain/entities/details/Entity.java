package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class Entity {

    private List<Integer> indices;
    private String type;

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
