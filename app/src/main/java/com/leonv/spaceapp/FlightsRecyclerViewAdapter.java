package com.leonv.spaceapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leonv.spaceapp.Models.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<FlightsRecyclerViewAdapter.ViewHolder> {

    private static final String LOGTAG = FlightsRecyclerViewAdapter.class.getName();

    private final List<Flight> flights;
    private OnItemClickListener onItemClickListener;

    public FlightsRecyclerViewAdapter(ArrayList<Flight> items, OnItemClickListener onItemClickListener) {
        this.flights = items;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_fragment_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.flightName.setText(flights.get(position).getName());
        holder.flight = flights.get(position);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView flightName;
        public Flight flight;

        public ViewHolder(View view) {
            super(view);
            flightName = view.findViewById(R.id.upcomingItemText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getBindingAdapterPosition());
        }
    }

}
