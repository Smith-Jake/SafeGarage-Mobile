package com.quickreports.safegarage_mobile.activity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.quickreports.safegarage_mobile.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PairFragment.OnPairFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PairFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PairFragment extends Fragment {

    private OnPairFragmentInteractionListener mListener;

    public PairFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PairFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PairFragment newInstance() {
        PairFragment fragment = new PairFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_pair, container, false);

        setupPairFragment(view);

        return view;
    }

    private void setupPairFragment(View view) {
        // set the onCheckChanged event to the Switch
        final Switch pairUnpairSwitch = view.findViewById(R.id.pair_switch);
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
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPairFragmentInteractionListener) {
            mListener = (OnPairFragmentInteractionListener) context;
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
    public interface OnPairFragmentInteractionListener {
        // TODO: Update argument type and name
        void onPairFragmentInteraction(Uri uri);
    }
}
