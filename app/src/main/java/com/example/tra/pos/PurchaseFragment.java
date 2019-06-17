package com.example.tra.pos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.tra.R;


/**
 * Created by ua on 8/23/2018.
 */

public class PurchaseFragment extends Fragment {

    private static final String TAG = "PurchaseFragment";
    public View view;
    public PosActivity home_activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_purchase,container,false);
        home_activity = (PosActivity) getActivity();



        return view;

    }


}
