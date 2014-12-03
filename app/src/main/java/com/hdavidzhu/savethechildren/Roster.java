package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by casey on 11/28/14.
 */
public class Roster extends Fragment{
    Context context;

    //this is the teacher roster?

//    public void MyMerp() {
//        Log.d("Roster Test", "Merp");
//
//    }
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
        View view = inflater.inflate(R.layout.roster, container, false);
        //now you must initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.list_view);
        String[] items = new String[] {"Item 1", "Item 2", "Item 3"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.roster_list_item, items);
        listview.setAdapter(adapter);
        return view;
    }

}
