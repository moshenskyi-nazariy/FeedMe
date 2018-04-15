package com.example.nazariy.places.domain.entities;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({StatusCode.OK, StatusCode.INVALID_REQUEST, StatusCode.NOT_FOUND, StatusCode.OVER_QUERY_LIMIT,
        StatusCode.REQUEST_DENIED, StatusCode.UNKNOWN_ERROR, StatusCode.ZERO_RESULTS})
public @interface StatusCode {
    String OK = "OK";
    String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    String ZERO_RESULTS = "ZERO_RESULTS";
    String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    String REQUEST_DENIED = "REQUEST_DENIED";
    String INVALID_REQUEST = "INVALID_REQUEST";
    String NOT_FOUND = "NOT_FOUND";
}
