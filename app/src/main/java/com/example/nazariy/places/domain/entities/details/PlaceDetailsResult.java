package com.example.nazariy.places.domain.entities.details;

import com.example.nazariy.places.domain.entities.base.BasePlaceResult;
import com.example.nazariy.places.domain.entities.base.ResultMeta;

public class PlaceDetailsResult extends BasePlaceResult {

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

}
