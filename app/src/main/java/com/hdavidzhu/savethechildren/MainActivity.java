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

    Roster rosterFragment = new Roster();

    public static List <String> tutorItems = new ArrayList<String>(){{
        add("Local Tutor Items");
    }};

    public static List <String> names = new ArrayList<String>() {{
        add("Local Tutor");
    }};

    public static Tutor curTutor;

    // Initializing tabs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        setupTabs();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.main_activity_container, new NewTutor());
        ft.commit();

    }

    private void setupTabs(){
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.new_tutor, menu);
        //getMenuInflater().inflate(R.menu.my, menu);
        Log.d("Main Activity", "onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);

        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (rosterFragment.onOptionsItemSelected(item)) {
//            return true;
//        }


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (id) {
            case R.id.new_entry:
//                RecordTutorSessionActivity();
//                MainActivity();
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                selectFragment(new NewTutor());
                return true;
            case R.id.roster:

                VolleySingleton.getInstance().getTutors(new TutorsCallback() {
                    @Override
                    public void handle(List<String> tutors) {
                        names = tutors;

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        Roster myRoster = new Roster();
                        ft.replace(R.id.main_activity_container, myRoster);
                        ft.commit();
                    }
                });

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectFragment(Fragment fragment){
        //add case statement
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_activity_container, fragment);
        ft.commit();
    }

    public void goBackToTutor() {
        Bundle curTutorBundle = new Bundle();
        selectFragment(curTutor);
    }

    public void switchTutor(String newTutor) {
        curTutor = Tutor.newInstance(newTutor);
        goBackToTutor();
    }
}