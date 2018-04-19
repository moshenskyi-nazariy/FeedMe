package com.example.nazariy.places.presentation.main.view.recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nazariy.places.R;
import com.example.nazariy.places.domain.entities.place_result.Result;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {
    private List<Result> results;

    public void update(List<Result> results) {
        if (results != null) {
            this.results = results;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.place_item, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        String placeName = results.get(position).getName();
        if (placeName != null) {
            holder.name.setText(placeName);
        }

        String placeAddress = results.get(position).getVicinity();
        if (placeAddress != null) {
            holder.address.setText(placeAddress);
        }

        Double rating = results.get(position).getRating();
        if (rating != null) {
            holder.rating.setRating((rating.floatValue()));
        }
    }

    @Override
    public int getItemCount() {
        return results != null? results.size() : 0;
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private RatingBar rating;

        PlaceViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.main__list_name);
            address = itemView.findViewById(R.id.main__list_address);
            rating = itemView.findViewById(R.id.main__list_rating);
        }
    }
}
