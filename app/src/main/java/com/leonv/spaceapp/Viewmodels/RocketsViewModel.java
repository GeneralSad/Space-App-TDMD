package com.leonv.spaceapp.Viewmodels;

import android.util.Log;

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
import com.leonv.spaceapp.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RocketsViewModel extends ViewModel implements SpaceXApiListener, OnItemClickListener {

    private static final String LOGTAG = RocketsViewModel.class.getName();

    private ArrayList<Rocket> rockets = new ArrayList<>();
    private MutableLiveData<Rocket> selectedRocket = new MutableLiveData<>();
    private SpaceXApiManager spaceXApiManager;

    //TODO: This need to somehow be able to be removed like in UpcomingViewModel, just don't know how
    public RocketsViewModel(SpaceXApiManager spaceXApiManager) {
        this.spaceXApiManager = spaceXApiManager;
        this.spaceXApiManager.addListener(this);
        this.spaceXApiManager.getRocketsData();
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    @Override
    public void onRocketsAvailable(ArrayList<Rocket> rockets) {
        this.rockets = rockets;
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.d(LOGTAG, "Pressed: " + rockets.get(clickedPosition).getName());
        this.selectedRocket.setValue(rockets.get(clickedPosition));
    }
}