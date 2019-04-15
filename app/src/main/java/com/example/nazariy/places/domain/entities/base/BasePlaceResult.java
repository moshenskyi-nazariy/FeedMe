package com.example.nazariy.places.domain.entities.base;

public abstract class BasePlaceResult {
    protected ResultMeta meta;

    public ResultMeta getMeta() {
        return meta;
    }

    public void setMeta(ResultMeta meta) {
        this.meta = meta;
    }
}
