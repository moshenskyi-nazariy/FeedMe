package com.example.nazariy.places.presentation.base;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerDelegate<Result, View> {
    protected static final int ITEM_PADDING = 10;

    protected View view;

    protected ListAdapter<Result, ?> adapter;

    protected RecyclerView recyclerView;

    protected LinearLayoutManager layoutManager;

    protected BaseRecyclerDelegate(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public abstract void obtainResults(List<Result> results);

    public void bind(View view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }

    public void setupRecycler(ListAdapter<Result, ?> adapter) {
        this.adapter = adapter;
    }

    public void swapLists(List<Result> newList) {
        adapter.submitList(null);
        adapter.submitList(newList);
    }

    @NonNull
    protected LinearLayoutManager getLayoutManager(RecyclerView recyclerView, @RecyclerView
            .Orientation int orientation) {
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(
                    recyclerView.getContext(),
                    orientation,
                    false);
        }
        return layoutManager;
    }

}
