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

/**
 * Created by casey on 11/20/14.
 */

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


        //final Context context = getApplicationContext();
        //final RecordTutorSessionActivity newActivity = new RecordTutorSessionActivity();

//        nameEditText = (EditText) findViewById(R.id.et_name);
//        notesEditText = (EditText) findViewById(R.id.et_notes);
//        timePicker = (TimePicker) findViewById(R.id.timePicker);
//        datePicker = (DatePicker) findViewById(R.id.datePicker);
//        submitButton = (Button) findViewById(R.id.bt_submit);

//        try {
//            studentsDb = new StudentsDb(context);
//            studentsDb.setUpDb();
//        } catch (CouchbaseLiteException c) {
//            Log.d("Couch", "Did not initiate database.");
//            return;
//        } catch (IOException e ) {
//            Log.d("Couch", "Did not initiate database.");
//            return;
//        }

//        submitButtonListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Form form = new Form(
//                        nameEditText.getText().toString(),
//                        datePicker.getYear(),
//                        datePicker.getMonth(),
//                        datePicker.getDayOfMonth(),
//                        timePicker.getCurrentHour(),
//                        timePicker.getCurrentMinute(),
//                        notesEditText.getText().toString());
//
//                JSONObject jsonForm = form.javaToJSONObjectConverter();
//                newActivity.postNewForm(context, jsonForm);
//                //postNewForm(context, jsonForm);
//
//                Document formDocument = studentsDb.database.createDocument();
//
//                try {
//                    @SuppressWarnings("unchecked")
//                    Map<String, Object> mapForm = form.toHashMapConverter();
//                    formDocument.putProperties(mapForm);
//                    Log.d (TAG, "Document written to database named " + studentsDb.dbName
//                            + " with ID = " + formDocument.getId());
//
//                    // save the ID of the new document
//                    String docID = formDocument.getId();
//                    // retrieve the document from the database
//                    Document retrievedDocument = studentsDb.database.getDocument(docID);
//                    // display the retrieved document
//                    Log.d(TAG, "retrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));
//
//                    // Display number of files in document.
//                    Log.d(TAG, String.valueOf(studentsDb.database.getDocumentCount()));
//
//                } catch (CouchbaseLiteException e) {
//                    Log.e(TAG, "Cannot write document to database", e);
//                }
//            }
//        };
//        Log.d("MainActivity", "Before OnClickListener");
//        submitButton.setOnClickListener(submitButtonListener);
//        Log.d("MainActivity", "AfterOnClickListener");
//        Query query = studentsDb.database.createAllDocumentsQuery();
//        query.setLimit(10);
//        query.setDescending(true);
//
//        try {
//            QueryEnumerator rowEnum = query.run();
//            for (Iterator<QueryRow> it = rowEnum;
//                 it.hasNext();) {
//                QueryRow row = it.next();
//                Log.d("Document ID:", row.getDocumentId());
//
//            }
//        } catch (CouchbaseLiteException e) {
//            e.printStackTrace();
//        }

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
            case R.id.tutor:
                selectFragment(new Tutor());
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

}