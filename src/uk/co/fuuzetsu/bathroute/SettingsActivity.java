package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final ListView lv = (ListView) findViewById(R.id.settings_list_view);

        String[] values = new String[] { "Image", "Favourites", "Position broadcasting",
                                         "Map", "Storage", "About" };

        ArrayAdapter<String> lvadapter
            = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        lv.setAdapter(lvadapter);
    }
}
