package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class PlacesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.TOP);
        tv.setText("Places");

        setContentView(tv);
    }


}
