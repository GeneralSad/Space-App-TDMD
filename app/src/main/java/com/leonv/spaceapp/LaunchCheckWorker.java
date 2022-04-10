package com.leonv.spaceapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

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
        return null;
    }

    public static WorkRequest buildWorkRequest(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        return new PeriodicWorkRequest.Builder(LaunchCheckWorker.class,
                5,
                TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();
    }
}
