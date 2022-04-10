package com.leonv.spaceapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.testing.SynchronousExecutor;
import androidx.work.testing.WorkManagerTestInitHelper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.leonv.spaceapp.Workers.LaunchCheckWorker;

@RunWith(AndroidJUnit4.class)
public class LaunchCheckWorkerTest {
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        Configuration config = new Configuration.Builder()
                .setMinimumLoggingLevel(Log.DEBUG)
                .setExecutor(new SynchronousExecutor())
                .build();

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(
                context, config);
    }

    @Test
    public void testSleepWorker() throws Exception  {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        fusedLocationProviderClient.setMockMode(true);
        String TEST_MOCK_GPS_LOCATION = "TEST_MOCK_GPS_LOCATION";

        // Set up your test
        Location location = new Location(TEST_MOCK_GPS_LOCATION);
        location.setLatitude(34.1233400);
        location.setLongitude(15.6777880);
        location.setAccuracy(7);
        location.setTime(8);
        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        fusedLocationProviderClient.setMockLocation(location);

        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(1000);
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                    }
                },
                Looper.getMainLooper());
        fusedLocationProviderClient.getLastLocation();


        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(LaunchCheckWorker.class).build();
        WorkManager workManager = WorkManager.getInstance(context);
        workManager.enqueue(request).getResult().get();
        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
        Data outputData = workInfo.getOutputData();
        assertThat(workInfo.getState(), is(WorkInfo.State.SUCCEEDED));
        Thread.sleep(20000);
    }
}
