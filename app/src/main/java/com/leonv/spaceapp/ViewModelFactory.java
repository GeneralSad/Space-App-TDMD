package com.leonv.spaceapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Viewmodels.MapViewModel;
import com.leonv.spaceapp.Viewmodels.RocketsViewModel;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static final String LOGTAG = ViewModelFactory.class.getName();

    private SpaceXApiManager spaceXApiManager;
    private Application application;

    public ViewModelFactory(Application application, SpaceXApiManager manager) {
        this.application = application;
        spaceXApiManager = manager;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
//        if (modelClass == UpcomingViewModel.class) {
//            return (T) new UpcomingViewModel(application);
//        } else if (modelClass == RocketsViewModel.class) {
//            return (T) new RocketsViewModel(spaceXApiManager);
//        } else if (modelClass == MapViewModel.class) {
//            return (T) new MapViewModel();
//        } else {
//            return (T) new MapViewModel();
//        }
        return null;
    }

}
