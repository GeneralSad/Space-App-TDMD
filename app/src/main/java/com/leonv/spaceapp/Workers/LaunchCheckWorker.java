package com.leonv.spaceapp.Workers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.leonv.spaceapp.Utils.FlightChecker;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.R;
import com.leonv.spaceapp.Views.FlightInfoFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LaunchCheckWorker extends Worker {
    private final int ALERT_DISTANCE = 1000 * 50;

    private Context context;

    public LaunchCheckWorker(@NonNull Context context,
                             @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork(){
        FlightChecker.getUpcomingFlights(context,
                ALERT_DISTANCE,
                60 * 60 * 24,
                flights -> flights.forEach(
                        flight -> flightNotification(this.getApplicationContext(), flight)
                ),
                e -> Log.e("LaunchCheckWorker", e.getLocalizedMessage()));

        return Result.success(getInputData());
    }

    public static PeriodicWorkRequest buildWorkRequest(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        return new PeriodicWorkRequest.Builder(LaunchCheckWorker.class,
                15,
                TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();
    }

    private void flightNotification(Context context, Flight flight)
    {
        Date currentTime = Calendar.getInstance().getTime();
        int id = (int)Math.round(currentTime.getTime() / 1000.0);
        String title = "There will be a launch soon";
        String description = String.format("Flight %s will launch at %s", flight.getFlightId(), flight.getLaunchDateString());
        PendingIntent pendingIntent = buildNotificationIntent(context, flight);
        NotificationCompat.Builder notificationBuilder = buildNotification(context,
                pendingIntent,
                title,
                description);
        showNotification(context, id, notificationBuilder);
    }

    private void showNotification(Context context, int id, NotificationCompat.Builder notificationBuilder){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, notificationBuilder.build());
    }

    private NotificationCompat.Builder buildNotification(Context context, PendingIntent intent, String title, String message){
        return new NotificationCompat.Builder(context, "spaceapp")
                .setSmallIcon(R.drawable.rocket)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(intent)
                .setAutoCancel(true);
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
