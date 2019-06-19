package com.example.tra.pos.SalesFragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tra.Database.Entities.Items;
import com.example.tra.R;
import com.example.tra.pos.PosActivity;
import com.example.tra.pos.PosViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.argb;

/**
 * Created by ua on 8/23/2018.
 */

public class SalesFragment extends Fragment {

    private PosViewModel posViewModel;

    private static final String TAG = "SalesFragment";
    public View view;
    public PosActivity home_activity;

    private Boolean opened = false;

    RelativeLayout selectedItems;
    RelativeLayout availableItems;

    RecyclerView itemsList;
    RecyclerView selectedList;
    GridLayout keypad;

    ImageButton dialPadButton;

    Boolean dialpad_visible = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sales,container,false);
        home_activity = (PosActivity) getActivity();

        posViewModel = ViewModelProviders.of(this).get(PosViewModel.class);


//        Items item1 = new Items("car",5000);
//        itemsArrayList.add(item1);
//
//        Items item2 = new Items("cellphone",600);
//        itemsArrayList.add(item2);

        itemsList = view.findViewById(R.id.items_list);
        itemsList.setLayoutManager(new LinearLayoutManager(home_activity));
        itemsList.setHasFixedSize(true);

        selectedList = view.findViewById(R.id.selected_list);
        selectedList.setLayoutManager(new LinearLayoutManager(home_activity));
        selectedList.setHasFixedSize(true);

        final ItemsListAdapter itemsListAdapter = new ItemsListAdapter(home_activity);
        itemsList.setAdapter(itemsListAdapter);

        SalesItemListAdapter salesItemListAdapter = new SalesItemListAdapter(home_activity);
        selectedList.setAdapter(salesItemListAdapter);

        posViewModel.getAllItems().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {
                itemsListAdapter.setItems(items);
            }
        });



        keypad = view.findViewById(R.id.keypad);






        selectedItems = view.findViewById(R.id.selected_items);
        availableItems = view.findViewById(R.id.available_items);


        view.findViewById(R.id.header_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_sales();
            }
        });

        view.findViewById(R.id.show_available_items).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide_sales();
            }
        });


        // When Checkout is clicked
        view.findViewById(R.id.checkout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        dialPadButton = view.findViewById(R.id.dialpad_button);
        dialPadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dialpad();

            }
        });

        ImageButton hideDialPadButton = view.findViewById(R.id.hide_dialpad);
        hideDialPadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide_dialpad();
            }
        });



        return view;

    }


    private void toggle_sales(){
        if(!opened){
            show_sales();
        } else {
            hide_sales();
        }

    }

    private void show_sales(){

        if(opened == false) {
            selectedItems.setVisibility(View.VISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,
                    0,
                    selectedItems.getHeight(),
                    0);
            animate.setDuration(500);
            animate.setFillAfter(true);
            selectedItems.startAnimation(animate);

            availableItems.setVisibility(View.INVISIBLE);
            TranslateAnimation animate2 = new TranslateAnimation(
                    0,
                    0,
                    0,
                    availableItems.getHeight());
            animate2.setDuration(500);
            animate2.setFillAfter(true);
            availableItems.startAnimation(animate2);
            opened = true;
        }

    }

    private void hide_sales(){
        if(opened == true) {
            selectedItems.setVisibility(View.INVISIBLE);
            TranslateAnimation animate = new TranslateAnimation(
                    0,
                    0,
                    0,
                    selectedItems.getHeight());
            animate.setDuration(500);
            animate.setFillAfter(true);
            selectedItems.startAnimation(animate);

            availableItems.setVisibility(View.VISIBLE);
            TranslateAnimation animate2 = new TranslateAnimation(
                    0,
                    0,
                    availableItems.getHeight(),
                    0);
            animate2.setDuration(500);
            animate2.setFillAfter(true);
            availableItems.startAnimation(animate2);
            opened = false;
        }


    }

    private void show_dialpad(){
        itemsList.setVisibility(View.INVISIBLE);
        keypad.setVisibility(View.VISIBLE);

        RelativeLayout item_search_div = view.findViewById(R.id.item_search_div);
        RelativeLayout keypad_input_div = view.findViewById(R.id.keypad_input_div);

        item_search_div.setVisibility(View.INVISIBLE);
        keypad_input_div.setVisibility(View.VISIBLE);

        dialpad_visible = true;
    }

    private void hide_dialpad(){
        itemsList.setVisibility(View.VISIBLE);
        keypad.setVisibility(View.INVISIBLE);
        dialPadButton.setImageResource(R.drawable.ic_dialpad);

        RelativeLayout item_search_div = view.findViewById(R.id.item_search_div);
        RelativeLayout keypad_input_div = view.findViewById(R.id.keypad_input_div);

        item_search_div.setVisibility(View.VISIBLE);
        keypad_input_div.setVisibility(View.INVISIBLE);

        dialpad_visible = false;
    }


}
