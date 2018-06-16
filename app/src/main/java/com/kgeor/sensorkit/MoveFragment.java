package com.kgeor.sensorkit;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Fragment for the device movement
 * <p>
 * A simple {@link Fragment} subclass.
 */
public class MoveFragment extends Fragment implements View.OnClickListener, SensorEventListener {
    // FIELDS //

    // XML //
    private Button btnMotion;
    private TextView motionResult;

    // DEVICE MOTION //
    private float movementInitial, movementFinal;
    private float movementDampener;
    boolean isMoving = false;

    // SENSORS //
    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    float[] accValues = new float[3]; // x-y-z axis

    // CONSTRUCTOR //
    public MoveFragment() {
        // Required empty public constructor
    }

    // METHODS //

    /**
     * Initializes values on fragment creation
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Link XML to Java
        btnMotion = getActivity().findViewById(R.id.btn_motion);
        btnMotion.setOnClickListener(this);
        motionResult = getActivity().findViewById(R.id.tv_motion);

        // initialize device movement default values
        movementInitial = SensorManager.GRAVITY_EARTH;
        movementFinal = SensorManager.GRAVITY_EARTH;
        movementDampener = 0.0f;

        // activate sensor service (acquire late)
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_move, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // sets the action bar title
        ((MainActivity) getActivity()).setActionBarTitle("Movement Sensor");
        // register the accelerometer sensor
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onPause() {
        // unregister sensors (release early)
        mSensorManager.unregisterListener(this);
        super.onPause();
    }


    /**
     * Responsible for sensor value changes
     *
     * @param event current sensor event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            accValues = event.values;

            // identify motion of device
            movementFinal = movementInitial;
            movementInitial = (float) Math.sqrt(accValues[0] * accValues[0] + accValues[1] * accValues[1] + accValues[2] * accValues[2]);
            float difference = movementInitial - movementFinal;
            movementDampener = movementDampener * 0.95f + difference;

            // control boolean value based on device movement
            if (movementDampener > 0.2) {
                isMoving = true;
            } else if (movementDampener < 3) {
                isMoving = false;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View v) {
        // update TextView based on if device is in motion or not
        if (v.getId() == R.id.btn_motion) {
            if (isMoving == true) {
                motionResult.setText("Device in motion");
            } else {
                motionResult.setText("Device is NOT in motion");
            }
        }
    }
}
