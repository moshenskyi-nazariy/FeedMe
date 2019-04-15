package com.example.nazariy.places.domain.entities.base;

public class ResultMeta {
    protected int code;
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
        if (o == null || getClass() != o.getClass()) return false;

        ResultMeta that = (ResultMeta) o;

        if (code != that.code) return false;
        return requestId != null ? requestId.equals(that.requestId) : that.requestId == null;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }
}
