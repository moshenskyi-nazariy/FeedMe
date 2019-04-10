package com.example.nazariy.places.presentation.base;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerDelegate<Result, View> {

    protected View view;

    protected BaseAdapter<?, Result> adapter;

    protected RecyclerView recyclerView;

    public abstract void obtainResults(Result results);

    public void bind(View view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void setupRecycler(RecyclerView recyclerView,  BaseAdapter<?, Result> adapter) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }
}
