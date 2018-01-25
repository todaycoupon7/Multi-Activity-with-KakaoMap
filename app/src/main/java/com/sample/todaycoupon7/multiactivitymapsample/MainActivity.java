package com.sample.todaycoupon7.multiactivitymapsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MapView.POIItemEventListener {

    private static final String TAG = "MainActivity";

    static class Marker {
        public String name;
        public double latitude;
        public double longitude;

        public Marker(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    // sample marker data
    private static ArrayList<Marker> markers = new ArrayList<>();
    static {
        markers.add(new Marker("첫번째 마커", 37.564449310302734f, 126.98155212402344f));
        markers.add(new Marker("두번째 마커", 37.56782913208008f, 126.98147583007813f));
        markers.add(new Marker("세번째 마커", 37.56437301635742f, 126.985595703125f));
        markers.add(new Marker("네번째 마커", 37.563751220703125f, 126.98139190673828f));
        markers.add(new Marker("다섯번째 마커", 37.5631217956543f, 126.9827651977539f));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = findViewById(R.id.map_view);
        mapView.setPOIItemEventListener(this);

        // add markers
        MapPOIItem selectItem = null;
        for(Marker marker : markers) {
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
                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                intent.putExtra(NextActivity.INTENT_EXTRA_X_WCONG, xWCONG);
                intent.putExtra(NextActivity.INTENT_EXTRA_Y_WCONG, yWCONG);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {}

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {}

}
