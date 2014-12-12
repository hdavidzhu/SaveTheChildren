package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by casey on 11/28/14.
 */
public class Roster extends Fragment{
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
//        View view = inflater.inflate(R.layout.tab, container, false);
//        TextView textview = (TextView) view.findViewById(R.id.text_view);
//        textview.setText("Yeah what up");
        View view = inflater.inflate(R.layout.roster, container, false);
        //now you must initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.list_view);
//        final String[]  = new String[] {"Paigers", "David", "Casey"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.roster_list_item, MainActivity.names);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // JSONObject subjects = getSubjects();
                // Log.d("Subjects", subjects.toString());
                Tutor fragment = new Tutor();
                Bundle bundle = new Bundle();
                bundle.putString("name", MainActivity.names.get(i));
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        return view;
    }

}
