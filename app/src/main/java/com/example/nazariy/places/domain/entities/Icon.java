
package com.example.nazariy.places.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Icon {

    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("suffix")
    @Expose
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Icon)) return false;
        Icon icon = (Icon) o;
        return Objects.equals(prefix, icon.prefix) &&
                Objects.equals(suffix, icon.suffix);
    }

    @Override
    public int hashCode() {

        return Objects.hash(prefix, suffix);
    }
}
