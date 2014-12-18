package com.hdavidzhu.savethechildren;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hdavidzhu.savethechildren.callbacks.TutorsCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{

    public static List <String> tutorItems = new ArrayList<String>(){{
        // This is the array that holds the class modules that each individual tutor feels they need training.
        // This is a static list, therefore there are no outputs, but this array can get longer by calling this Array and adding to it.
    }};

    public static List <String> names = new ArrayList<String>() {{
        // This array holds the tutor names that will propagate the tutor Roster.
        // This is a static list, therefore no outputs.
    }};
    public static Tutor curTutor; //Static method, returns nothing.
    // Makes an instance of Tutor named cuTutor to track modules they need training.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inputs: None.
        //Outputs: None
        //First Method called when start up. Method sets up tabs ands calls New Tutor page
        setContentView(R.layout.main_activity); //Set the layout of the MainActivity page
        setupTabs(); //call to the function setupTabs below.
        FragmentTransaction ft = getFragmentManager().beginTransaction(); //initialize fragment manager
        ft.add(R.id.main_activity_container, new NewTutor()); //adds a fragment to the screen.
        //Notice this Fragment Transaction adds the fragment NewTutor to the container.
        // The first time Fragment transaction is called, a fragment needs to be added to the container.
        // Every time afterwards, the fragment manager replaces the current fragment.
        ft.commit(); //commit fragment changes.

        // Save the names of the tutors after a GET Request is sent to the server asking for the names.
        VolleySingleton.getInstance().getTutors(new TutorsCallback() {
            // Callbacks handle the information received by the phone from the server.
            @Override
            public void handle(List<String> tutors) {
                //Inputs: List of strings of tutors.
                //Outputs: None
                names = tutors; //tutor strings from the database populates the names Array defined in Main Activity
            }
        });
    }

    private void setupTabs(){
        //Inputs: None
        //Outputs: None

        ActionBar actionBar = getActionBar(); //acquires the state of the action.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); //Android Navigation Mode for tab selection
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Input: the Action Bar Menu
        //Output:Inflated Menu
        // This method adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Input: Item selected
        //Output: false until item is selected
        // This method handles the action bar item clicks as long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); //gets id for the action bar item clicked

        switch (id) {
            case R.id.new_entry: //if the new entry button is clicked, call select Fragment method and pass in an instance of NewTutor.
                switchFragment(new NewTutor());
                return true;
            case R.id.roster: //if the roster button is clicked, call the select Fragment method and pass in an instance of Roster

                VolleySingleton.getInstance().getTutors(new TutorsCallback() {
                    @Override
                    public void handle(List<String> tutors) {
                        names = tutors;
                        Log.d("Names", names.toString());

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Roster myRoster = new Roster();
                        ft.replace(R.id.main_activity_container, myRoster);
                        ft.commit();
                    }
                });


                return true;
            case R.id.TNA:
                switchFragment(new TNA());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(Fragment fragment){
        //Input: Fragment to change to
        //Output: None
        //This method switches fragments
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_activity_container, fragment);
        ft.commit();
    }

    public void goBackToTutor() {
        //Input:None
        //Output:None
        //This method bundles curTutor instance, packages its instance to keep track of the modules, and switches fragments.
        Bundle curTutorBundle = new Bundle();
        switchFragment(curTutor); //switches back to old tutor
    }

    public void switchTutor(String newTutor) {
        //Input: tutor Name
        //Output: None
        //This module saves the tutor name for later when we want to add modules to the tutor name.
        curTutor = Tutor.newInstance(newTutor);
        goBackToTutor();  //switches back to old tutor
    }
}