package com.quickreports.safegarage_mobile.activity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quickreports.safegarage_mobile.BackEnd;
import com.quickreports.safegarage_mobile.R;
import com.quickreports.safegarage_mobile.activity.MainActivity;
import com.quickreports.safegarage_mobile.models.CloseTime;
import com.quickreports.safegarage_mobile.models.StatusResponse;
import com.quickreports.safegarage_mobile.rest.interfaces.apiError;
import com.quickreports.safegarage_mobile.rest.interfaces.apiSuccess;

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
    private View view;

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
        view = inflater.inflate(R.layout.fragment_time, container, false);

        // Get the Closing Time Picker, Button, and Switch
//        setupTimeFragment(view);

        // Don't forget to return the view
        return view;
    }

    public void setupTimeFragment() {
        Log.i(getClass().toString(), "Initializing Time Fragment");

        TextView closingTimeTextView = view.findViewById(R.id.closeTimeData);
        Switch closingTimeSwitch = view.findViewById(R.id.closeTimeSwitch);
        TimePicker closingTimePicker = view.findViewById(R.id.closingTimePicker);
        Button closingTimeButton = view.findViewById(R.id.closingTimeButton);

        // Setup the Closing Time switch's onChecked event
        closingTimeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            closingTimeButton.setEnabled(isChecked);

            if (!isChecked) {
                // TODO: Tell the Server to NOT set a closing time
                BackEnd.setClosingTime(new apiError(){
                    @Override
                    public void run(String error) {

                    }
                }, new apiSuccess(){
                    @Override
                    public void run(StatusResponse input) {
                        CloseTime.time = "Not Set";
                        closingTimeTextView.setText(CloseTime.time);
                        Log.i(getClass().toString(), "Disabling Automatic Closing Time");
                    }
                }, "24:00");
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
                String closingTime = CloseTime.closingTimeToString(hour, minute);

                // TODO: Tell the Server to actually set the closing time
                BackEnd.setClosingTime(new apiError(){
                    @Override
                    public void run(String error) {

                    }
                }, new apiSuccess(){
                    @Override
                    public void run(StatusResponse input) {
                        CloseTime.time = closingTime;
                        closingTimeTextView.setText(CloseTime.time);
                        Log.i(getClass().toString(), "Setting Closing Time to: " + CloseTime.time);
                    }
                }, closingTime);
            }
        });

        // 24:00 means that the closing time is not set
        if (CloseTime.time.equals("24:00")) {
            closingTimeTextView.setText("Not Set");
            closingTimeSwitch.setChecked(false);
        }
        else {
            closingTimeTextView.setText(CloseTime.time);
            closingTimeSwitch.setChecked(true);
        }

        Log.i(getClass().toString(), "Setting Closing Time to: " + CloseTime.time);
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
