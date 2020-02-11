package com.quickreports.safegarage_mobile.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickreports.safegarage_mobile.R;
import com.quickreports.safegarage_mobile.models.Alarm;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlarmFragment.OnAlarmFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmFragment extends Fragment {
    private OnAlarmFragmentInteractionListener mListener;
    private Alarm alarm;

    public AlarmFragment() {
        // Create the Alarm -- assume the alarms to be not going off
        alarm = new Alarm(false, false);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AlarmFragment.
     */
    public static AlarmFragment newInstance() {
        AlarmFragment fragment = new AlarmFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        // Setup the Alarm GIF and text
        initialize(view);

        // Don't forget to return the View
        return view;
    }

    private void initialize(View view) {
        Log.i(getClass().toString(), "Initializing Alarm Fragment");

        final GifImageView alarmView = view.findViewById(R.id.alarm_view);
        boolean isSmokeAlarming = alarm.isSmokeAlarming();
        boolean isCoAlarming = alarm.isCoAlaraming();

        // Determine which GIF to use and whether to set the Directions text
        if (!(isSmokeAlarming || isCoAlarming)) { // both alarms are off
            alarmView.setBackgroundResource(R.drawable.both_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.stop();
        } else if (isSmokeAlarming && isCoAlarming) { // both alarms are on
            alarmView.setBackgroundResource(R.drawable.both_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.start();
        } else if (isSmokeAlarming) { // only smoke is on
            alarmView.setBackgroundResource(R.drawable.smoke_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.start();
        } else if (isCoAlarming) { // only CO is on
            alarmView.setBackgroundResource(R.drawable.co_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.start();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAlarmFragmentInteractionListener) {
            mListener = (OnAlarmFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAlarmFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAlarmFragmentInteraction(Uri uri);
    }
}
