package com.leonv.spaceapp.Views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Utils.OnItemClickListener;
import com.leonv.spaceapp.R;
import com.squareup.picasso.Picasso;

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
        holder.flightTime.setText(flights.get(position).getLaunchDateString());
        holder.flight = flights.get(position);

        String missionPatch = flights.get(position).getMissionPatch();
        if (!missionPatch.isEmpty()) {
            Picasso.get().load(flights.get(position).getMissionPatch()).into(holder.logo);
        } else {
            holder.logo.setImageResource(R.drawable.rocket);
        }
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ConstraintLayout itemLayout;
        public TextView flightName;
        public TextView flightTime;
        public ImageView logo;
        public Flight flight;

        public ViewHolder(View view) {
            super(view);
            flightName = view.findViewById(R.id.launchpadItemText);
            flightTime = view.findViewById(R.id.launchpadItemTime);
            logo = view.findViewById(R.id.launchpadItemImage);
            itemLayout = view.findViewById(R.id.launchpadItemLayout);
            itemView.setOnClickListener(this);
            logo.getLayoutParams().height = 80;
            logo.getLayoutParams().width = 80;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getBindingAdapterPosition());
            Intent intent = new Intent(view.getContext(), FlightInfoFragment.class);
                intent.putExtra("name", flight.getName());
                intent.putExtra("reusedFairings", flight.hasReusedFairings());
                intent.putExtra("webcast", flight.getWebcastLink());
                intent.putExtra("article", flight.getArticleLink());
                intent.putExtra("wikipedia", flight.getWikipediaLink());
                intent.putExtra("staticDate", flight.getStaticFireDate());
                intent.putExtra("details", flight.getLaunchDetails());
                intent.putExtra("flightNumber", flight.getFlightNumber());
                intent.putExtra("launchDate", flight.getLaunchDateString());
                intent.putExtra("missionPatch", flight.getMissionPatch());
            view.getContext().startActivity(intent);
            Log.i(LOGTAG, "Clicked on item");
        }
    }

}
