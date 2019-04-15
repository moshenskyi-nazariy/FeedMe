package com.example.nazariy.places.domain.entities.places;

import com.example.nazariy.places.domain.entities.base.BasePlaceResult;

import java.util.Objects;

public class PlaceResult extends BasePlaceResult {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaceResult)) return false;
        PlaceResult that = (PlaceResult) o;
        return Objects.equals(meta, that.meta) &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {

        return Objects.hash(meta, response);
    }
}
