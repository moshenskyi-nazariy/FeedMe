package com.example.nazariy.places.presentation.main.view.recyclerview;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazariy.places.R;
import com.example.nazariy.places.domain.entities.Venue;
import com.example.nazariy.places.presentation.main.model.ViewResponse;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import java.util.ArrayList;
import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private final List<ViewVenue> results;

    public PlacesAdapter() {
        results = new ArrayList<>();
    }

    public void update(List<ViewVenue> results) {
        if (results != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                    new VenueListDiffCallback(this.results, results));
            diffResult.dispatchUpdatesTo(this);
            this.results.clear();
            this.results.addAll(results);
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
        ViewVenue venue = results.get(position);
        String placeName = venue.getName();
        if (placeName != null) {
            holder.name.setText(placeName);
        }

        SpannableString placeAddress = venue.getLocation().getAddress();
        if (placeAddress != null) {
            holder.address.setText(placeAddress);
        }

        int distanceInMeters = venue.getLocation().getDistance();
        Resources resources = holder.itemView.getResources();
        holder.distance.setText(resources.getString(R.string.distance_placeholder, distanceInMeters));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            for (String key : bundle.keySet()) {
                if (key.equals(VenueListDiffCallback.KEY_NAME)) {
                    holder.name.setText(bundle.getString(key));
                }
                if (key.equals(VenueListDiffCallback.KEY_DISTANCE)) {
                    holder.setDistance(bundle.getInt(key));
                }
                if (key.equals(VenueListDiffCallback.KEY_LOCATION)) {
                    holder.address.setText(bundle.getString(key));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView distance;

        PlaceViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.main__list_name);
            address = itemView.findViewById(R.id.main__list_address);
            distance = itemView.findViewById(R.id.main__place_distance);
        }

        void setDistance(int distance) {
            Resources resources = itemView.getResources();
            this.distance.setText(resources.getString(R.string.distance_placeholder, distance));
        }
    }
}
