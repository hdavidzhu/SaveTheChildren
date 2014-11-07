package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecordTutorSessionActivity extends Activity {

    Context context;

    EditText nameEditText;
    EditText startTimeEditText;
    EditText startDateEditText;
    EditText endTimeEditText;
    EditText endDateEditText;
    EditText notesEditText;
    Button submitButton;

    View.OnClickListener submitButtonListener;

    JSONObject jsonForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_tutor_session);

        context = getApplicationContext();

        nameEditText = (EditText) findViewById(R.id.et_name);
        startTimeEditText = (EditText) findViewById(R.id.et_start_time);
        startDateEditText = (EditText) findViewById(R.id.et_start_date);
        endTimeEditText = (EditText) findViewById(R.id.et_end_time);
        endDateEditText = (EditText) findViewById(R.id.et_end_date);
        notesEditText = (EditText) findViewById(R.id.et_additional_notes);
        submitButton = (Button) findViewById(R.id.bt_submit);

        submitButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Form form = new Form(
                        nameEditText.getText().toString(),
                        startTimeEditText.getText().toString(),
                        startDateEditText.getText().toString(),
                        endTimeEditText.getText().toString(),
                        endDateEditText.getText().toString(),
                        notesEditText.getText().toString()
                );

                jsonForm = form.javaToJSONObjectConverter();
                Log.d("something","something");
                postNewForm(context, form);
                Log.d("something","something2");

            }
        };

        submitButton.setOnClickListener(submitButtonListener);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void postNewForm(Context context, final Form form){
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.56.101:3000/api/tests";

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonForm, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("onResponse", "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("David still stinks");
                Log.d("This is a tag", "David still stinks");
            }
        });

        // Posting queue.
//        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("onResponse", "Success");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.print("David stinks. just kidding");
//                Log.d("This is Log.d", "David stinks. just kidding");
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams () throws AuthFailureError{
//                Map<String, String> formMap = new HashMap<String, String>();
//                formMap.put("name", form.name);
//                formMap.put("starttime", form.starttime);
//                formMap.put("startdate", form.startdate);
//                formMap.put("endtime", form.endtime);
//                formMap.put("enddate", form.enddate);
//                formMap.put("notes", form.notes);
//                return formMap;
//            }
//        };

        Log.d("Request in add post", "printing request");
        System.out.println(jsonRequest);
        queue.add(jsonRequest);
        Log.d("Printing the queue", "queue");
        System.out.println(jsonRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}