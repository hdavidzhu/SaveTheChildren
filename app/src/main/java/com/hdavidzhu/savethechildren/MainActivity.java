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

import java.util.ArrayList;
import java.util.List;

//DON'T FORGET TO ADD A BACK TO STACK
public class MainActivity extends Activity{
    //calls record tutor session

//    EditText nameEditText;
//    EditText notesEditText;
//    TimePicker timePicker;
//    DatePicker datePicker;
//    Button submitButton;

//    StudentsDb studentsDb;
//    private static final String TAG = "Couch";
//
//    View.OnClickListener submitButtonListener;
//
    Roster rosterFragment = new Roster();


    public static List <String> tutorItems = new ArrayList<String>(){{
        add("Fake Module 1");
        add("Fake Module 2");
        add("Fake Module 3");
        add("Fake Module 4");
    }};

    public static List <String> names = new ArrayList<String>() {{
        add("Paigers");
        add("David");
        add("Casey");
    }};

    Tutor curTutor;
//    NewTutor tutorFragment = new NewTutor();

    //intiializing tabs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        Log.d("Main Activity", "onCreate");
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
                selectFragment(new Roster());
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
        selectFragment(curTutor);
    }

    public void switchTutor(String newTutor) {
        curTutor = Tutor.newInstance(newTutor);
        goBackToTutor();
    }

}