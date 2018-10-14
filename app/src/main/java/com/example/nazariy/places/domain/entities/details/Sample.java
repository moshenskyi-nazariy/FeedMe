package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class Sample {

    private List<Entity> entities = null;
    private String text;

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
