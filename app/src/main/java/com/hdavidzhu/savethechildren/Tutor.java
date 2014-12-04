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
public class Tutor extends Fragment{
    Context context;

    //this fragment will display the name and then the list of things they need training in


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.tab, container, false);
//        TextView textview = (TextView) view.findViewById(R.id.text_view);
//        textview.setText("Yeah what up");
        View view = inflater.inflate(R.layout.tutor, container, false);
        //now you must initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.training_list);
        String[] items = new String[] {"Fake Module 1", "Fake Module 2", "Fake Module 3", "Fake Module 4"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.tutor_list_item, items);
        listview.setAdapter(adapter);


        return view;
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//    }
}
