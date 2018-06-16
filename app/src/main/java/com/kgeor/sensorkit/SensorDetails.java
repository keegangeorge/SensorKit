package com.kgeor.sensorkit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;


import java.util.List;

/**
 * Responsible for expanded details of a specific sensor
 */
public class SensorDetails extends AppCompatActivity implements SensorEventListener {
    // FIELDS //
    TextView sensorInfo, sensorValues; // xml references
    SensorManager mSensorManager; // reference to sensor manager
    Sensor sensor = null; // sensor is initially null
    List<Sensor> sensorList; // list of sensors on device

    // METHODS //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_details);

        // acquire sensors
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        // link XML to Java
        sensorInfo = findViewById(R.id.sensor_details);
        sensorValues = findViewById(R.id.sensor_values);

        sensor = mSensorManager.getDefaultSensor(sensorList.get(getIntent().getExtras().getInt("sensorKey")).getType());
        // update action bar title to selected sensor's name
        getSupportActionBar().setTitle(sensor.getName());

        // string for sensor details
        String sensorDetails =
                "<b>Name:</b> \"" + sensor.getName() + "\"<br>" +
                        "<b>Type:</b> \"" + sensor.getType() + "\"<br>" +
                        "<b>Version:</b> \"" + sensor.getVersion() + "\"<br>" +
                        "<b>Vendor:</b> \"" + sensor.getVendor() + "\"<br>" +
                        "<b>Power:</b> \"" + sensor.getPower() + "\"<br>" +
                        "<b>Resolution:</b> \"" + sensor.getResolution() + "\"<br>" +
                        "<b>Minimum Delay:</b> \"" + sensor.getMinDelay() + "\"<br>" +
                        "<b>Maximum Range:</b> \"" + sensor.getMaximumRange() + "\"<br>";
        // set TextView based on receiving String from an HTML format
        sensorInfo.setText(Html.fromHtml(sensorDetails));
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    /**
     * Responsible for events when sensor values are updated
     *
     * @param event current event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        // array of sensor values
        float[] values = event.values;

        String curVal = ""; // initial value

        // loop through all values and display & update them in the text view
        for (int i = 0; i < values.length; i++) {
            curVal += Float.toString(values[i]) + "\n";
        }
        sensorValues.setText(curVal);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated stub
    }
    
} // class end
