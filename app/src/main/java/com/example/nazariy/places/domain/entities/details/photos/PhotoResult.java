
package com.example.nazariy.places.domain.entities.details.photos;

import com.example.nazariy.places.domain.entities.details.Meta;

public class PhotoResult {

    private Meta meta;

    private PhotoResponse response;

    public PhotoResponse getResponse() {
        return response;
    }

    public void setResponse(PhotoResponse response) {
        this.response = response;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
