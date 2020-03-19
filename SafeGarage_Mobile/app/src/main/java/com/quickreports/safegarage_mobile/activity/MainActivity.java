package com.quickreports.safegarage_mobile.activity;

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

import com.quickreports.safegarage_mobile.BackEnd;
import com.quickreports.safegarage_mobile.R;
import com.quickreports.safegarage_mobile.activity.fragments.AlarmFragment;
import com.quickreports.safegarage_mobile.activity.fragments.PairFragment;
import com.quickreports.safegarage_mobile.activity.fragments.TimeFragment;
import com.quickreports.safegarage_mobile.models.Alarm;
import com.quickreports.safegarage_mobile.models.CloseTime;
import com.quickreports.safegarage_mobile.models.GarageDoor;
import com.quickreports.safegarage_mobile.models.StatusResponse;
import com.quickreports.safegarage_mobile.models.Temperature;
import com.quickreports.safegarage_mobile.rest.interfaces.apiError;
import com.quickreports.safegarage_mobile.rest.interfaces.apiSuccess;

import java.util.concurrent.Semaphore;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements PairFragment.OnPairFragmentInteractionListener,
        AlarmFragment.OnAlarmFragmentInteractionListener, TimeFragment.OnTimeFragmentInteractionListener {

    /**
     * The number of top fragments
     */
    private static final int NUM_FRAGMENTS = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access
     * previous and next fragments
     */
    private ViewPager viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget
     */
    private PagerAdapter pagerAdapter;

    private TimeFragment timeFragment;
    private AlarmFragment alarmFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        BackEnd.retrofitSetup();
        BackEnd.getStatus(new apiError(){
            @Override
            public void run(String error) {
                // TODO: display to the user that they are not connected?
            }
        }, new apiSuccess(){
            @Override
            public void run(StatusResponse input) {
                updateModelStatus(input);
                initializeFragments();
            }
        });

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
    }

    private void updateModelStatus(StatusResponse status) {
        GarageDoor.state = status.state;
        Alarm.isCoAlaraming = status.co;
        Alarm.isSmokeAlarming = status.smoke;
        Temperature.temperature = status.temp;
        CloseTime.time = status.close_time;
    }

    private void initializeFragments() {
        setupDoorFragment();
        timeFragment.setupTimeFragment();
    }

    // This might need to move into the PairFragment class

    private void setupDoorFragment() {
        Log.i(getClass().toString(), "Initializing Garage Door Fragment");
        final GifImageView garageDoorView = findViewById(R.id.garage_door_view);

        // set the Gif based on the current state of the garage door
        if (GarageDoor.state == GarageDoor.CLOSED)
            garageDoorView.setBackgroundResource(R.drawable.garage_door_opening);
        else if (GarageDoor.state == GarageDoor.OPEN)
            garageDoorView.setBackgroundResource(R.drawable.garage_door_closing);
        else if (GarageDoor.state == GarageDoor.TRANSITIONING)
            // TODO: what do we do in this case?
            // for now, I'll just use the opening gif
            // in the future, we need to know if the garage is currently being opened or closed
            garageDoorView.setBackgroundResource(R.drawable.garage_door_opening);
        else
            Log.e(getClass().toString(), "Unknown Garage Door State: " + GarageDoor.state);

        // stop the animation so that its just an image
        GifDrawable garageDoorAnimation = (GifDrawable)garageDoorView.getBackground();
        garageDoorAnimation.stop();

        Log.i(getClass().toString(), "Setting Garage Door State to: " + GarageDoor.getState());
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
            switch (GarageDoor.state) {
                // Garage Door was open, so display the closing gif
                case GarageDoor.OPEN: {
                    garageDoorView.setBackgroundResource(R.drawable.garage_door_closing);
                    garageDoorAnimation.start();
                    GarageDoor.state = GarageDoor.CLOSED;
                    break;
                }
                // Garage Door was closed, so display the opening gif
                case GarageDoor.CLOSED: {
                    garageDoorView.setBackgroundResource(R.drawable.garage_door_opening);
                    garageDoorAnimation.start();
                    GarageDoor.state = GarageDoor.OPEN;
                    break;
                }
                // Garage Door is transiting ... may not need this
                case GarageDoor.TRANSITIONING: {
                    GarageDoor.state = GarageDoor.TRANSITIONING;
                    break;
                }
                default: {
                    Log.e(getClass().toString(), "Unknown Garage Door State: " + GarageDoor.state);
                }
            }
            // tell the server to actually toggle the garage door state
            BackEnd.toggleDoor(new apiError(){
                @Override
                public void run(String error) {

                }
            }, new apiSuccess(){
                @Override
                public void run(StatusResponse input) {

                }
            });

            Log.i(getClass().toString(), "Setting Garage Door State to: " + GarageDoor.getState());
        }
    }

    @Override
    public void onPairFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAlarmFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTimeFragmentInteraction(Uri uri) {

    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
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
                    return PairFragment.newInstance();
                case 1:
                    timeFragment = TimeFragment.newInstance();
                    return timeFragment;
                case 2:
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
