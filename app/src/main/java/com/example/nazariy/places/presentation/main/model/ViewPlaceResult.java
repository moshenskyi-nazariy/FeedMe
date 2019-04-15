package com.example.nazariy.places.presentation.main.model;

import com.example.nazariy.places.domain.entities.base.ResultMeta;
import com.example.nazariy.places.domain.entities.places.Response;

import java.util.Objects;

public class ViewPlaceResult {
    private ResultMeta meta;
    private Response response;

    public ResultMeta getMeta() {
        return meta;
    }

    public void setMeta(ResultMeta meta) {
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
