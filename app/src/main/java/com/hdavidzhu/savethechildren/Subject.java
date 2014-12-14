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

import com.hdavidzhu.savethechildren.callbacks.SubjectsCallback;

import java.util.List;

public class Subject extends Fragment {

    public String[] items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.training_subject, container, false);
        final ListView listview =(ListView)view.findViewById(R.id.subjects_listview);

        VolleySingleton.getInstance().getSubjects(new SubjectsCallback() {
            @Override
            public void handle(List<String> subjects) {
                items = subjects.toArray(new String[subjects.size()]);
                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(view.getContext(), R.layout.subject_list_item, items);
                listview.setAdapter(adapter);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                Grade chosenGrade = new Grade();
                Bundle bundle = new Bundle();
                bundle.putString("subject", items[i]);
                chosenGrade.setArguments(bundle);

                ft.replace(R.id.main_activity_container, chosenGrade);
                ft.commit();
            }
        });
        return view;
    }
}