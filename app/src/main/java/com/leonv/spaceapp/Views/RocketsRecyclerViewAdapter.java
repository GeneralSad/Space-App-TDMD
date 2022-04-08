package com.leonv.spaceapp.Views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RocketsRecyclerViewAdapter extends RecyclerView.Adapter<RocketsRecyclerViewAdapter.RocketsViewHolder> {

    private static final String LOGTAG = RocketsRecyclerViewAdapter.class.getName();

    private Context context;
    private List<Rocket> rockets;
    private OnItemClickListener onItemClickListener;

    public RocketsRecyclerViewAdapter(Context context, ArrayList<Rocket> items, OnItemClickListener onItemClickListener) {
        this.context = context;
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
        Rocket rocket = rockets.get(position);

        holder.rocketName.setText(rocket.getName());
        holder.successRate.setText(String.format("Success rate: %s%%", rocket.getSuccessRate()));
        holder.launchCost.setText(String.format("Cost: %s Million USD", rocket.getLaunchCostDollar() / 1000000.0));
        holder.rocket = rockets.get(position);

        holder.rocketItemLayout.setOnClickListener(itemView -> {
            Intent intent = new Intent(context, RocketInfoPopup.class);
            intent.putExtra("name", rocket.getName());
            intent.putExtra("height", rocket.getHeight());
            intent.putExtra("diameter", rocket.getDiameter());
            intent.putExtra("mass", rocket.getMass());
            intent.putExtra("FSReusable", rocket.isFS_Reusable());
            intent.putExtra("FSEngines", rocket.getFS_Engines());
            intent.putExtra("FSFuelInTons", rocket.getFS_FuelInTons());
            intent.putExtra("SSEngines", rocket.getSS_Engines());
            intent.putExtra("SSFuelInTons", rocket.getSS_FuelInTons());
            intent.putExtra("enginesType", rocket.getEngines_Type());
            intent.putExtra("enginesLossMax", rocket.getEngines_EngineLossMax());
            intent.putExtra("propellant1", rocket.getPropellant1());
            intent.putExtra("propellant2", rocket.getPropellant2());
            intent.putExtra("TWR", rocket.getTWR());
            intent.putExtra("active", rocket.isActive());
            intent.putExtra("stages", rocket.getStages());
            intent.putExtra("boosters", rocket.getBoosters());
            intent.putExtra("launchCostDollar", rocket.getLaunchCostDollar());
            intent.putExtra("successRate", rocket.getSuccessRate());
            intent.putExtra("company", rocket.getCompany());
            intent.putExtra("wikipedia", rocket.getWikipediaLink());
            intent.putExtra("description", rocket.getDescription());
            intent.putExtra("image", rocket.getImage());
            context.startActivity(intent);
            Log.i(LOGTAG, "Clicked on item");
        });

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
        public ConstraintLayout rocketItemLayout;

        public RocketsViewHolder(View view) {
            super(view);
            rocketName = view.findViewById(R.id.rocketsItemText);
            successRate = view.findViewById(R.id.rocketsItemSuccessRate);
            launchCost = view.findViewById(R.id.rocketsItemLaunchCost);
            image = view.findViewById(R.id.rocketsItemImage);
            rocketItemLayout = view.findViewById(R.id.rocketsItemLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getBindingAdapterPosition());

//            this.rocketItemLayout.setOnClickListener();

        }
    }

}
