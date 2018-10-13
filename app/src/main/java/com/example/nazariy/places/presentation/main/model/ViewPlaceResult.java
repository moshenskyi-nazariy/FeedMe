package com.example.nazariy.places.presentation.main.model;

import com.example.nazariy.places.domain.entities.Meta;
import com.example.nazariy.places.domain.entities.Response;

import java.util.Objects;

public class ViewPlaceResult {
    private Meta meta;
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewPlaceResult)) return false;
        ViewPlaceResult that = (ViewPlaceResult) o;
        return Objects.equals(meta, that.meta) &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta, response);
    }
}
