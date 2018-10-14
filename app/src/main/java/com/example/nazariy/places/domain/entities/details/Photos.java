package com.example.nazariy.places.domain.entities.details;

import java.util.List;

public class Photos {

    private int count;
    private List<Group> groups = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
