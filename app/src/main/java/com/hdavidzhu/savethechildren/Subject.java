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
 * Created by casey on 12/3/14.
 */
public class Subject extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.training_subject, container, false);
        ListView listview =(ListView)view.findViewById(R.id.subjects_listview);
        String[] items = new String[] {"Fake Subject: Math", "Fake Subject: Language", "Fake Subject: Hindi", "Fake Subject: Life", "Fake Subject:Something Else"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.subject_list_item, items);
        listview.setAdapter(adapter);
        return view;

    }
}
