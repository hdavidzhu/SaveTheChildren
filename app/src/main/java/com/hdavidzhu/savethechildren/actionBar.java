package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.hdavidzhu.savethechildren.callbacks.TutorsCallback;

import java.util.List;

public class actionBar extends Fragment {
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.action_bar, container, false);
        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).switchFragment(new NewTutor());
            }
        });

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleySingleton.getInstance().getTutors(new TutorsCallback() {
                    @Override
                    public void handle(List<String> tutors) {
                        MainActivity.names = tutors;
                        Log.d("Names", MainActivity.names.toString());

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Roster myRoster = new Roster();
                        ft.replace(R.id.main_activity_container, myRoster);
                        ft.commit();
                    }
                });
            }
        });

        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).switchFragment(new TNA());
            }
        });

        return view;
    }
}
