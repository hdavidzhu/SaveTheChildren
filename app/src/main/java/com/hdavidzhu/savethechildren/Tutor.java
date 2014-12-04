package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.hdavidzhu.savethechildren.R;

/**
 * Created by casey on 12/2/14.
 */
public class Tutor extends ListFragment implements View.OnClickListener {
    Context context;

    //this fragment will display the name and then the list of things they need training in


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor, container, false);
        //now you must initialize your list view
        ListView listview = (ListView)view.findViewById(R.id.training_list);

        //need an adapter here

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //I think database things happen here.

            }
        });


        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }
}
