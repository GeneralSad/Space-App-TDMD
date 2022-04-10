package com.leonv.spaceapp.Views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leonv.spaceapp.utils.GeofenceManager;
import com.leonv.spaceapp.utils.LaunchpadViewHolder;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.SpaceApp;
import com.leonv.spaceapp.Viewmodels.MapViewModel;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;
import com.leonv.spaceapp.databinding.FragmentMapBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements MapViewModel.LaunchpadListener {

    private FragmentMapBinding binding;
    private MapViewModel mapViewModel;
    private UpcomingViewModel upcomingViewModel;
    private final ActivityResultLauncher<String> requestPermissionLauncher;

    public MapFragment() {
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts
                        .RequestPermission(),
                this::permissionCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.mapViewModel = new ViewModelProvider(requireActivity()).get(MapViewModel.class);
        this.upcomingViewModel = new ViewModelProvider(requireActivity()).get(UpcomingViewModel.class);
        this.mapViewModel.addLaunchpadListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mapViewModel.requestLaunchpads();
        upcomingViewModel.requestFlights();
        upcomingViewModel.requestRockets();

        binding = FragmentMapBinding.inflate(inflater, container, false);

        Context ctx = requireContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        initMap();
        askPermissions(ctx);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    //Set default map values
    private void initMap() {
        MapView mapView = binding.mapview;

        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(5);
        mapView.setMaxZoomLevel(20.0);
        mapView.setMinZoomLevel(3.0);
        mapView.setVerticalMapRepetitionEnabled(false);
        mapView.setScrollableAreaLimitLatitude(80.0, -80.0, 10);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        GeoPoint gPt = new GeoPoint(38.495586, -99.4411535) ;
        mapController.setCenter(gPt);
    }

    private void askPermissions(Context context) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission_group.LOCATION) ==
                PackageManager.PERMISSION_DENIED)
        {
            requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION);
            return;
        }

        addLocationToMap();
    }

    private void addLocationToMap()
    {
        MapView mapView = binding.mapview;

        MyLocationNewOverlay locationNewOverlay = new MyLocationNewOverlay(mapView);
        locationNewOverlay.enableMyLocation();
        mapView.getOverlayManager().add(locationNewOverlay);
        mapView.invalidate();
    }

    private void permissionCallback(boolean isGranted)
    {
        if(isGranted)
        {
            addLocationToMap();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLaunchpadAvailable(List<Launchpad> newLaunchpads) {
        List<LaunchpadViewHolder> launchpadViewHolders = newLaunchpads.stream()
                .map((x) -> new LaunchpadViewHolder(this, binding.mapview, x, this.upcomingViewModel))
                .collect(Collectors.toList());

        GeofenceManager geofenceManager = new GeofenceManager((SpaceApp)this.requireContext().getApplicationContext());

        //Add marker and geofence to each launchpad
        for (LaunchpadViewHolder launchpadViewHolder : launchpadViewHolders) {
            Marker marker = launchpadViewHolder.create();
            binding.mapview.getOverlays().add(marker);
            geofenceManager.addGeofence(launchpadViewHolder.getLaunchpad().getId(),
                    launchpadViewHolder.getGeoPoint(),
                    50*1000);
        }
        if(this.mapViewModel.getGeofenceManager() == null){
            this.mapViewModel.setGeofenceManager(geofenceManager);
            geofenceManager.enableGeofences();
        }
        binding.mapview.invalidate();
    }
}