package com.leonv.spaceapp.Viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.leonv.spaceapp.API.SpaceXApiManager;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static final String LOGTAG = ViewModelFactory.class.getName();

    private SpaceXApiManager spaceXApiManager;

    public ViewModelFactory(SpaceXApiManager manager) {
        spaceXApiManager = manager;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == UpcomingViewModel.class) {
            return (T) new UpcomingViewModel(spaceXApiManager);
        } else if (modelClass == RocketsViewModel.class) {
            return (T) new RocketsViewModel(spaceXApiManager);
        } else if (modelClass == MapViewModel.class) {
            return (T) new MapViewModel();
        } else {
            return (T) new MapViewModel();
        }
    }

}
