package com.example.nazariy.places.presentation.main.view;


import com.example.nazariy.places.presentation.base.LoadingMvpView;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.List;

public interface PlacesListMvpView extends LoadingMvpView {

    void obtainResults(List<ViewVenue> placeResult);

    void showMessage(String message);

}
