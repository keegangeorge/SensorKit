package com.kgeor.sensorkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author Keegan George
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // FIELDS //
    private ArrayList<String> mNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started.");
        initItemNames();

    }

    private void initItemNames() {
        mNames.add("Accelerometer");
        mNames.add("This is sensor 2");
        mNames.add("This is sensor 3");
        mNames.add("This is sensor 4");
        mNames.add("This is sensor 5");
        mNames.add("This is sensor 6");
        mNames.add("This is sensor 7");
        mNames.add("This is sensor 8");
        mNames.add("This is sensor 9");
        mNames.add("This is sensor 10");
        mNames.add("This is sensor 11");
        mNames.add("This is sensor 12");
        mNames.add("This is sensor 13");
        mNames.add("This is sensor 14");
        mNames.add("This is sensor 15");
        mNames.add("This is sensor 16");
        mNames.add("This is sensor 17");
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
