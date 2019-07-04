package com.example.nazariy.places.presentation.main.view.delegate;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.base.BaseRecyclerDelegate;
import com.example.nazariy.places.presentation.main.view.MainActivity;
import com.example.nazariy.places.presentation.utils.CustomDividerItemDecoration;
import com.example.nazariy.places.presentation.utils.SpaceItemDecoration;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BackdropRecyclerDelegate extends BaseRecyclerDelegate<String, MainActivity> {
    public BackdropRecyclerDelegate(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public void obtainResults(List<String> categories) {
        if (categories != null) {
            adapter.submitList(categories);
        }
    }

    @Override
    public void setupRecycler(ListAdapter<String, ?> adapter) {
        super.setupRecycler(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(getLayoutManager(recyclerView, RecyclerView.VERTICAL));

        float density = recyclerView.getContext().getResources().getDisplayMetrics().density;
        recyclerView.addItemDecoration(new SpaceItemDecoration((int) (density * ITEM_PADDING)));
        recyclerView.addItemDecoration(new CustomDividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL, recyclerView.getResources().getDrawable(R
                .drawable.item_divider), density));
    }
}
