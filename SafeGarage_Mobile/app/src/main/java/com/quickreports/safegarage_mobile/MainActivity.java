package com.quickreports.safegarage_mobile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.quickreports.safegarage_mobile.fragments.AlarmFragment;
import com.quickreports.safegarage_mobile.fragments.DoorFragment;
import com.quickreports.safegarage_mobile.fragments.PairFragment;
import com.quickreports.safegarage_mobile.fragments.TemperatureFragment;
import com.quickreports.safegarage_mobile.fragments.TimeFragment;

import static com.quickreports.safegarage_mobile.fragments.TimeFragment.newInstance;

public class MainActivity extends FragmentActivity implements PairFragment.OnPairFragmentInteractionListener,
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
