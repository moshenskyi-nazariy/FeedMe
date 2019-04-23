package com.example.nazariy.places.presentation.base;

import java.util.List;

public interface ISorter<T> {
    List<T> sort(String sortingType, List<T> list);
}
