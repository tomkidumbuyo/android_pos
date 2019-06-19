package com.example.tra.pos.PurchasesFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.tra.Database.Entities.Items;
import com.example.tra.R;
import com.example.tra.pos.PosActivity;
import com.example.tra.pos.PosViewModel;

import java.util.List;


/**
 * Created by ua on 8/23/2018.
 */

public class PurchaseFragment extends Fragment {

    private PosViewModel posViewModel;

    private static final String TAG = "PurchaseFragment";
    public View view;
    public PosActivity home_activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_purchase,container,false);
        home_activity = getActivity();

        posViewModel = ViewModelProviders.of(this).get(PosViewModel.class);
        posViewModel.getAllItems().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {

            }
        });



        return view;

    }


}
