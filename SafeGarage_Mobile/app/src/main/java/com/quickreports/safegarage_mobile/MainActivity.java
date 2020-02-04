package com.quickreports.safegarage_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.quickreports.safegarage_mobile.fragments.AlarmFragment;
import com.quickreports.safegarage_mobile.fragments.DoorFragment;
import com.quickreports.safegarage_mobile.fragments.PairFragment;
import com.quickreports.safegarage_mobile.fragments.TemperatureFragment;
import com.quickreports.safegarage_mobile.fragments.TimeFragment;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements PairFragment.OnPairFragmentInteractionListener,
        DoorFragment.OnDoorFragmentInteractionListener, TemperatureFragment.OnTemperatureFragmentInteractionListener,
        AlarmFragment.OnAlarmFragmentInteractionListener, TimeFragment.OnTimeFragmentInteractionListener {

    /**
     * The number of top fragments
     */
    private static final int NUM_FRAGMENTS = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access
     * previous and next fragments
     */
    private ViewPager viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget
     */
    private PagerAdapter pagerAdapter;

    /**
     * The BackEnd that will communicate to the server
     */
    private BackEnd server;

    /**
     * Instance variables for the garage
     */
    // assume to be open until REST API is setup
    private int garageDoorState = 1; // 1 = open, 2 = closed, 3 = transiting

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Instantiate a ViewPager and a PagerAdapter
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // set the view pager's OnPageChangeListener, so it can
        // swap out the top fragment for the previous/next fragment
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // setup the server
        server = new BackEnd();

        // set the data for all of the fragements
        initializeFragments();
    }

    private void initializeFragments() {
        setupDoorFragment();
        if (setupPairFragment()) {
            Log.i(getClass().toString(), "Initializing fragments");
            // TODO get all the data from SafeGarage and set the respective fragments
        } else {
            // TODO should we try to pair again?
        }
    }

    private boolean setupPairFragment() {
        // set the onCheckChanged event to the Switch
        final Switch pairUnpairSwitch = findViewById(R.id.pair_switch);
        pairUnpairSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            // toggle from being paired to unpaired, and visa versa
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // if switch is set to paired, then re-initialize all the fragments
                    Log.i(getClass().toString(), "Paired to SafeGarage");
                } else {
                    // TODO should we try to pair again?
                    // if switch is set to unpaired, then ...
                    Log.i(getClass().toString(), "Unpaired from SafeGarage");
                }
            }
        });

        return pairUnpairSwitch.isChecked();
    }

    private void setupDoorFragment() {
        Log.i(getClass().toString(), "Initializing Garage Door Fragment");
        final GifImageView garageDoorView = findViewById(R.id.garage_door_view);
        garageDoorView.setBackgroundResource(R.drawable.garage_door_closing);
        GifDrawable garageDoorAnimation = (GifDrawable)garageDoorView.getBackground();
        garageDoorAnimation.stop();
    }

    /**
     * Called when the user clicks the Garage Door image on the bottom of the screen.
     * @param view
     */
    public void onGarageDoorClick(View view) {
        final GifImageView garageDoorView = findViewById(R.id.garage_door_view);
        GifDrawable garageDoorAnimation = (GifDrawable)garageDoorView.getBackground();

        // Only allow the user to click the Garage Door if the gif is done playing
        if (!garageDoorAnimation.isPlaying())
        {
            // Play the open or close garage door gif depending on the current state
            switch (garageDoorState) {
                // Garage Door was open, so display the closing gif
                case 1: {
                    garageDoorView.setBackgroundResource(R.drawable.garage_door_closing);
                    garageDoorAnimation.start();
                    garageDoorState = 2;
                    break;
                }
                // Garage Door was closed, so display the opening gif
                case 2: {
                    garageDoorView.setBackgroundResource(R.drawable.garage_door_opening);
                    garageDoorAnimation.start();
                    garageDoorState = 1;
                    break;
                }
                // Garage Door is transiting ... may not need this
                case 3: {
                    break;
                }
                default: {
                    Log.e(getClass().toString(), "Unknown Garage Door State: " + garageDoorState);
                }
            }
            // tell the server to actually toggle the garage door state
            server.toggleDoor();
        }
    }

    @Override
    public void onDoorFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPairFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAlarmFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTemperatureFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTimeFragmentInteraction(Uri uri) {

    }

    /**
     * A simple pager adapter that represents 4 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TimeFragment.newInstance();
                case 1:
                    return TemperatureFragment.newInstance();
                case 2:
                    return PairFragment.newInstance();
                case 3:
                    return AlarmFragment.newInstance();
                    default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_FRAGMENTS;
        }
    }
}
