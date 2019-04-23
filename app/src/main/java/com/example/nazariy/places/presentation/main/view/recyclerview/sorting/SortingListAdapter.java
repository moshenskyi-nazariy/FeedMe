package com.example.nazariy.places.presentation.main.view.recyclerview.sorting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.example.nazariy.places.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SortingListAdapter extends ListAdapter<String, SortingListAdapter.SortingViewHolder>
        implements SortingTypeListener {
    private static String checkedSortingType;

    public SortingListAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public SortingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sorting_type_item, parent, false);
        return new SortingViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull SortingViewHolder holder, int position) {
        holder.sortingTypeCheckbox.setText(getItem(position));
    }

    @Override
    public String getCheckedSortingType() {
        return checkedSortingType == null ? getItem(0) : checkedSortingType;
    }

    static class SortingViewHolder extends RecyclerView.ViewHolder {
        private CheckedTextView sortingTypeCheckbox;

        SortingViewHolder(@NonNull View itemView) {
            super(itemView);
            sortingTypeCheckbox = itemView.findViewById(R.id.sorting_type_checkbox);
            sortingTypeCheckbox.setOnClickListener(currentView -> {
                CheckedTextView checkedTextView = (CheckedTextView) currentView;
                if (checkedTextView.isChecked()) {
                    checkedTextView.setCheckMarkDrawable(android.R.color.transparent);
                    checkedTextView.setChecked(false);
                } else {
                    checkedTextView.setCheckMarkDrawable(R.drawable.ic_done);
                    checkedTextView.setChecked(true);
                    checkedSortingType = checkedTextView.getText().toString();
                }
            });
        }
    }
}
