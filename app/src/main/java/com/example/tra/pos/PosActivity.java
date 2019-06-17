package com.example.tra.pos;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;


import com.example.tra.Database.Entities.Items;
import com.example.tra.R;
import com.example.tra.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PosActivity extends AppCompatActivity {

    private PosViewModel posViewModel;

    private ViewPager mViewPager;
    private  SectionsPageAdapter mSectionsPageAdapter;

    private SalesFragment salesFragment = new SalesFragment();
    private PurchaseFragment purchaseFragment = new PurchaseFragment();

    TabLayout tabLayout;
    Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos);

        posViewModel = ViewModelProviders.of(this).get(PosViewModel.class);
        posViewModel.getAllItems().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {

            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_menu);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(thisActivity, R.color.black);
                        //tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
//                        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "paypalsansbig_light.tff");
//                        TextView text = (TextView) tab.getCustomView();
//                        text.setTypeface(font, Typeface.BOLD);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(thisActivity, R.color.grey);
                        //tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

//                        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "paypalsansbig_regular.tff");
//                        TextView text = (TextView) tab.getCustomView();
//                        text.setTypeface(font, Typeface.BOLD);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_music);
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_album);

        tabLayout.getTabAt(0).setText("SALE");
        tabLayout.getTabAt(1).setText("PURCHASE");

       /* tabLayout.newTab().setText("Sales");
        tabLayout.newTab().setText("Purchases");*/


        //tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        //tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#95a5a6"), PorterDuff.Mode.SRC_ATOP);

        thisActivity = this;


    }



    private void setupViewPager(ViewPager viewPager){

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(salesFragment);
        adapter.addFragment(purchaseFragment);

        // adapter.addFragment(searchFragment);
        viewPager.setAdapter(adapter);

    }

    static class ItemsListAdapter extends BaseAdapter {

        private Context context; //context
        private ArrayList<Items> items;

        ItemsListAdapter(Context context, ArrayList<Items> items){
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size(); //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return items.get(position); //returns list item at the specified position
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.list_pos_items, parent, false);
            }

            return convertView;

        }
    }

}
