package com.sample.todaycoupon7.multiactivitymapsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import static com.sample.todaycoupon7.multiactivitymapsample.SampleMarkers.markers;

/**
 * Created by chaesooyang on 2018. 1. 26..
 */

public class DaumMapActivity extends AppCompatActivity implements MapView.POIItemEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daum_map);

        MapView mapView = findViewById(R.id.map_view);
        mapView.setPOIItemEventListener(this);

        // add markers
        MapPOIItem selectItem = null;
        for(SampleMarkers.Marker marker : markers) {
            MapPOIItem item = new MapPOIItem();
            item.setItemName(marker.name);
            item.setMapPoint(MapPoint.mapPointWithGeoCoord(marker.latitude, marker.longitude));
            item.setMarkerType(MapPOIItem.MarkerType.BluePin);
            item.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            mapView.addPOIItem(item);
            if(selectItem == null) {
                selectItem = item;
            }
        }
        // 기본적으로 선택된 아이템 설정
        if(selectItem != null) {
            mapView.selectPOIItem(selectItem, true);
        }
        // 첫번째 마커의 위치가 지도의 가운데로 오도록 지도를 이동시킴
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(
                markers.get(0).latitude,markers.get(0).longitude), true);
    }

    /**********************************
     * MapView.POIItemEventListener
     **********************************/

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {}

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        final double xWCONG = mapPOIItem.getMapPoint().getMapPointWCONGCoord().x;
        final double yWCONG = mapPOIItem.getMapPoint().getMapPointWCONGCoord().y;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(DaumMapActivity.this, StaticMapActivity.class);
                intent.putExtra(StaticMapActivity.INTENT_EXTRA_X_WCONG, xWCONG);
                intent.putExtra(StaticMapActivity.INTENT_EXTRA_Y_WCONG, yWCONG);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {}

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {}

}
