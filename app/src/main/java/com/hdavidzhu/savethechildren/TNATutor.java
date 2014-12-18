package com.hdavidzhu.savethechildren;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.hdavidzhu.savethechildren.callbacks.TNATutorCallback;

import java.util.List;

public class TNATutor extends Fragment{
    public String[] tutorsNames;

    // The onCreate method for this class takes in the Layout, the View, and the Bundle that contains variables we need
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.tna_tutors, container, false);
        final ListView listview =(ListView)view.findViewById(R.id.grades_listview);

        Bundle bundle = this.getArguments();
        final String myClassModule = bundle.getString("classModule");

        //Get instance of volley singleton and the getGrades method that it defines
        VolleySingleton.getInstance().getTNATutors(myClassModule, new TNATutorCallback() {
            //When VolleySingleton calls callback.handle this method can process the list that is passed through the callback
            @Override
            public void handle(List<String> tutors) {
                tutorsNames = tutors.toArray(new String[tutors.size()]);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(view.getContext(), R.layout.grade_list_item, tutorsNames);
                listview.setAdapter(adapter);
            }

            @Override
            public void pass() {}
        });

        // Button that completes and clears class module requests.
        Button clearButton = (Button) view.findViewById(R.id.bt_submit);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VolleySingleton.getInstance().deleteTNA(myClassModule, new TNATutorCallback() {
                    @Override
                    public void handle(List<String> tutors) {}

                    @Override
                    public void pass() {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.main_activity_container, new TNA());
                        ft.commit();
                    }
                });
            }
        });
        return view;
    }
}