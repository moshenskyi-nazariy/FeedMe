package com.example.nazariy.places.domain.entities.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PlaceResult {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
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
