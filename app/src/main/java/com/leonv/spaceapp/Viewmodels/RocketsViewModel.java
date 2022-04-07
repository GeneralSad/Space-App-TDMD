package com.leonv.spaceapp.Viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Fragments.MapFragment;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.SpaceApp;

public class RocketsViewModel extends AndroidViewModel implements SpaceXApiListener {

    private static final String LOGTAG = RocketsViewModel.class.getName();

    private MutableLiveData<String> mText;
    private final SpaceXApiManager spaceXApiManager;

    public RocketsViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is rockets fragment");

        this.spaceXApiManager = ((SpaceApp)application).getApiManager();
        this.spaceXApiManager.addListener(this);
    }

    public LiveData<String> getText() {
        return mText;
    }

    @Override
    public void onFlightAvailable(Flight flight) {

    }

    @Override
    public void onRocketAvailable(Rocket rocket) {

    }

    @Override
    public void onPayloadAvailable(Payload payload) {

    }

    @Override
    public void onLaunchpadAvailable(Launchpad launchpad) {

    }

    @Override
    public void onLandpadAvailable(Landpad landpad) {

    }

    @Override
    public void onDataError(Error error) {

    }
}