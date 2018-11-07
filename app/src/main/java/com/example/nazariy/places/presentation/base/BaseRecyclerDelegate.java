package com.example.nazariy.places.presentation.base;

import android.support.v7.widget.RecyclerView;

public abstract class BaseRecyclerDelegate<Result, View, Presenter> {

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

    public void setupRecycler(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
