package com.hdavidzhu.savethechildren;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import com.example.android.supportv4.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.hdavidzhu.savethechildren.TabHelper.TabHelper;

import org.json.JSONObject;

public class RecordTutorSessionActivity extends Fragment {
    private RecordTutorSessionActivity fragmentTabHost;


    EditText nameEditText;
    EditText notesEditText;
    TimePicker timePicker;
    DatePicker datePicker;
    Button submitButton;

    StudentsDb studentsDb;
    private static final String TAG = "Couch";

    View.OnClickListener submitButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_tutor_session);

//        TabHelper tabHelper = TabHelper.createInstance(context);
//        tabHelper.setUp();
        fragmentTabHost = (RecordTutorSessionActivity)findViewById(android.R.id.tabhost);
        fragmentTabHost.setUp(this, getSupportFragmentManager(), R.id.realtabcontent);

        context = getApplicationContext();

        nameEditText = (EditText) findViewById(R.id.et_name);
        notesEditText = (EditText) findViewById(R.id.et_notes);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        submitButton = (Button) findViewById(R.id.bt_submit);

        try {
            studentsDb = new StudentsDb(context);
            studentsDb.setUpDb();
        } catch (CouchbaseLiteException c) {
            Log.d("Couch", "Did not initiate database.");
            return;
        } catch (IOException e ) {
            Log.d("Couch", "Did not initiate database.");
            return;
        }

        submitButtonListener = new View.OnClickListener() {
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
                postNewForm(context, jsonForm);

                Document formDocument = studentsDb.database.createDocument();

                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> mapForm = form.toHashMapConverter();
                    formDocument.putProperties(mapForm);
                    Log.d (TAG, "Document written to database named " + studentsDb.dbName
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
        };

        submitButton.setOnClickListener(submitButtonListener);

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

    }

    public void postNewForm(Context context, JSONObject jsonForm){
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.56.101:3000/api/tests";

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonForm,
                new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("onResponse", "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", "Posting failed.");
            }
        });

        // Log.d("Request in add post", "printing request");
        // System.out.println(jsonRequest);

        queue.add(jsonRequest);

        // Log.d("Printing the queue", "queue");
        // System.out.println(jsonRequest);
    }
}