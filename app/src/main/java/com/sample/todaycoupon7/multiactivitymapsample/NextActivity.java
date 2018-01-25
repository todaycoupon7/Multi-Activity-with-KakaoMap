package com.sample.todaycoupon7.multiactivitymapsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class NextActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_X_WCONG = "xWCONG";
    public static final String INTENT_EXTRA_Y_WCONG = "yWCONG";

    private ImageView ivStaticMap;
    private ImageView ivMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        final double xWCONG = getIntent().getDoubleExtra(INTENT_EXTRA_X_WCONG, 0.f);
        final double yWCONG = getIntent().getDoubleExtra(INTENT_EXTRA_Y_WCONG, 0.f);

        ivMarker = findViewById(R.id.ivMarker);
        ivStaticMap = findViewById(R.id.ivStaticMap);
        ivStaticMap.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivStaticMap.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // loading static map image
                int mapWidth = ivStaticMap.getWidth();
                int mapHeight = ivStaticMap.getHeight();
                final String staticMapUrl = "http://map2.daum.net/map/imageservice?IW=" + (mapWidth / 2)
                        + "&IH=" + (mapHeight / 2)
                        + "&MX=" + (int) xWCONG
                        + "&MY=" + (int) yWCONG
                        + "&SCALE=2.5&COORDSTM=WCONGNAMUL";
                Glide.with(NextActivity.this).load(staticMapUrl).into(ivStaticMap);

                ivMarker.setTranslationX((mapWidth - ivMarker.getWidth()) / (float) 2);
                ivMarker.setTranslationY((mapHeight / (float) 2) - ivMarker.getHeight());
            }
        });
    }

}
