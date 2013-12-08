package uk.co.fuuzetsu.bathroute;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MiscActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.misc, container, false);

        return rootView;
    }


    // @Override
    // public void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.misc);

    //     final ListView lv = (ListView) findViewById(R.id.misc_list_view);

    //     String[] values = new String[] { "Friends", "Computers", "Mailroom", "Toilets" };

    //     ArrayAdapter<String> lvadapter
    //         = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

    //     lv.setAdapter(lvadapter);
    // }


}
