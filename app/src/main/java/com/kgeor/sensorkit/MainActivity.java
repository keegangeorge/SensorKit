package com.kgeor.sensorkit;


import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


/**
 * Main Activity Class
 *
 * @author Keegan George
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    // FIELDS //
    private static final String TAG = "MainActivity";
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    public static final int REQUEST_CODE = 200;


    // REFERENCES TO FRAGMENTS //
    private HomeFragment homeFragment;
    private EnviroFragment environmentFragment;
    private LightFragment lightFragment;
    private MoveFragment movementFragment;
    private VibrationFragment vibrationFragment;

    // PERMISSIONS //
    private boolean permissionRecordAudio = false;
    private boolean permissionWriteAudio = false;
    private String[] permissions = {
            "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"
    };


    /**
     * Responsible for requesting permissions to user
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // what to do when permission is granted
        switch (REQUEST_CODE) {
            case 200:
                permissionRecordAudio = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                permissionWriteAudio = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                break;
        }

        // what to do when permission is denied
        if (!permissionRecordAudio) {
            MainActivity.super.finish();
        }

        if (!permissionWriteAudio) {
            MainActivity.super.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, REQUEST_CODE);
        }

        // link XML to Java
        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);
        initFragments();
    } // onCreate() end

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Responsible for settings the title of the action bar
     *
     * @param title title to be set
     */
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * Responsible for settings fragment
     *
     * @param fragment desired fragment
     */
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Create new fragment objects
     * Control action of bottom bar
     */
    protected void initFragments() {
        homeFragment = new HomeFragment();
        environmentFragment = new EnviroFragment();
        lightFragment = new LightFragment();
        movementFragment = new MoveFragment();
        vibrationFragment = new VibrationFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_light:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(lightFragment);
                        return true;

                    case R.id.nav_vibration:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(vibrationFragment);
                        return true;

                    case R.id.nav_movement:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(movementFragment);
                        return true;

                    case R.id.nav_environment:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(environmentFragment);
                        return true;

                    default:
                        return false;
                }
            }


        });
    }
} // class end
