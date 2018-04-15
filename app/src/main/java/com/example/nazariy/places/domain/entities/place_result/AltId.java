
package com.example.nazariy.places.domain.entities.place_result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AltId {

    @SerializedName("place_id")
    @Expose
    private String placeId;
    private String scope;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
