package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by casey on 12/2/14.
 */
public class Modules extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_module, container, false);
        ListView listview =(ListView)view.findViewById(R.id.modules_listview);
        String[] items = new String[] {"Fake Module One", "Fake Module Two", "Fake Module Three", "Fake Module Four", "Fake Module Five"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.module_list_item, items);
        listview.setAdapter(adapter);
        return view;
    }
}
