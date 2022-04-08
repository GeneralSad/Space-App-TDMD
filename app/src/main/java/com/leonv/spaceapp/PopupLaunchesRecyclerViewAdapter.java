package com.leonv.spaceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leonv.spaceapp.Models.Flight;

import java.util.ArrayList;
import java.util.List;

public class PopupLaunchesRecyclerViewAdapter extends RecyclerView.Adapter<PopupLaunchesRecyclerViewAdapter.ViewHolder> {

    private static final String LOGTAG = PopupLaunchesRecyclerViewAdapter.class.getName();

    private final List<Flight> flights;
    private final OnItemClickListener onItemClickListener;

    public PopupLaunchesRecyclerViewAdapter(ArrayList<Flight> items, OnItemClickListener onItemClickListener) {
        this.flights = items;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_launchpad_launch_item, parent, false);
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
            flightName = view.findViewById(R.id.popup_launchpad_launch_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getBindingAdapterPosition());
        }
    }

}
