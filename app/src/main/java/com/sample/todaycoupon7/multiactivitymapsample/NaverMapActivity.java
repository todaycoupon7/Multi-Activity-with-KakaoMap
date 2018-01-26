package com.sample.todaycoupon7.multiactivitymapsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.sample.todaycoupon7.multiactivitymapsample.support_naver_map.NMapPOIflagType;
import com.sample.todaycoupon7.multiactivitymapsample.support_naver_map.NMapViewerResourceProvider;

import static com.sample.todaycoupon7.multiactivitymapsample.SampleMarkers.markers;


/**
 * Created by chaesooyang on 2018. 1. 26..
 */

public class NaverMapActivity extends NMapActivity implements NMapPOIdataOverlay.OnStateChangeListener {

    private static final String TAG = "NaverMapActivity";

    private NMapView mMapView;
    private NMapOverlayManager mOverlayManager;
    private NMapViewerResourceProvider mMapViewerResourceProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map);

        mMapView = findViewById(R.id.map_view);
        mMapView.setClientId(getString(R.string.naver_map_client_id)); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
        mMapView.setScalingFactor(2.5f);    // 지도에 표시되는 글자 크기 설정

        // create resource provider
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        // create overlay manager
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

        // add markers
        final int count = markers.size();
        NMapPOIdata poiData = new NMapPOIdata(count, mMapViewerResourceProvider);
        poiData.beginPOIdata(count);
        for(SampleMarkers.Marker marker : markers) {
            poiData.addPOIitem(marker.longitude, marker.latitude, marker.name, NMapPOIflagType.PIN, 0);
        }
        poiData.endPOIdata();
        // create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        poiDataOverlay.setOnStateChangeListener(this);
        poiDataOverlay.selectPOIitem(0, true);  // select an item
    }

    /**********************************
     * NMapPOIdataOverlay.OnStateChangeListener
     **********************************/

    @Override
    public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

    }

    @Override
    public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {
        if(nMapPOIitem != null) {
            Intent intent = new Intent(this, StaticMapActivity.class);
            intent.putExtra(StaticMapActivity.INTENT_EXTRA_LONGITUDE, nMapPOIitem.getPoint().longitude);
            intent.putExtra(StaticMapActivity.INTENT_EXTRA_LATITUDE, nMapPOIitem.getPoint().latitude);
            startActivity(intent);
        }
    }
}