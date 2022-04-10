package com.leonv.spaceapp.utils;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapUtils {

    public static Marker AddPoiToMap(MapView mapView, GeoPoint geoPoint) {
        Marker marker = new Marker(mapView);
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        //Hierdoor zijn is er geen auto navigatie naar de marker en geen pop up, onclick
        marker.setOnMarkerClickListener((marker1, mapView1) -> false);

        mapView.getOverlays().add(marker);
        mapView.invalidate();
        return marker;
    }

    public static List<Marker> AddPoisToMap(MapView mapView, List<GeoPoint> geoPoints) {
        List<Marker> markers = new ArrayList<>();
        for(GeoPoint geoPoint :geoPoints) {
            Marker marker = new Marker(mapView);
            marker.setPosition(geoPoint);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            //Hierdoor zijn is er geen auto navigatie naar de marker en geen pop up, onclick
            marker.setOnMarkerClickListener((marker1, mapView1) -> false);

            mapView.getOverlays().add(marker);
            markers.add(marker);
        }
        mapView.invalidate();
        return markers;
    }
}
