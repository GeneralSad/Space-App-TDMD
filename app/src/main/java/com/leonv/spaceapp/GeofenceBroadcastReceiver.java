package com.leonv.spaceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
                    NotificationCompat.Builder builder = buildNotification(context, title , description);
                    showNotification(context, id, builder);
                    spaceXApiManager.removeListener(this);
                }
            });
            spaceXApiManager.getLaunchPadData(requestId);
        }
    }

    private NotificationCompat.Builder buildNotification(Context context, String title, String message){
        return new NotificationCompat.Builder(context, "spaceapp")
                .setSmallIcon(R.drawable.rocket)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
    }

    private void showNotification(Context context, int id, NotificationCompat.Builder notificationBuilder){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, notificationBuilder.build());
    }
}
