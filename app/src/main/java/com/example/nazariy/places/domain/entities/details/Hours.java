package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class Hours {

    private String status;
    private boolean isOpen;
    private boolean isLocalHoliday;
    private List<Timeframe> timeframes = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isIsLocalHoliday() {
        return isLocalHoliday;
    }

    public void setIsLocalHoliday(boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }

    public List<Timeframe> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(List<Timeframe> timeframes) {
        this.timeframes = timeframes;
    }

}
