package com.leonv.spaceapp.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.R;

import java.util.ArrayList;
import java.util.List;

public class RocketsRecyclerViewAdapter extends RecyclerView.Adapter<RocketsRecyclerViewAdapter.RocketsViewHolder> {

    private static final String LOGTAG = RocketsRecyclerViewAdapter.class.getName();

    private final List<Rocket> rockets;
    private OnItemClickListener onItemClickListener;

    public RocketsRecyclerViewAdapter(ArrayList<Rocket> items, OnItemClickListener onItemClickListener) {
        this.rockets = items;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RocketsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_fragment_item, parent, false);
        return new RocketsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RocketsViewHolder holder, int position) {
        holder.rocketName.setText(rockets.get(position).getName());
        holder.rocket = rockets.get(position);
    }

    @Override
    public int getItemCount() {
        return rockets.size();
    }

    public class RocketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rocketName;
        public Rocket rocket;

        public RocketsViewHolder(View view) {
            super(view);
            rocketName = view.findViewById(R.id.rocketsItemText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getBindingAdapterPosition());
        }
    }

}
