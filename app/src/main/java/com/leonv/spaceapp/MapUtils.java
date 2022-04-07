package com.leonv.spaceapp;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.List;

public class MapUtils {

    public static void AddPoiToMap(MapView mapView, GeoPoint geoPoint) {
        Marker marker = new Marker(mapView);
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        //Hierdoor zijn is er geen auto navigatie naar de marker en geen pop up, onclick
        marker.setOnMarkerClickListener((marker1, mapView1) -> false);

        mapView.getOverlays().add(marker);
        mapView.invalidate();
    }

    public static void AddPoisToMap(MapView mapView, List<GeoPoint> geoPoints) {
        for(GeoPoint geoPoint :geoPoints) {
            Marker marker = new Marker(mapView);
            marker.setPosition(geoPoint);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            //Hierdoor zijn is er geen auto navigatie naar de marker en geen pop up, onclick
            marker.setOnMarkerClickListener((marker1, mapView1) -> false);

            mapView.getOverlays().add(marker);
        }
        mapView.invalidate();
    }
}
