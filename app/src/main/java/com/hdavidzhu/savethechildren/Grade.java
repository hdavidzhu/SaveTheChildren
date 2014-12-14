package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hdavidzhu.savethechildren.callbacks.GradeCallback;

import java.util.List;

public class Grade extends Fragment {

    public String[] items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.training_grade, container, false);
        final ListView listview =(ListView)view.findViewById(R.id.grades_listview);

        Bundle bundle = this.getArguments();
        final String mySubject = bundle.getString("subject");

        VolleySingleton.getInstance().getGrades(mySubject, new GradeCallback() {
            @Override
            public void handle(List<String> grades) {
                items = grades.toArray(new String[grades.size()]);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(view.getContext(), R.layout.grade_list_item, items);
                listview.setAdapter(adapter);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                Module chosenModule = new Module();
                Bundle bundle = new Bundle();
                bundle.putString("subject", mySubject);
                bundle.putString("grade", items[i]);
                chosenModule.setArguments(bundle);

                ft.replace(R.id.main_activity_container, chosenModule);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}