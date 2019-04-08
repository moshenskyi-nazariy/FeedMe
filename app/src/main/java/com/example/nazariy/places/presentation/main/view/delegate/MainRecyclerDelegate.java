package com.example.nazariy.places.presentation.main.view.delegate;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.base.BaseRecyclerDelegate;
import com.example.nazariy.places.presentation.main.model.ViewVenue;
import com.example.nazariy.places.presentation.main.view.MainActivity;
import com.example.nazariy.places.presentation.main.view.recyclerview.PlacesAdapter;
import com.example.nazariy.places.presentation.main.view.recyclerview.SpaceItemDecoration;

import java.util.List;

public class MainRecyclerDelegate extends
        BaseRecyclerDelegate<List<ViewVenue>, MainActivity> {
    @Override
    public void obtainResults(List<ViewVenue> results) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_left_slide);

        recyclerView.setLayoutAnimation(controller);
        adapter.update(results);
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void setupRecycler(RecyclerView recyclerView) {
        super.setupRecycler(recyclerView);
        adapter = new PlacesAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView));
        recyclerView.addItemDecoration(new SpaceItemDecoration(8));
    }

    @NonNull
    private LinearLayoutManager getLayoutManager(RecyclerView recyclerView) {
        return new LinearLayoutManager(
                recyclerView.getContext(),
                RecyclerView.VERTICAL,
                false);
    }
}
