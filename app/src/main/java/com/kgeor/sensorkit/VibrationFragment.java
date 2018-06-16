package com.kgeor.sensorkit;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Class responsible for the vibration / device on table fragment
 *
 * @author Keegan George
 * @version 1.0
 * A simple {@link Fragment} subclass.
 */
public class VibrationFragment extends Fragment implements SensorEventListener {
    // FIELDS //
    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    private ImageView vibratingImg, nonVibratingImg; // image of vibrating device
    private boolean isFlat = false; // state of device being flat on table
    float[] accValues = new float[3]; // values from accelerometer
    Vibrator vibration; // reference to vibration motor

    // CONSTRUCTOR //
    public VibrationFragment() {
        // Required empty public constructor
    }

    // METHODS //

    /**
     * Initializes values on fragment creation
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // link XML to Java
        vibratingImg = getActivity().findViewById(R.id.vibration_image);
        nonVibratingImg = getActivity().findViewById(R.id.not_vibration_image);

        // acquire sensors
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vibrate, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // update action bar title
        ((MainActivity) getActivity()).setActionBarTitle("Vibration Sensor");

        // register accelerometer sensor (acquire late)
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        // release sensor (release early)
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // reference to type of sensor
        int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            accValues = event.values;

            /* if device is flat display phone vibrating image
             * if device is not flat display regular phone image
             */
            if (isFlat) {
                vibratingImg.setVisibility(View.VISIBLE);
                nonVibratingImg.setVisibility(View.INVISIBLE);
            } else if (!isFlat) {
                vibratingImg.setVisibility(View.INVISIBLE);
                nonVibratingImg.setVisibility(View.VISIBLE);
            }

            // Device is flat, begin vibration
            if (accValues[0] < 0.3 && accValues[1] < 0.3 && accValues[2] > 9.6 && !isFlat) {
                isFlat = true;

                vibration = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

                if (vibration.hasVibrator()) {
                    // Vibrate for 5000 milliseconds
                    vibration.vibrate(5000);
                    // Display message to user when device is flat
                    Toast.makeText(this.getActivity(), "Device Flat - Beep", Toast.LENGTH_SHORT).show();
                } else {
                    //Else display a toast "No Vibrator - Device Flat"
                    Toast.makeText(this.getActivity(), "Vibrator Unavailable but Device is Flat", Toast.LENGTH_SHORT).show();
                }

            } else if (((accValues[0] > 3.0 || accValues[1] > 3.0) && accValues[2] < 8.0) && isFlat) {
                isFlat = false;

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }
} // class end

