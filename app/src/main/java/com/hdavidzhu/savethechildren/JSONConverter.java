package com.hdavidzhu.savethechildren;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class JSONConverter extends Fragment {
    //private RecordTutorSessionActivity fragmentTabHost;
//
//
//    StudentsDb studentsDb;
//    private static final String TAG = "Couch";
//
//    View.OnClickListener submitButtonListener;


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