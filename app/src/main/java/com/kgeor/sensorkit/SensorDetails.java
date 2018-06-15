package com.kgeor.sensorkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SensorDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        String itemId = getIntent().getExtras().getString(DataItemAdapter.ITEM_ID_KEY);
    }
}
