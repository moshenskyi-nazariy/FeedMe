package com.example.nazariy.places.presentation.main.view.recyclerview.categories;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazariy.places.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesListAdapter extends ListAdapter<String, CategoriesListAdapter
        .CategoryViewHolder> {

    protected CategoriesListAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

    }

    protected class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
        }
    }
}
