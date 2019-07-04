package com.example.nazariy.places.presentation.main.view.recyclerview.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazariy.places.R;
import com.example.nazariy.places.domain.entities.places.Category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesListAdapter extends ListAdapter<String, CategoriesListAdapter
        .CategoryViewHolder> {

    private final ItemClickListener itemClickListener;

    public CategoriesListAdapter(@NonNull ItemClickListener itemClickListener, @NonNull
            DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,
                parent, false);
        return new CategoryViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.categoryName.setText(getItem(position));
    }

    public interface ItemClickListener {
        void categorySelected(String categoryName);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;


        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryName.setOnClickListener(view ->
                    itemClickListener.categorySelected(((TextView) view).getText().toString()));
        }
    }
}
