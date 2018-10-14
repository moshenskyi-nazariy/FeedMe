package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class Timeframe {

    private String days;
    private boolean includesToday;
    private List<Open> open;
    private List<Object> segments;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public boolean isIncludesToday() {
        return includesToday;
    }

    public void setIncludesToday(boolean includesToday) {
        this.includesToday = includesToday;
    }

    public List<Open> getOpen() {
        return open;
    }

    public void setOpen(List<Open> open) {
        this.open = open;
    }

    public List<Object> getSegments() {
        return segments;
    }

    public void setSegments(List<Object> segments) {
        this.segments = segments;
    }

}
