package com.example.nazariy.places.presentation.main.view.recyclerview.venues;


import android.content.res.Resources;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazariy.places.R;
import com.example.nazariy.places.presentation.main.model.ViewVenue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class PlacesAdapter extends ListAdapter<ViewVenue, PlacesAdapter.PlaceViewHolder> {

    private final VenueListener itemListener;

    public PlacesAdapter(VenueListener itemListener, DiffUtil.ItemCallback<ViewVenue> diffCallback) {
        super(diffCallback);
        this.itemListener = itemListener;
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
        ViewVenue venue = getItem(position);
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
        holder.distance.setText(resources.getString(R.string.distance_placeholder,
                distanceInMeters));

        holder.itemView.setOnClickListener(v -> itemListener.onEstablishmentClick(venue.getId(),
                holder.name.getText().toString(), holder.itemView));
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
    }
}
