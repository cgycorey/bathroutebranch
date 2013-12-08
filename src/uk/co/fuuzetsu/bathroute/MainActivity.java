package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import android.view.View;
import android.view.ViewGroup;


import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
// import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class MainActivity
    extends FragmentActivity
    implements ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Misc", "Places", "Settings" };


    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        return;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                             .setTabListener(this));
        }


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    actionBar.setSelectedNavigationItem(position);
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                @Override
                public void onPageScrollStateChanged(int arg0) {
                }
            });
    }

    private class TabsPagerAdapter extends FragmentPagerAdapter {

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {

            switch (index) {
            case 0:
                return new MiscActivity();
            case 1:
                return new PlacesActivity();
            case 2:
                return new SettingsActivity();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
