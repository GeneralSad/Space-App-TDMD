package com.leonv.spaceapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.leonv.spaceapp.API.SpaceXApiManager;

public class SpaceApp extends Application {

    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        this.getNotificationManager();
        this.enableBackgroundWorker();
    }

    public SpaceXApiManager getApiManager(){
        return SpaceXApiManager.getInstance(this);
    }

    public NotificationManager getNotificationManager(){
        if(this.notificationManager == null){
            this.notificationManager = this.createNotificationChannel();
        }
        return this.notificationManager;
    }

    public void enableBackgroundWorker(){
        PeriodicWorkRequest workRequest = LaunchCheckWorker.buildWorkRequest();
        WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork("LaunchCheckWorker",
                        ExistingPeriodicWorkPolicy.KEEP,
                        workRequest);
    }

    private NotificationManager createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Spaceapp";
            String description = "Spaceapp";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("spaceapp", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            return notificationManager;
        }
        return null;
    }
}
