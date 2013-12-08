package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class PlacesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        final ListView lv = (ListView) findViewById(R.id.places_list_view);

        String[] values = new String[] { "3WN", "Library", "3E", "East Building" };

        ArrayAdapter<String> lvadapter
            = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        lv.setAdapter(lvadapter);
    }
}
