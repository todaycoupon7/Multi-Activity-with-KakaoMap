package com.sample.todaycoupon7.multiactivitymapsample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClicked(View v) {
        switch (v.getId()) {
            case R.id.btnDaumMap:
                startActivity(new Intent(this, DaumMapActivity.class));
                break;
            case R.id.btnNaverMap:
                startActivity(new Intent(this, NaverMapActivity.class));
                break;
        }
    }
}
