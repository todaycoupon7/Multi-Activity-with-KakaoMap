package com.sample.todaycoupon7.multiactivitymapsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private ViewGroup mapViewContainer;

    private double longitude = 0.f;
    private double latitude = 0.f;
    private int zoomLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // java code
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // init or resume map
        mapView = new MapView(this);
        mapViewContainer.addView(mapView);

        if(latitude != 0 &&
                longitude != 0) {
            mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude,longitude), false);
        }
        if(zoomLevel != 0) {
            mapView.setZoomLevel(zoomLevel, false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // release map
        final MapPoint cmp = mapView.getMapCenterPoint();
        if(cmp != null) {
            latitude = cmp.getMapPointGeoCoord().latitude;
            longitude = cmp.getMapPointGeoCoord().longitude;
        } else {
            latitude = longitude = 0;
        }
        zoomLevel = mapView.getZoomLevel();
        mapViewContainer.removeAllViews();
    }

    public void onClicked(View v) {
        if(v.getId() == R.id.btnNext) {
            startActivity(new Intent(this, NextActivity.class));
        }
    }
}
