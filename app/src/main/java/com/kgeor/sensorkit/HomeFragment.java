package com.kgeor.sensorkit;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgeor.sensorkit.model.DataItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Main Home Activity listing all the sensors in a Recycler View list
 *
 * @author Keegan George
 * @version 1.0
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    // FIELDS //
    private List<Sensor> mSensorList; // list of sensors
    private List<DataItem> mSensorNames; // list of sensor names
    private RecyclerView recyclerView;
    private SensorManager mSensorManager; // reference to sensor manager

    // CONSTRUCTOR //
    public HomeFragment() {
        // Required empty public constructor
    }

    // METHODS //

    /**
     * Initializes values on fragment creation
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // reference to sensor and attach listener (acquire later - release early)
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        // create an ArrayList filled with all the sensors of the device
        mSensorNames = new ArrayList<>();
        for (int i = 0; i < mSensorList.size(); i++) {
            mSensorNames.add(new DataItem(i, mSensorList.get(i).getName()));
        }
        DataItemAdapter adapter = new DataItemAdapter(this.getActivity(), mSensorNames);
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // updates actionbar title
        ((MainActivity) getActivity()).setActionBarTitle("Sensor Kit");
    }

    /**
     * Method for when View is created for fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
