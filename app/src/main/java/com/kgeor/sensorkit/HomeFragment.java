package com.kgeor.sensorkit;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgeor.sensorkit.model.DataItem;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Keegan George
 * @version 1.0
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    List<Sensor> mSensorList; // list of sensors
    List<DataItem> mSensorNames; // list of sensor names
    RecyclerView recyclerView;
    private SensorManager mSensorManager; // reference to sensor manager

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // reference to sensor and attach listener (acquire later - release early)
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        mSensorNames = new ArrayList<>();
        for (int i = 0; i < mSensorList.size(); i++) {
            mSensorNames.add(new DataItem(DataItemAdapter.ITEM_ID_KEY, mSensorList.get(i).getName()));
        }



        DataItemAdapter adapter = new DataItemAdapter(this.getActivity(), mSensorNames);

        recyclerView = getActivity().findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onClick(View v) {

    }
}
