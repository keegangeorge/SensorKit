package com.kgeor.sensorkit;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keegan George
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    // GENERAL FIELDS //
    private static final String TAG = "MainActivity";

    // RECYCLER VIEW FIELDS //
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mSensorNames = new ArrayList<>();

    // SENSOR FIELDS //
    SensorManager mySensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started.");

        // reference to sensor and attach listener (acquire later - release early)
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> mSensorList = mySensorManager.getSensorList(Sensor.TYPE_ALL);


        // Add sensors to recycler view list
        for (int i = 0; i < mSensorList.size(); i++) {
            mSensorNames.add(mSensorList.get(i).getName());
        }

        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected  void onPause() {
        super.onPause();
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(this, mSensorNames);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
