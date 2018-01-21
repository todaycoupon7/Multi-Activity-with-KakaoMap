package com.sample.todaycoupon7.multiactivitymapsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapView;

public class NextActivity extends AppCompatActivity {

    private MapView mapView;
    private ViewGroup mapViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // java code
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // init or resume map
        mapView = new MapView(this);
        mapViewContainer.addView(mapView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // release map
        mapViewContainer.removeAllViews();
    }

}
