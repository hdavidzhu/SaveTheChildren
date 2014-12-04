package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
public class Tutor extends Fragment{
    Context context;

    //this fragment will display the name and then the list of things they need training in


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tutor, container, false);
        //now you must initialize your list view
        ListView listview = (ListView)view.findViewById(R.id.training_list);

        String[] items = new String[] {"Fake Module 1 Here", "Fake Module 2 Here", "Fake Module 3 Here"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.tutor_list_item, items);
        listview.setAdapter(adapter);

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Subject());
                ft.commit();
            }
        });


        return view;
    }

}
