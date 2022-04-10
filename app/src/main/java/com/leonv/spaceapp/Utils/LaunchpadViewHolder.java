package com.leonv.spaceapp.Utils;

import androidx.fragment.app.Fragment;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;
import com.leonv.spaceapp.Popup.PopupLaunchpad;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class LaunchpadViewHolder implements UpcomingViewModel.FlightsListener {

    private final Fragment fragment;
    private final MapView mapView;
    private Marker marker;
    private final Launchpad launchpad;
    private final ArrayList<Flight> flights;

    private GeoPoint geoPoint;
    private PopupLaunchpad popupLaunchpad;

    public LaunchpadViewHolder(Fragment fragment, MapView mapView, Launchpad launchpad, UpcomingViewModel upcomingViewModel) {
        this.fragment = fragment;
        this.mapView = mapView;
        this.launchpad = launchpad;
        this.flights = new ArrayList<>();
        upcomingViewModel.addFlightsListener(this);
    }

    public Marker create(){
        GeoPoint geoPoint = this.getGeoPoint();
        this.marker = new Marker(this.mapView);
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        //Hierdoor zijn is er geen auto navigatie naar de marker en geen pop up, onclick
        marker.setOnMarkerClickListener(this::markerOnClick);

        return marker;
    }

    private boolean markerOnClick(Marker marker, MapView mapView) {
        this.getPopupLaunchpad().show(this.fragment.getLayoutInflater());
        return true;
    }

    public GeoPoint getGeoPoint(){
        if(geoPoint == null) {
            this.geoPoint = new GeoPoint(this.launchpad.getLatitude(), this.launchpad.getLongitude());
        }
        return this.geoPoint;
    }

    private PopupLaunchpad getPopupLaunchpad(){
        if(this.popupLaunchpad == null){
            this.popupLaunchpad = new PopupLaunchpad(this.launchpad.getFullName(), this.flights);
        }
        return this.popupLaunchpad;
    }

    @Override
    public void onFlightsAvailable(List<Flight> flightList) {
        for(Flight flight : flightList) {
            if(flight.getLaunchpadId().equals(this.launchpad.getId()) && !this.flights.contains(flight)) {
                this.flights.add(flight);
            }
        }
    }

    public Launchpad getLaunchpad() {
        return launchpad;
    }
}
