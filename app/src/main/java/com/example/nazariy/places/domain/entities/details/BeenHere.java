package com.example.nazariy.places.domain.entities.details;

public class BeenHere {

    private int count;
    private int unconfirmedCount;
    private boolean marked;
    private int lastCheckinExpiredAt;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnconfirmedCount() {
        return unconfirmedCount;
    }

    public void setUnconfirmedCount(int unconfirmedCount) {
        this.unconfirmedCount = unconfirmedCount;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(int lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

}
