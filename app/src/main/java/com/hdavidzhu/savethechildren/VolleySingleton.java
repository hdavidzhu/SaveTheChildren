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
import com.hdavidzhu.savethechildren.callbacks.TutorItemsCallback;
import com.hdavidzhu.savethechildren.callbacks.TutorsCallback;

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

    public JSONObject getClassModules(final String subject,
                                      final String grade,
                                      final ClassModuleCallback callback) {
        response = new JSONObject();
        String url = "http://192.168.56.101:3000/subject/" + subject + "/" + grade;

        final JsonObjectRequest classModuleRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject serverResponse) {
                        try {
                            response = serverResponse;

                            List<String> classModulesList = new ArrayList<String>();
                            JSONArray classModulesArray = response.getJSONArray("grade_info");
                            for(int i = 0 ; i < classModulesArray.length() ; i++){
                                classModulesList.add(classModulesArray.getJSONObject(i).getString("name"));
                            }

                            java.util.Collections.sort(classModulesList);

                            // TODO Add commands that allow this information to be relayed back to the server.

                            callback.handle(classModulesList);

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

        queue.add(classModuleRequest);

        return response;
    }

    public JSONObject getTutors(final TutorsCallback callback) {
        response = new JSONObject();
        String url = "http://192.168.56.101:3000/teachers";

        final JsonObjectRequest tutorsRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject serverResponse) {
                        try {
                            response = serverResponse;
                            List<String> tutorsList = new ArrayList<String>();
                            JSONArray tutorsArray = response.getJSONArray("teachers");
                            for(int i = 0 ; i < tutorsArray.length() ; i++){
                                tutorsList.add(tutorsArray.getString(i));
                            }

                            callback.handle(tutorsList);
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

        queue.add(tutorsRequest);

        return response;
    }

    public JSONObject getTutorItems(String teacher, final TutorItemsCallback callback) {
        response = new JSONObject();
        teacher = teacher.replaceAll(" ", "%20");
        String url = "http://192.168.56.101:3000/teacher/" + teacher;
        Log.d("URL", url);

        final JsonObjectRequest tutorItemsRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject serverResponse) {
                        try {
                            response = serverResponse;
                            List<String> tutorItemsList = new ArrayList<String>();
                            JSONArray tutorItemsArray = response.getJSONArray("help");
                            for(int i = 0 ; i < tutorItemsArray.length() ; i++){
                                tutorItemsList.add(tutorItemsArray.getString(i));
                            }

                            callback.handle(tutorItemsList);
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
        queue.add(tutorItemsRequest);
        return response;
    }

    public void setTutorItem(String teacher, String class)
}