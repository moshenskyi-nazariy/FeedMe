
package com.example.nazariy.places.domain.entities.place_result;

import com.example.nazariy.places.domain.entities.StatusCode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResult {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions;
    private List<Result> results;

    @StatusCode
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @StatusCode
    public String getStatus() {
        return status;
    }

    public void setStatus(@StatusCode String status) {
        this.status = status;
    }

}
