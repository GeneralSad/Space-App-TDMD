package com.leonv.spaceapp;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.leonv.spaceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = MainActivity.class.getName();

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
//    private SpaceXApiManager spaceXApiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        spaceXApiManager = new SpaceXApiManager(this);

//        UpcomingViewModel upcomingViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication(), spaceXApiManager)).get(UpcomingViewModel.class);
//        MapViewModel mapViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication(), spaceXApiManager)).get(MapViewModel.class);
//        RocketsViewModel rocketsViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication(), spaceXApiManager)).get(RocketsViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_map, R.id.nav_upcoming, R.id.nav_rockets)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}