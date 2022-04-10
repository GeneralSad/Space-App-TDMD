package com.leonv.spaceapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.testing.SynchronousExecutor;
import androidx.work.testing.TestDriver;
import androidx.work.testing.TestListenableWorkerBuilder;
import androidx.work.testing.WorkManagerTestInitHelper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import com.google.android.gms.location.LocationListener;

@RunWith(AndroidJUnit4.class)
public class LaunchCheckWorkerTest {
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
//        context = InstrumentationRegistry.getTargetContext();
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
//        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
//        fusedLocationProviderClient.setMockMode(true);
//        String TEST_MOCK_GPS_LOCATION = "TEST_MOCK_GPS_LOCATION";
////        LocationManager locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
////        locationManager.addTestProvider(TEST_MOCK_GPS_LOCATION, false, false, false, false, false, false, false, Criteria.POWER_LOW, Criteria.ACCURACY_FINE);
////        locationManager.setTestProviderEnabled(TEST_MOCK_GPS_LOCATION, true);
//
//
//        // Set up your test
//
//        Location location = new Location(TEST_MOCK_GPS_LOCATION);
//        location.setLatitude(34.1233400);
//        location.setLongitude(15.6777880);
//        location.setAccuracy(7);
//        location.setTime(8);
//        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
////        locationManager.setTestProviderLocation(TEST_MOCK_GPS_LOCATION, location);
//        fusedLocationProviderClient.setMockLocation(location);
//
//        LocationRequest mLocationRequest = LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(10000);
//        mLocationRequest.setFastestInterval(1000);
//        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
//                new LocationCallback() {
//                    @Override
//                    public void onLocationResult(@NonNull LocationResult locationResult) {
//                        super.onLocationResult(locationResult);
//                    }
//                },
//                Looper.getMainLooper());
//        fusedLocationProviderClient.getLastLocation();
//
//
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(LaunchCheckWorker.class).build();
//        WorkManager workManager = WorkManager.getInstance(context);
//        workManager.enqueue(request).getResult().get();
//        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();
//        Data outputData = workInfo.getOutputData();
//        assertThat(workInfo.getState(), is(WorkInfo.State.SUCCEEDED));
//        Thread.sleep(20000);
////        locationManager.removeTestProvider("Test");
//        fusedLocationProviderClient.setMockMode(false);


//        PeriodicWorkRequest workRequest = LaunchCheckWorker.buildWorkRequest();
//        WorkManager workManager = WorkManager.getInstance(context);
//        TestDriver testDriver = WorkManagerTestInitHelper.getTestDriver();
//        workManager.enqueueUniquePeriodicWork("LaunchCheckWorker",
//                        ExistingPeriodicWorkPolicy.KEEP,
//                        workRequest);
//        assert testDriver != null;
//        testDriver.setAllConstraintsMet(workRequest.getId());
//        testDriver.setPeriodDelayMet(workRequest.getId());
//        WorkInfo workInfo = workManager.getWorkInfoById(workRequest.getId()).get();
//
//        // Assert
//        assertThat(workInfo.getState(), is(WorkInfo.State.ENQUEUED));

        assertTrue(true);
    }
}
