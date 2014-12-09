package com.hdavidzhu.savethechildren;

import android.app.DownloadManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by casey on 12/3/14.
 */
public class Subject extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.training_subject, container, false);
        ListView listview =(ListView)view.findViewById(R.id.subjects_listview);
        String[] items = new String[] {"Fake Subject: Math", "Fake Subject: Language", "Fake Subject: Hindi", "Fake Subject: Life", "Fake Subject:Something Else"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(view.getContext(), R.layout.subject_list_item, items);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // JSONObject subjects = getSubjects();
                // Log.d("Subjects", subjects.toString());

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_activity_container, new Grade());
                ft.commit();
            }
        });

        return view;

    }

//    JSONObject response = new JSONObject();
//
//    public JSONObject getSubjects() {
//        final RequestQueue queue = Volley.newRequestQueue(getActivity());
//        String url = "http://10.7.88.28:3000/subjects";
//
//        final JsonObjectRequest subjectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject serverResponse) {
//                        try{
//                            response = serverResponse;
//                            VolleyLog.v("Response:%n %s", serverResponse.toString(4));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("onErrorResponse", "Get failed");
//            }
//        });
//
//        queue.add(subjectRequest);
//
//        return response;
//    }
}
