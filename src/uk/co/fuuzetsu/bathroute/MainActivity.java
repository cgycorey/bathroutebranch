package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        TabSpec miscTab = tabHost.newTabSpec("miscT");
        TabSpec placesTab = tabHost.newTabSpec("placesT");
        TabSpec settingsTab = tabHost.newTabSpec("settingsT");

        miscTab.setIndicator("Misc");
        miscTab.setContent(new Intent(this, MiscActivity.class));

        placesTab.setIndicator("Places");
        placesTab.setContent(new Intent(this, PlacesActivity.class));

        settingsTab.setIndicator("Settings");
        settingsTab.setContent(new Intent(this, SettingsActivity.class));

        tabHost.addTab(miscTab);
        tabHost.addTab(placesTab);
        tabHost.addTab(settingsTab);

        /* Set ‘Places’ tab as the default active one. */
        tabHost.setCurrentTab(1);
    }
}
