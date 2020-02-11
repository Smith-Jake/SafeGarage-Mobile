package com.quickreports.safegarage_mobile.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.quickreports.safegarage_mobile.BackEnd;
import com.quickreports.safegarage_mobile.R;
import com.quickreports.safegarage_mobile.Utility;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeFragment.OnTimeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeFragment extends Fragment {

    private OnTimeFragmentInteractionListener mListener;
    private BackEnd server;
    private TimePicker closingTimePicker;
    private Button closingTimeButton;

    public TimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TimeFragment.
     */
    public static TimeFragment newInstance() {
        TimeFragment fragment = new TimeFragment();
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
        View view = inflater.inflate(R.layout.fragment_time, container, false);

        // Get the Closing Time Picker and Button, and Server
        closingTimePicker = view.findViewById(R.id.closingTimePicker);
        closingTimeButton = view.findViewById(R.id.closingTimeButton);
        server = new BackEnd();

        // Setup the Closing Time Picker's onTimeChanged event to handle the enabling the
        // Set Closing Time button
        closingTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                closingTimeButton.setEnabled(true);
            }
        });

        // Setup the Set Closing Time button's onClick event
        closingTimeButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the Hour and Minute
                int hour = closingTimePicker.getHour();
                int minute = closingTimePicker.getMinute();

                // Convert the Hour and Minute to a human-readable String
                String closingTime = Utility.closingTimeToString(hour, minute);
                Log.i(getClass().toString(), "Setting Closing Time to: " + closingTime);

                // Tell the Server to actually set the closing time
                server.setClosingTime(closingTime);

                // Disable the Closing Time button
                closingTimeButton.setEnabled(false);
            }
        });

        // Don't forget to return the view
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTimeFragmentInteractionListener) {
            mListener = (OnTimeFragmentInteractionListener) context;
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
    public interface OnTimeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTimeFragmentInteraction(Uri uri);
    }
}
