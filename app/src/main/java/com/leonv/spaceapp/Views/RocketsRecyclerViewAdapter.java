package com.leonv.spaceapp.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RocketsRecyclerViewAdapter extends RecyclerView.Adapter<RocketsRecyclerViewAdapter.RocketsViewHolder> {

    private static final String LOGTAG = RocketsRecyclerViewAdapter.class.getName();

    private List<Rocket> rockets;
    private OnItemClickListener onItemClickListener;

    public RocketsRecyclerViewAdapter(ArrayList<Rocket> items, OnItemClickListener onItemClickListener) {
        this.rockets = items;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RocketsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rockets_fragment_item, parent, false);
        return new RocketsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RocketsViewHolder holder, int position) {
        holder.rocketName.setText(rockets.get(position).getName());
        holder.successRate.setText(String.format("Success rate: %s%%", rockets.get(position).getSuccessRate()));
        holder.launchCost.setText(String.format("Cost: %s Million USD", rockets.get(position).getLaunchCostDollar() / 1000000.0));
        holder.rocket = rockets.get(position);
        Picasso.get().load(rockets.get(position).getImage()).into(holder.image);
    }

    public void updateRockets(List<Rocket> rockets) {
        this.rockets = rockets;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return rockets.size();
    }

    public class RocketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rocketName;
        public TextView successRate;
        public TextView launchCost;
        public Rocket rocket;
        public ImageView image;

        public RocketsViewHolder(View view) {
            super(view);
            rocketName = view.findViewById(R.id.rocketsItemText);
            successRate = view.findViewById(R.id.rocketsItemSuccessRate);
            launchCost = view.findViewById(R.id.rocketsItemLaunchCost);
            image = view.findViewById(R.id.rocketsItemImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getBindingAdapterPosition());
        }
    }

}
