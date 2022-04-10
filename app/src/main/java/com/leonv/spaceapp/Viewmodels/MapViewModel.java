package com.leonv.spaceapp.Viewmodels;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.utils.GeofenceManager;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.utils.SpaceApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapViewModel extends AndroidViewModel implements SpaceXApiListener {

    private static final String LOGTAG = MapViewModel.class.getName();

    private final HashMap<String, Launchpad> launchpads = new HashMap<>();
    private final ArrayList<LaunchpadListener> launchpadListeners = new ArrayList<>();
    private GeofenceManager geofenceManager;
    private SpaceXApiManager spaceXApiManager;

    public MapViewModel(@NonNull Application application) {
        super(application);
        spaceXApiManager = ((SpaceApp) application).getApiManager();
        spaceXApiManager.addListener(this);
    }

    public interface LaunchpadListener{
        void onLaunchpadAvailable(List<Launchpad> newLaunchpads);
    }

    public void addLaunchpadListener(LaunchpadListener launchpadListener)
    {
        if(this.launchpadListeners.contains(launchpadListener))
            return;

        this.launchpadListeners.add(launchpadListener);
    }

    public void removeLaunchpadListener(LaunchpadListener launchpadListener)
    {
        if(!this.launchpadListeners.contains(launchpadListener))
            return;

        this.launchpadListeners.remove(launchpadListener);
    }

    public Launchpad getLaunchPad(String id){
        return launchpads.get(id);
    }

    public HashMap<String, Launchpad> getLaunchPads(){
        return this.launchpads;
    }

    public GeofenceManager getGeofenceManager() {
        return geofenceManager;
    }

    public void setGeofenceManager(GeofenceManager geofenceManager) {
        this.geofenceManager = geofenceManager;
    }

    //Make a request to get the launchpads from the API
    public void requestLaunchpads() {
        spaceXApiManager.getLaunchPadsData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLaunchpadsAvailable(List<Launchpad> availableLaunchpads) {
        this.launchpads.clear();

        HashMap<String, Launchpad> availableLaunchpadsMap = availableLaunchpads
                .stream()
                .collect(Collectors.toMap(Launchpad::getId,
                        Function.identity(),
                        (a,b) -> a,
                        HashMap::new)
                );

        this.launchpadListeners.forEach(x -> x.onLaunchpadAvailable(availableLaunchpads));

        launchpads.putAll(availableLaunchpadsMap);
    }
}