package com.leonv.spaceapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Views.FlightInfoFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e("GeoFence", errorMessage);
            return;
        }

        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {

            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            String requestId = triggeringGeofences.get(0).getRequestId();

            SpaceXApiManager spaceXApiManager = ((SpaceApp)context.getApplicationContext()).getApiManager();
            Date currentTime = Calendar.getInstance().getTime();
            spaceXApiManager.addListener(new SpaceXApiListener() {
                @Override
                public void onLaunchpadAvailable(Launchpad launchpad) {
                    int id = (int)Math.round(currentTime.getTime() / 1000.0);
                    String title = "There is a launchpad near";
                    String description = String.format("Launchpad %s is near", launchpad.getName());
                    NotificationCompat.Builder builder = buildNotification(context, null, title , description);
                    showNotification(context, id, builder);
                    spaceXApiManager.removeListener(this);
                }
            });
            spaceXApiManager.getLaunchPadData(requestId);

            FlightChecker.getUpcomingFlights(context.getApplicationContext(),
                    requestId,
                    60 * 60 * 24,
                    flights ->
                        flights.forEach(flight -> flightNotification(context.getApplicationContext(), flight)),
                    e -> Log.e("LaunchCheckWorker", e.getLocalizedMessage()));
        }
    }

    private void flightNotification(Context context, Flight flight)
    {
        Date currentTime = Calendar.getInstance().getTime();
        int id = (int)Math.round(currentTime.getTime() / 1000.0);
        String title = "There will be a launch soon";
        String description = String.format("Flight %s will launch at %s", flight.getFlightId(), flight.getLaunchDateString());
        PendingIntent pendingIntent = buildNotificationIntent(context, flight);
        NotificationCompat.Builder notificationBuilder = buildNotification(context, pendingIntent, title, description);
        showNotification(context, id, notificationBuilder);
    }

    private NotificationCompat.Builder buildNotification(Context context, PendingIntent pendingIntent, String title, String message){
        return new NotificationCompat.Builder(context, "spaceapp")
                .setSmallIcon(R.drawable.rocket)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    private void showNotification(Context context, int id, NotificationCompat.Builder notificationBuilder){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, notificationBuilder.build());
    }

    private PendingIntent buildNotificationIntent(Context context, Flight flight){
        Intent intent = new Intent(context, FlightInfoFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

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

        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }
}

