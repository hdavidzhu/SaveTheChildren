package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by casey on 12/1/14.
 */

//This class creates the the new tutor form for the application
public class NewTutor extends Fragment {
    Context context;
    EditText nameEditText;
    EditText notesEditText;
    TimePicker timePicker;
    DatePicker datePicker;
    Button submitButton;

    StudentsDb studentsDb;
    private static final String TAG = "Couch";

    View.OnClickListener submitButtonListener;

    Roster rosterFragment = new Roster();


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.new_tutor, container, false);
        ListView myListView = (ListView) rootView.findViewById(R.id.list_view);
        final JSONConverter newActivity = new JSONConverter();
        nameEditText = (EditText) rootView.findViewById(R.id.et_name);
        notesEditText = (EditText) rootView.findViewById(R.id.et_notes);
        timePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        datePicker = (DatePicker) rootView.findViewById(R.id.datePicker);
        submitButton = (Button) rootView.findViewById(R.id.bt_submit);

        try {
            studentsDb = new StudentsDb(context);
            studentsDb.setUpDb();
        } catch (CouchbaseLiteException c) {
            Log.d("Couch", "Did not initiate database.");
            return rootView;
        } catch (IOException e) {
            Log.d("Couch", "Did not initiate database.");
            return rootView;
        }


//        submitButtonListener =


        Log.d("MainActivity", "Before OnClickListener");
        rootView.findViewById(R.id.bt_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Form form = new Form(

                        nameEditText.getText().toString(),
                        datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        notesEditText.getText().toString());

                JSONObject jsonForm = form.javaToJSONObjectConverter();
                newActivity.postNewForm(context, jsonForm);
                //postNewForm(context, jsonForm);

                Document formDocument = studentsDb.database.createDocument();

                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> mapForm = form.toHashMapConverter();
                    formDocument.putProperties(mapForm);
                    Log.d(TAG, "Document written to database named " + studentsDb.dbName
                            + " with ID = " + formDocument.getId());

                    // save the ID of the new document
                    String docID = formDocument.getId();
                    // retrieve the document from the database
                    Document retrievedDocument = studentsDb.database.getDocument(docID);
                    // display the retrieved document
                    Log.d(TAG, "retrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));

                    // Display number of files in document.
                    Log.d(TAG, String.valueOf(studentsDb.database.getDocumentCount()));

                } catch (CouchbaseLiteException e) {
                    Log.e(TAG, "Cannot write document to database", e);
                }
            }
        });
        Log.d("MainActivity", "AfterOnClickListener");
        Query query = studentsDb.database.createAllDocumentsQuery();
        query.setLimit(10);
        query.setDescending(true);

        try {
            QueryEnumerator rowEnum = query.run();
            for (Iterator<QueryRow> it = rowEnum;
                 it.hasNext();) {
                QueryRow row = it.next();
                Log.d("Document ID:", row.getDocumentId());

            }
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return rootView;
    }



}
