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

import com.hdavidzhu.savethechildren.callbacks.SubjectsCallback;

import java.util.List;

public class Subject extends Fragment {

    public String[] items;

    // The onCreate method for this class takes in the Layout, the View, and the Bundle that contains variables we need
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.training_subject, container, false);
        final ListView listview =(ListView)view.findViewById(R.id.subjects_listview);

        //Get instance of volley singleton and the getSubjects method that it defines
        VolleySingleton.getInstance().getSubjects(new SubjectsCallback() {
            @Override
            //When VolleySingleton calls callback.handle this method can process the list that is passed through the callback
            public void handle(List<String> subjects) {
                items = subjects.toArray(new String[subjects.size()]);
                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(view.getContext(), R.layout.subject_list_item, items);
                listview.setAdapter(adapter);
            }
        });

        // This onClick listener takes in the adapter view and changes the fragment to the fragment that the user clicks on
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                Grade chosenGrade = new Grade();
                Bundle bundle = new Bundle();
                bundle.putString("subject", items[i]);
                chosenGrade.setArguments(bundle);

                ft.replace(R.id.main_activity_container, chosenGrade);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}