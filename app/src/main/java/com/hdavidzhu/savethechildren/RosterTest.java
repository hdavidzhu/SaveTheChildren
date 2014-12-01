package com.hdavidzhu.savethechildren;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by casey on 11/28/14.
 */
public class RosterTest extends Fragment{
    Context context;

    public void MyMerp() {
        Log.d("Roster Test", "Merp");

    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.context = activity;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.tab, container, false);
//        TextView textview = (TextView) view.findViewById(R.id.text_view);
//        textview.setText("Yeah what up");
        View view = inflater.inflate(R.layout.rostertest, container, false);
        //now you must initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.list_view);

        //EDITED Code
        String[] items = new String[] {"Item 1", "Item 2", "Item 3"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.rostertest, items);
;

        listview.setAdapter(adapter);
        return view;
    }

}
