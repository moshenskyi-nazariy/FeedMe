
package com.example.nazariy.places.domain.entities.details.photos;

import com.example.nazariy.places.domain.entities.base.BasePlaceResult;

public class PhotoResult extends BasePlaceResult {

    private PhotoResponse response;

    public PhotoResponse getResponse() {
        return response;
    }

    public void setResponse(PhotoResponse response) {
        this.response = response;
    }

}
