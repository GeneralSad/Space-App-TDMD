package com.leonv.spaceapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Launchpad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightChecker {

    public static void getUpcomingFlights(Context context,
                                                  int distanceInMeters,
                                                  int timeInSeconds,
                                                  @NonNull OnSuccessListener<ArrayList<Flight>> onSuccessListener,
                                                  @NonNull OnFailureListener onFailureListener){
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        SpaceXApiManager apiManager = ((SpaceApp) context).getApiManager();
        HashMap<String, Launchpad> launchpadHashMap = new HashMap<>();
        apiManager.addListener(new SpaceXApiListener() {
            @Override
            public void onFlightsAvailable(ArrayList<Flight> flights) {
                if(!checkPermission(context))
                    return;

                try {
                    fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(@NonNull Location location) {
                            Stream<Flight> flightsStream = flights.stream().sorted(Comparator.comparing(Flight::getLaunchDateUtc))
                                    .filter(x -> isWithinTimeFrame(x.getLaunchDateUtc(), getTime(), timeInSeconds));

                            if (location == null) {
                                onFailureListener.onFailure(new Exception("No location available"));
                                return;
                            }

                            flightsStream = flightsStream.filter(x -> {
                                Launchpad launchpad = launchpadHashMap.get(x.getLaunchpadId());
                                if(launchpad == null)
                                    return false;
                                Location location1 = createLocation(launchpad.getLongitude(), launchpad.getLatitude());
                                return location.distanceTo(location1) < distanceInMeters;
                            });
                            ArrayList<Flight> flightsResult = flightsStream.collect(Collectors.toCollection(ArrayList::new));
                            onSuccessListener.onSuccess(flightsResult);
                        }
                    }).addOnFailureListener(x ->{
                        x.printStackTrace();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    onFailureListener.onFailure(e);
                }
            }

            @Override
            public void onLaunchpadsAvailable(List<Launchpad> launchpads) {
                launchpads.forEach(x -> launchpadHashMap.put(x.getId(), x));
                apiManager.getFlightsData("upcoming");
            }
        });

        apiManager.getLaunchPadsData();
    }

    public static void getUpcomingFlights(Context context,
                                          String launchpadId,
                                          int timeInSeconds,
                                          @NonNull OnSuccessListener<ArrayList<Flight>> onSuccessListener,
                                          @NonNull OnFailureListener onFailureListener){
        SpaceXApiManager apiManager = ((SpaceApp) context).getApiManager();
        apiManager.addListener(new SpaceXApiListener() {
            @Override
            public void onFlightsAvailable(ArrayList<Flight> flights) {
                if(!checkPermission(context))
                    return;

                try {
                    Stream<Flight> flightsStream = flights.stream()
                            .filter(x -> x.getLaunchpadId().equals(launchpadId))
                            .sorted(Comparator.comparing(Flight::getLaunchDateUtc))
                            .filter(x -> isWithinTimeFrame(x.getLaunchDateUtc(), getTime(), timeInSeconds));
                    ArrayList<Flight> flightsResult = flightsStream.collect(Collectors.toCollection(ArrayList::new));
                    onSuccessListener.onSuccess(flightsResult);

                } catch (Exception e) {
                    e.printStackTrace();
                    onFailureListener.onFailure(e);
                }
            }
        });

        apiManager.getLaunchPadsData();
    }

    private static boolean isWithinTimeFrame(Date date1, Date date2, long seconds)
    {
        long date1Time = date1.getTime();
        long date2Time = date2.getTime();
        boolean result = Math.abs(date1Time - date2Time) < seconds * 1000;
        return result;
    }

    private static Date getTime(){
        return Calendar.getInstance().getTime();
//        Date result = new Date(2022 - 1900, 4-1, 21, 11, 0, 0);
//        return result;
    }

    private static Location createLocation(double longitude, double latitude){
        Location location = new Location("Point a");
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return location;
    }

    private static boolean checkPermission(Context context)
    {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

}
