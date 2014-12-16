package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by casey on 12/15/14.
 */
public class newTutor extends Fragment {
    Context context;
    EditText tutorNameEdit;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.adding_new_tutor, container, false);
        final EditText tutorNameEdit = (EditText) view.findViewById(R.id.insert_name);
        view.findViewById(R.id.submit_tutor_name_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name  = tutorNameEdit.getText().toString();
                MainActivity.names.add(name);
                tutorNameEdit.setText("");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Roster());
                ft.commit();

            }

        });


        return view;
    }
}
