package com.example.nazariy.places.presentation.main.view.recyclerview.sorting;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class SortingTypeDiffCallback extends DiffUtil.ItemCallback<String> {
    @Override
    public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
        return oldItem.equals(newItem);
    }
}
