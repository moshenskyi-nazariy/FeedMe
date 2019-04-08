package com.example.nazariy.places.presentation.base;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<V extends RecyclerView.ViewHolder, R> extends RecyclerView.Adapter<V> {
    public abstract void update(R results);
}
