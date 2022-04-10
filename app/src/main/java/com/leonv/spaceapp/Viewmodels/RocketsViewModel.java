package com.leonv.spaceapp.Viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.utils.OnItemClickListener;
import com.leonv.spaceapp.utils.SpaceApp;

import java.util.ArrayList;
import java.util.List;

public class RocketsViewModel extends AndroidViewModel implements SpaceXApiListener, OnItemClickListener {

    private static final String LOGTAG = RocketsViewModel.class.getName();

    private ArrayList<Rocket> rockets = new ArrayList<>();
    private MutableLiveData<Rocket> selectedRocket = new MutableLiveData<>();
    private SpaceXApiManager spaceXApiManager;
    private final ArrayList<RocketsListener> rocketListeners = new ArrayList<>();

    //TODO: This need to somehow be able to be removed like in UpcomingViewModel, just don't know how
    public RocketsViewModel(Application application) {
        super(application);
        this.spaceXApiManager = ((SpaceApp)application).getApiManager();
        this.spaceXApiManager.addListener(this);
        this.spaceXApiManager.getRocketsData();
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    @Override
    public void onRocketsAvailable(ArrayList<Rocket> rockets) {
        this.rockets = rockets;
        this.rocketListeners.forEach(x -> x.onRocketsAvailable(this.rockets));
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.d(LOGTAG, "Pressed: " + rockets.get(clickedPosition).getName());
        this.selectedRocket.setValue(rockets.get(clickedPosition));
    }

    public interface RocketsListener{
        void onRocketsAvailable(List<Rocket> rocketList);
    }

    public void addRocketsListener(RocketsListener rocketsListener)
    {
        if(this.rocketListeners.contains(rocketsListener))
            return;

        this.rocketListeners.add(rocketsListener);
    }

    public void removeRocketsListener(RocketsListener rocketsListener)
    {
        if(!this.rocketListeners.contains(rocketsListener))
            return;

        this.rocketListeners.remove(rocketsListener);
    }
}