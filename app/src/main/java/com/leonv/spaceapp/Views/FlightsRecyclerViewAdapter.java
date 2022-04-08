package com.leonv.spaceapp.Views;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<FlightsRecyclerViewAdapter.FlightsViewHolder> {

    private static final String LOGTAG = FlightsRecyclerViewAdapter.class.getName();

    private List<Flight> flights;
    private OnItemClickListener onItemClickListener;

    public FlightsRecyclerViewAdapter(ArrayList<Flight> items, OnItemClickListener onItemClickListener) {
        this.flights = items;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public FlightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_fragment_item, parent, false);
        return new FlightsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FlightsViewHolder holder, int position) {
        holder.flightName.setText(flights.get(position).getName());
        holder.flight = flights.get(position);
        holder.flightTime.setText(flights.get(position).getLaunchDate());
        holder.flightRocket.setText(flights.get(position).getRocketId());

        String missionPatch = flights.get(position).getMissionPatch();
        if (!missionPatch.isEmpty()) {
            Picasso.get().load(flights.get(position).getMissionPatch()).into(holder.missionPatch);
        } else {
            holder.missionPatch.setImageResource(R.drawable.rocket);
        }
    }

    public void updateFlights(List<Flight> flights) {
        this.flights = flights;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public class FlightsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView flightName;
        public TextView flightTime;
        public TextView flightRocket;
        public Flight flight;
        public ImageView missionPatch;

        public FlightsViewHolder(View view) {
            super(view);
            flightName = view.findViewById(R.id.upcomingItemText);
            missionPatch = view.findViewById(R.id.upcomingItemImage);
            flightTime = view.findViewById(R.id.upcomingItemTime);
            flightRocket = view.findViewById(R.id.upcomingItemRocket);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}
