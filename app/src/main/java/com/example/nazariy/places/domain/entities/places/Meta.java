package com.example.nazariy.places.domain.entities.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Meta {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("requestId")
    @Expose
    private String requestId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;
        Meta meta = (Meta) o;
        return code == meta.code &&
                Objects.equals(requestId, meta.requestId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, requestId);
    }
}
