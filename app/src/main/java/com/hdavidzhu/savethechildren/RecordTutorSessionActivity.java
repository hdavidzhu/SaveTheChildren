package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RecordTutorSessionActivity extends Activity {

    Context context;

    EditText nameEditText;
    EditText notesEditText;
    TimePicker timePicker;
    DatePicker datePicker;
    Button submitButton;

    View.OnClickListener submitButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_tutor_session);

        context = getApplicationContext();

        nameEditText = (EditText) findViewById(R.id.et_name);
        notesEditText = (EditText) findViewById(R.id.et_notes);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        submitButton = (Button) findViewById(R.id.bt_submit);

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
            }
        };

        submitButton.setOnClickListener(submitButtonListener);

    }

    public void postNewForm(Context context, JSONObject jsonForm){
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(context);
        // String url = "http://192.168.56.101:3001/api/tests";
        String url = "http://192.168.56.101:3001/api/student";


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
            }
        });

        Log.d("Request in add post", "printing request");
        System.out.println(jsonRequest);
        queue.add(jsonRequest);
        Log.d("Printing the queue", "queue");
        System.out.println(jsonRequest);
    }
}