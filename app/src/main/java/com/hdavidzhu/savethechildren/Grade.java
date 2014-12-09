package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by casey on 12/4/14.
 */
public class Grade extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.training_grade, container, false);
        ListView listview =(ListView)view.findViewById(R.id.grades_listview);
        String[] items = new String[] {"Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.grade_list_item, items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Module());
                ft.commit();
            }
        });
        return view;
    }
}
