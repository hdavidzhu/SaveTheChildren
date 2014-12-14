package com.hdavidzhu.savethechildren;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hdavidzhu.savethechildren.callbacks.ClassModuleCallback;
import com.hdavidzhu.savethechildren.callbacks.GradeCallback;
import com.hdavidzhu.savethechildren.callbacks.SubjectsCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    JSONObject response;
    static RequestQueue queue;

    public VolleySingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static void init(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
            queue = VolleySingleton.getInstance().getRequestQueue();
        }
    }
    public static VolleySingleton getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public JSONObject getSubjects(final SubjectsCallback callback) {
        response = new JSONObject();
        String url = "http://192.168.56.101:3000/subjects";

        final JsonObjectRequest subjectsRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject serverResponse) {
                        try {
                            response = serverResponse;
                            List<String> subjectList = new ArrayList<String>();
                            JSONArray subjectArray = response.getJSONArray("subjects");
                            for(int i = 0 ; i < subjectArray.length() ; i++){
                                subjectList.add(subjectArray.getString(i));
                            }

                            callback.handle(subjectList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", "Get failed");
                Log.d("onErrorResponse", error.toString());
            }
        });

        queue.add(subjectsRequest);

        return response;
    }

    public JSONObject getGrades(final String subject, final GradeCallback callback) {
        response = new JSONObject();
        String url = "http://192.168.56.101:3000/subject/" + subject;

        final JsonObjectRequest gradesRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject serverResponse) {
                        try {
                            response = serverResponse;
                            List<String> gradesList = new ArrayList<String>();
                            JSONArray gradesArray = response.getJSONArray("subject_info");
                            Log.d("gradeLength", String.valueOf(gradesArray.length()));
                            for(int i = 0 ; i < gradesArray.length() ; i++){
                                gradesList.add(gradesArray.getJSONObject(i).getString("name"));
                            }

                            java.util.Collections.sort(gradesList);

                            callback.handle(gradesList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", "Get failed");
                Log.d("onErrorResponse", error.toString());
            }
        });

        queue.add(gradesRequest);

        return response;
    }

//    public JSONObject getClassModules(final String subject,
//                                      final String grade,
//                                      final ClassModuleCallback callback) {
//        response = new JSONObject();
//        String url = "http://192.168.56.101:3000/subject/" + subject + "/" + grade;
//
//        final JsonObjectRequest classModuleRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject serverResponse) {
//                        try {
//                            response = serverResponse;
//                            List<String> classModulesList = new ArrayList<String>();
//                            JSONArray classModulesArray = response.getJSONArray("subject_info");
//                            Log.d("gradeLength", String.valueOf(gradesArray.length()));
//                            for(int i = 0 ; i < gradesArray.length() ; i++){
//                                gradesList.add(gradesArray.getJSONObject(i).getString("name"));
//                            }
//
//                            java.util.Collections.sort(gradesList);
//
//                            callback.handle(gradesList);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("onErrorResponse", "Get failed");
//                Log.d("onErrorResponse", error.toString());
//            }
//        });
//
//        queue.add(gradesRequest);
//
//        return response;
//    }

}