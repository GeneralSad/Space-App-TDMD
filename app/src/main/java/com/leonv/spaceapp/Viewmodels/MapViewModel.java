package com.leonv.spaceapp.Viewmodels;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.SpaceApp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapViewModel extends AndroidViewModel implements SpaceXApiListener {

    private static final String LOGTAG = MapViewModel.class.getName();

    private final HashMap<String, Launchpad> launchpads = new HashMap<>();
    private final ArrayList<LaunchpadListener> launchpadListeners = new ArrayList<>();

    public MapViewModel(@NonNull Application application) {
        super(application);
        SpaceXApiManager spaceXApiManager = ((SpaceApp) application).getApiManager();
        spaceXApiManager.addListener(this);
        spaceXApiManager.getLaunchPadsData();
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