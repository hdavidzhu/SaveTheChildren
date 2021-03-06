package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hdavidzhu.savethechildren.callbacks.PostTutorCallback;
import com.hdavidzhu.savethechildren.callbacks.TutorsCallback;

import java.util.Collections;
import java.util.List;

public class NewTutor extends Fragment {
    Context context; //initialize context


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Input: The Main Activity activity
        //Output: None
        //This method is run when the fragment and the Main Activity first meet.
        // It informs the Main Activity that this fragment belongs to the activity.
        this.context = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Input: inflated layout, view container, and bundled instances saved by previous fragments
        //Output: view
        //This method runs every time NewTutor is called.

        View view = inflater.inflate(R.layout.adding_new_tutor, container, false); //inflate the view with this container
        //view.setBackgroundColor(Color.rgb(20, 200, 300));

        final EditText tutorNameEdit = (EditText) view.findViewById(R.id.insert_name);
        view.findViewById(R.id.submit_tutor_name_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { //onClickListener, Listens for button press
                String name  = tutorNameEdit.getText().toString();
                if (name.length() == 0) return;
                MainActivity.names.add(name); //add the string typed by the user to the Main Activity names array.
                tutorNameEdit.setText("");

                // POST the tutor, and then switch to a new Fragment when the tutor is successfully registered on the database.
                VolleySingleton.getInstance().postTutor(name, new PostTutorCallback() {
                    @Override
                    public void handle() {
                        VolleySingleton.getInstance().getTutors(new TutorsCallback() {
                            @Override
                            public void handle(List<String> tutors) throws InterruptedException {
                                Collections.sort(tutors);
                                MainActivity.names = tutors;

                                //Replace this NewTutor fragment with a new instance of the Roster fragment.
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.main_activity_container, new Roster());
                                ft.commit();
                            }
                        });
                    }
                });
                // MainActivity.names.add(name);
                tutorNameEdit.setText("");
            }
        });
        return view;
    }
}