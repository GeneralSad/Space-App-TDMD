package com.leonv.spaceapp.Views;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.R;

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
        public Flight flight;

        public FlightsViewHolder(View view) {
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
