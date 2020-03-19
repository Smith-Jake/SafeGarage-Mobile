package com.quickreports.safegarage_mobile.activity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quickreports.safegarage_mobile.R;
import com.quickreports.safegarage_mobile.models.Alarm;
import com.quickreports.safegarage_mobile.models.Temperature;

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

    public AlarmFragment() {
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

        setupAlarmFragment(view);

        // Don't forget to return the View
        return view;
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

    private void setupAlarmFragment(View view) {
        Log.i(getClass().toString(), "Initializing Alarm Fragment");

        // get the Alarm Gif
        final GifImageView alarmView = view.findViewById(R.id.alarm_gif);

        // Determine which GIF to use and whether to set the Directions text
        if (!(Alarm.isSmokeAlarming || Alarm.isCoAlaraming)) { // both alarms are off
            alarmView.setBackgroundResource(R.drawable.both_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.stop();
        } else if (Alarm.isSmokeAlarming && Alarm.isCoAlaraming) { // both alarms are on
            alarmView.setBackgroundResource(R.drawable.both_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.start();
        } else if (Alarm.isSmokeAlarming) { // only smoke is on
            alarmView.setBackgroundResource(R.drawable.smoke_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.start();
        } else if (Alarm.isCoAlaraming) { // only CO is on
            alarmView.setBackgroundResource(R.drawable.co_alarm);
            GifDrawable alarmAnimation = (GifDrawable)alarmView.getBackground();
            alarmAnimation.start();
        }

        // set temperature
        TextView temperatureData = view.findViewById(R.id.tempLabel);
        temperatureData.setText(Double.toString(Temperature.temperature));

        Log.i(getClass().toString(), "Setting CO Alarm to: " + Alarm.isCoAlaraming);
        Log.i(getClass().toString(), "Setting Smoke Alarm to: " + Alarm.isSmokeAlarming);
        Log.i(getClass().toString(), "Setting Temperature to: " + Temperature.temperature);
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
