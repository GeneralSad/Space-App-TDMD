package com.leonv.spaceapp.utils;

import android.Manifest;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.leonv.spaceapp.utils.GeofenceBroadcastReceiver;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class GeofenceManager {

    private final Application application;
    private final List<Geofence> geofenceList;
    private GeofencingClient geofencingClient;
    private PendingIntent geofencePendingIntent;

    public GeofenceManager(Application application) {
        this.application = application;
        this.geofenceList = new ArrayList<>();
        this.geofencingClient = LocationServices.getGeofencingClient(application);
    }

    public Geofence addGeofence(String launchpadId, GeoPoint geofenceCenterPoint, float geofenceRadius) {
        Geofence geofence = new Geofence.Builder()
                .setRequestId(launchpadId)

                .setCircularRegion(
                        geofenceCenterPoint.getLatitude(),
                        geofenceCenterPoint.getLongitude(),
                        geofenceRadius
                )
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();

        this.geofenceList.add(geofence);
        return geofence;
    }

    public GeofencingRequest makeRequest() {
        return makeRequest(GeofencingRequest.INITIAL_TRIGGER_ENTER);
    }

    public GeofencingRequest makeRequest(@GeofencingRequest.InitialTrigger int initialTrigger) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(initialTrigger);
        builder.addGeofences(this.geofenceList);
        return builder.build();
    }

    public void enableGeofences(){
        if (ActivityCompat.checkSelfPermission(this.application, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            geofencingClient.addGeofences(this.makeRequest(), getGeofencePendingIntent())
                    .addOnSuccessListener(e -> Log.d("GeoFence", "Successfully added geofence"))
                    .addOnFailureListener(e -> Log.e("GeoFence", "Failed to add geofence", e));
        }
    }

    public void disableGeofences(){
        if (ActivityCompat.checkSelfPermission(this.application, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            geofencingClient.removeGeofences(this.getGeofencePendingIntent())
                    .addOnSuccessListener(e -> Log.d("GeoFence", "Successfully removed geofence"))
                    .addOnFailureListener(e -> Log.e("GeoFence", "Failed to remove geofence", e));
        }
    }

    private PendingIntent getGeofencePendingIntent() {
        if (geofencePendingIntent != null) {
            return geofencePendingIntent;
        }
        Intent intent = new Intent(this.application, GeofenceBroadcastReceiver.class);
        geofencePendingIntent = PendingIntent.getBroadcast(this.application, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }

    public void clearAll() {
        this.geofenceList.clear();
    }

    public List<Geofence> getGeofence() {
        return this.geofenceList;
    }
}
