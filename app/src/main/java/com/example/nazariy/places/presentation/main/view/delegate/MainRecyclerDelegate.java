package com.example.nazariy.places.presentation.main.view.delegate;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.base.BaseRecyclerDelegate;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.main.view.MainActivity;
import com.example.nazariy.places.presentation.utils.CustomDividerItemDecoration;
import com.example.nazariy.places.presentation.utils.SpaceItemDecoration;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MainRecyclerDelegate extends BaseRecyclerDelegate<ViewVenue, MainActivity> {
    private final LayoutAnimationController animationController;

    public MainRecyclerDelegate(RecyclerView recyclerView) {
        super(recyclerView);
        Context context = recyclerView.getContext();
        animationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade_in);
    }

    @Override
    public void obtainResults(List<ViewVenue> results) {
        if (results != null) {
            recyclerView.setLayoutAnimation(animationController);
            adapter.submitList(results);
            recyclerView.scheduleLayoutAnimation();
        }
    }

    @Override
    public void setupRecycler(ListAdapter<ViewVenue, ?> adapter) {
        super.setupRecycler(adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView));

        float density = recyclerView.getContext().getResources().getDisplayMetrics().density;
        recyclerView.addItemDecoration(new SpaceItemDecoration((int) (density * ITEM_PADDING)));
        recyclerView.addItemDecoration(new CustomDividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL, recyclerView.getResources().getDrawable(R.drawable.item_divider), density));
    }

    @Override
    public void swapLists(List<ViewVenue> newList) {
        recyclerView.setLayoutAnimation(animationController);

        adapter.submitList(null);
        adapter.submitList(newList);

        recyclerView.scheduleLayoutAnimation();
        recyclerView.smoothScrollToPosition(0);
    }

    @NonNull
    private LinearLayoutManager getLayoutManager(RecyclerView recyclerView) {
        return new LinearLayoutManager(
                recyclerView.getContext(),
                RecyclerView.VERTICAL,
                false);
    }
}
