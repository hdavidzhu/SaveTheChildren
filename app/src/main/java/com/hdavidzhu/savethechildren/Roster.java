package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Roster extends Fragment{
    MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.roster, container, false);

        //Now you must initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.list_view);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.roster_list_item, MainActivity.names);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activity.switchTutor(MainActivity.names.get(i));
            }
        });

        return view;
    }
}