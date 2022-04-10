package com.leonv.spaceapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LaunchCheckWorker extends Worker {

    private FusedLocationProviderClient fusedLocationClient;

    public LaunchCheckWorker(@NonNull Context context,
                             @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        Date currentTime = Calendar.getInstance().getTime();
        int id = (int)Math.round(currentTime.getTime() / 1000.0);
        String title = "LaunchCheckWorker";
        String description = String.format("Launchpad %s is near", "LaunchCheckWorker");
        NotificationCompat.Builder builder = buildNotification(this.getApplicationContext(), title , description);
        showNotification(this.getApplicationContext(), id, builder);

        return Result.success();
    }

    public static PeriodicWorkRequest buildWorkRequest(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        return new PeriodicWorkRequest.Builder(LaunchCheckWorker.class,
                1,
                TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();
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
