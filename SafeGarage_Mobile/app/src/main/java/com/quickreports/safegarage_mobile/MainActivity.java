package com.quickreports.safegarage_mobile;

import androidx.fragment.app.FragmentActivity;

import android.net.Uri;
import android.os.Bundle;

import com.quickreports.safegarage_mobile.fragments.DoorFragment;
import com.quickreports.safegarage_mobile.fragments.PairFragment;

public class MainActivity extends FragmentActivity implements PairFragment.OnPairFragmentInteractionListener,
        DoorFragment.OnDoorFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    @Override
    public void onDoorFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPairFragmentInteraction(Uri uri) {

    }
}
