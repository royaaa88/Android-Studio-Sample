package com.roy.bluetoothcommunication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roy.bluetoothcommunication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab02Fragment extends Fragment {

    public Tab02Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab02,container,false);
    }
}
