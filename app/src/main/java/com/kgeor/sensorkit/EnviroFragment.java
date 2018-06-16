package com.kgeor.sensorkit;


import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Fragment class for environmental noise
 *
 * @author Keegan George
 * @version 1.0
 * A simple {@link Fragment} subclass.
 */
public class EnviroFragment extends Fragment implements View.OnClickListener {
    // FIELDS //
    private TextView noiseResult;
    private MediaRecorder mRecorder;

    // CONSTRUCTOR //
    public EnviroFragment() {
        // Required empty public constructor
    }

    /**
     * Initializes values on fragment creation
     */
    // METHODS //
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // create new Media Recorder object
        mRecorder = new MediaRecorder();

        // link XML TextView of noiseResult to Java
        noiseResult = Objects.requireNonNull(getActivity()).findViewById(R.id.tv_noise);

        // link XML Detect Noise button to Java
        Button btnNoise = getActivity().findViewById(R.id.btn_noise);
        btnNoise.setOnClickListener(this);
    }


    /**
     * Method for when View is created for fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enviro, container, false);
    }

    @Override
    public void onPause() {
        mRecorder.release();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Updates actionbar title
        ((MainActivity) getActivity()).setActionBarTitle("Environmental Noise");

    }

    @Override
    public void onClick(View v) {
        // Set Recorder Audio Source, Output, and Encoder
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile("/dev/null");
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // start recording at max amps
        mRecorder.start();
        mRecorder.getMaxAmplitude();
        /*
         * Record for preset time
         * Updates TextView when noise is heart
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRecorder.getMaxAmplitude() > 500) {
                            noiseResult.setText(R.string.str_noise_heard);
                        } else {
                            noiseResult.setText(R.string.str_noise_unheard);
                        }
                        mRecorder.reset();
                    }
                });
            }
        }, 1000);

    }
}
