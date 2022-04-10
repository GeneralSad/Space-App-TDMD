package com.leonv.spaceapp.Views;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    private Context context;
    private List<Flight> flights;
    private OnItemClickListener onItemClickListener;

    public FlightsRecyclerViewAdapter(Context context, ArrayList<Flight> items, OnItemClickListener onItemClickListener) {
        this.context = context;
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
        Flight flight = flights.get(position);

        holder.flightName.setText(flight.getName());
        holder.flight = flight;
        holder.flightTime.setText(flight.getLaunchDateString());
        holder.flightRocket.setText(flight.getRocketId());

        holder.itemLayout.setOnClickListener(itemView -> {
            Intent intent = new Intent(context, FlightInfoFragment.class);
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
            context.startActivity(intent);
            Log.i(LOGTAG, "Clicked on item");
        });

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
        public ConstraintLayout itemLayout;

        public FlightsViewHolder(View view) {
            super(view);
            flightName = view.findViewById(R.id.upcomingItemText);
            missionPatch = view.findViewById(R.id.upcomingItemImage);
            flightTime = view.findViewById(R.id.upcomingItemTime);
            flightRocket = view.findViewById(R.id.upcomingItemRocket);
            itemLayout = view.findViewById(R.id.upcomingItemLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}
