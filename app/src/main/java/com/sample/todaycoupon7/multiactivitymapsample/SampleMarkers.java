package com.sample.todaycoupon7.multiactivitymapsample;

import java.util.ArrayList;

/**
 * Created by chaesooyang on 2018. 1. 26..
 */

public class SampleMarkers {

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
    public static ArrayList<Marker> markers = new ArrayList<>();
    static {
        markers.add(new Marker("첫번째 마커", 37.564449310302734f, 126.98155212402344f));
        markers.add(new Marker("두번째 마커", 37.56782913208008f, 126.98147583007813f));
        markers.add(new Marker("세번째 마커", 37.56437301635742f, 126.985595703125f));
        markers.add(new Marker("네번째 마커", 37.563751220703125f, 126.98139190673828f));
        markers.add(new Marker("다섯번째 마커", 37.5631217956543f, 126.9827651977539f));
    }

}
