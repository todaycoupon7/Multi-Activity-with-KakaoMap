package com.sample.todaycoupon7.multiactivitymapsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by chaesooyang on 2018. 1. 26..
 */

public class StaticMapActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_X_WCONG = "xWCONG";
    public static final String INTENT_EXTRA_Y_WCONG = "yWCONG";
    public static final String INTENT_EXTRA_LONGITUDE = "longitude";
    public static final String INTENT_EXTRA_LATITUDE = "latitude";

    private ImageView ivStaticMap;
    private ImageView ivMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_map);

        // for daum map
        final double xWCONG = getIntent().getDoubleExtra(INTENT_EXTRA_X_WCONG, 0.f);
        final double yWCONG = getIntent().getDoubleExtra(INTENT_EXTRA_Y_WCONG, 0.f);
        // for naver map
        final double longitude = getIntent().getDoubleExtra(INTENT_EXTRA_LONGITUDE, 0.f);
        final double latitude = getIntent().getDoubleExtra(INTENT_EXTRA_LATITUDE, 0.f);

        ivMarker = findViewById(R.id.ivMarker);
        ivStaticMap = findViewById(R.id.ivStaticMap);
        ivStaticMap.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivStaticMap.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // loading static map image
                int mapWidth = ivStaticMap.getWidth();
                int mapHeight = ivStaticMap.getHeight();
                String staticMapUrl = null;
                if(xWCONG != 0 && yWCONG != 0) {
                    staticMapUrl = "http://map2.daum.net/map/imageservice?IW=" + (mapWidth / 2)
                            + "&IH=" + (mapHeight / 2)
                            + "&MX=" + (int) xWCONG
                            + "&MY=" + (int) yWCONG
                            + "&SCALE=2.5&COORDSTM=WCONGNAMUL";
                } else if(longitude != 0 && latitude != 0) {
                    final String client_id = getString(R.string.naver_map_client_id);
                    final String regWebServiceUrl = getString(R.string.naver_map_for_web_service_url);
                    staticMapUrl = "https://openapi.naver.com/v1/map/staticmap.bin?clientId=" + client_id
                            + "&url=" + regWebServiceUrl
                            + "&crs=EPSG:4326&center=" + longitude
                            + "," + latitude
                            + "&level=13&w=" + mapWidth
                            + "&h=" + mapHeight + "&baselayer=default";
                } else {
                    return;
                }
                Glide.with(ivStaticMap.getContext()).load(staticMapUrl).into(ivStaticMap);

                ivMarker.setTranslationX((mapWidth - ivMarker.getWidth()) / (float) 2);
                ivMarker.setTranslationY((mapHeight / (float) 2) - ivMarker.getHeight());
            }
        });
    }

}
