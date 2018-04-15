package com.example.nazariy.places.domain.entities;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef
@Retention(RetentionPolicy.SOURCE)
public @interface SortType {
    String BY_POPULARITY = "prominence";
    String NEAREST ="distance";
}
