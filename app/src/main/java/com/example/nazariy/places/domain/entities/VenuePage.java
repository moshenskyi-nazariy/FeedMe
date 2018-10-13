
package com.example.nazariy.places.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class VenuePage {

    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenuePage)) return false;
        VenuePage venuePage = (VenuePage) o;
        return Objects.equals(id, venuePage.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
