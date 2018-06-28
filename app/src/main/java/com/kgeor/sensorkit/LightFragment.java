package com.kgeor.sensorkit;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Light fragment class for updating light sensor
 *
 * @author Keegan George
 * @version 1.0
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment implements SensorEventListener {
    // FIELDS //
    SensorManager mSensorManager;
    Sensor mLightSensor;
    TextView lightSensorValTextView;
    Ringtone ring;

    // CONSTRUCTOR //
    public LightFragment() {
        // Required empty public constructor
    }

    // METHODS //

    /**
     * Initializes values on fragment creation
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lightSensorValTextView = getActivity().findViewById(R.id.light_sensor_value);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    /**
     * Method for when View is created for fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light, container, false);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // update actionbar title
        ((MainActivity) getActivity()).setActionBarTitle("Light Sensor");

        // update light sensor text view based on sensor values or invalid sensor
        if (mLightSensor == null) {
            lightSensorValTextView.setText("Light Sensor Unavailable");
        } else {
            mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // check for data inside light sensor and display it
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightSensorValTextView.setText(String.valueOf(event.values[0]));

            // play sound / toast if light sensor is fully covered
            if(event.values[0] == 0){
                Toast.makeText(this.getActivity(), "No Light!", Toast.LENGTH_SHORT).show();
                Uri beep = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ring = RingtoneManager.getRingtone(getActivity().getApplicationContext(), beep);
                ring.play();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }
}
