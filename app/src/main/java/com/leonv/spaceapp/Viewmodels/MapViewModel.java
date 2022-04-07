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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MapViewModel extends ViewModel {

    private static final String LOGTAG = MapViewModel.class.getName();

    public MapViewModel() {
    }
}