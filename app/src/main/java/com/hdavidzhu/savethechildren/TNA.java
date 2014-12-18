package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by pmc on 12/17/14.
 */
public class TNA extends Fragment {
    MainActivity activity;
    Context myContext;
    TNAModelAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
        this.myContext = activity.getApplicationContext();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tna, container, false);
        ListView listView = (ListView)view.findViewById(R.id.tna_list_view);
        // if extending Activity
        //setContentView(R.layout.activity_main);

        // 1. pass context and data to the custom adapter
        adapter = new TNAModelAdapter(this.myContext, generateData());

        // if extending Activity 2. Get ListView from activity_main.xml


        // 3. setListAdapter
        listView.setAdapter(adapter);
        return view;
    }

    private ArrayList<TNAModel> generateData(){
        ArrayList<TNAModel> models = new ArrayList<TNAModel>();
        models.add(new TNAModel("Group Title"));
        models.add(new TNAModel("Menu Item 1","1"));
        models.add(new TNAModel("Menu Item 2","2"));
        models.add(new TNAModel("Menu Item 3","12"));

        return models;
    }
}