package com.leonv.spaceapp.Viewmodels;

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

public class MapViewModel extends ViewModel implements SpaceXApiListener {

    private static final String LOGTAG = MapViewModel.class.getName();

    private MutableLiveData<String> mText;
    private SpaceXApiManager spaceXApiManager;

    //TODO: This need to somehow be able to be removed like in UpcomingViewModel, just don't know how or why
    public MapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
    }

    public MapViewModel(SpaceXApiManager spaceXApiManager) {

        this.spaceXApiManager = spaceXApiManager;
        this.spaceXApiManager.addListener(this);

        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
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