package com.kgeor.sensorkit;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;


/**
 * @author Keegan George
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    // FIELDS //
    private static final String TAG = "MainActivity";
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private EnviroFragment environmentFragment;
    private LightFragment lightFragment;
    private MoveFragment movementFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started."); // TODO Remove before deployment

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        environmentFragment = new EnviroFragment();
        lightFragment = new LightFragment();
        movementFragment = new MoveFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_environment:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(environmentFragment);
                        return true;


                    case R.id.nav_light:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(lightFragment);
                        return true;


                    case R.id.nav_movement:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(movementFragment);
                        return true;

                    default:
                        return false;
                }
            }


        });


    } // onCreate() end


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
