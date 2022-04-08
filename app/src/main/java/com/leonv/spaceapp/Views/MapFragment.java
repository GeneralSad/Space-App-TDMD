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

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.MapUtils;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Viewmodels.MapViewModel;
import com.leonv.spaceapp.databinding.FragmentMapBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements SpaceXApiListener {

    private FragmentMapBinding binding;
    private MapViewModel mapViewModel;
    private final ActivityResultLauncher<String> requestPermissionLauncher;
    private SpaceXApiManager spaceXApiManager;

    public MapFragment() {
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                this::permissionCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.mapViewModel = new ViewModelProvider(requireActivity()).get(MapViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);

        Context ctx = requireContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        initMap();

        this.spaceXApiManager = new SpaceXApiManager(ctx);
        this.spaceXApiManager.addListener(this);
        this.spaceXApiManager.getLaunchPadsData();

        askPermissions(ctx);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initMap() {
        MapView mapView = binding.mapview;

        MapController mapController = (MapController) mapView.getController();
        mapController.setZoom(15);
        mapView.setMaxZoomLevel(20.0);
        mapView.setMinZoomLevel(1.0);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.ALWAYS);
        GeoPoint gPt = new GeoPoint(51.588905, 4.776070);
        mapController.setCenter(gPt);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLaunchpadsAvailable(List<Launchpad> launchpads) {
        List<GeoPoint> geoPointList = launchpads.stream()
                .map((x) -> new GeoPoint(x.getLatitude(), x.getLongitude()))
                .collect(Collectors.toList());

        MapUtils.AddPoisToMap(binding.mapview, geoPointList);
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
}