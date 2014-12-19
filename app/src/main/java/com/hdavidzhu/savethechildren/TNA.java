package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hdavidzhu.savethechildren.callbacks.TNACallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TNA extends Fragment {
    MainActivity activity;
    Context myContext;
    TNAModelAdapter adapter;
    ArrayList<TNAModel> models;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
        this.myContext = activity.getApplicationContext();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.tna, container, false);
        final ListView listView = (ListView)view.findViewById(R.id.tna_list_view);
        // if extending Activity
        //setContentView(R.layout.activity_main);

        // pass context and data to the custom adapter

        VolleySingleton.getInstance().getTNA(new TNACallback() {
            @Override
            public void handle(Map TNA) {
                Iterator it = TNA.entrySet().iterator();
                models = new ArrayList<TNAModel>();
                models.add(new TNAModel("Needs Training"));

//                models.add(new TNAModel("Group Title"));
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();
                    models.add(new TNAModel(pairs.getKey().toString(), pairs.getValue().toString()));
                    it.remove(); // avoids a ConcurrentModificationException
                }

                adapter = new TNAModelAdapter(activity.getApplicationContext(), models);

                // setListAdapter
                listView.setAdapter(adapter);
            }
        });

        // This onClick listener takes in the adapter view and changes the fragment to the fragment that the user clicks on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                TNATutor chosenModule = new TNATutor();
                Bundle bundle = new Bundle();
                bundle.putString("classModule", models.get(i).getTitle());
                chosenModule.setArguments(bundle);

                ft.replace(R.id.main_activity_container, chosenModule);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

//    private ArrayList<TNAModel> generateData(){
//        ArrayList<TNAModel> models = new ArrayList<TNAModel>();
//        models.add(new TNAModel("Group Title"));
//        models.add(new TNAModel("Menu Item 1","1"));
//        models.add(new TNAModel("Menu Item 2","2"));
//        models.add(new TNAModel("Menu Item 3","12"));
//
//        return models;
//    }
}
