package com.hdavidzhu.savethechildren;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Form {

    String name;
    String timestamp;
    String notes;

    public Form(String name, String timestamp, String notes){
        this.name = name;
        this.timestamp = timestamp;
        this.notes = notes;
    }

    public JSONObject javaToJSONObjectConverter (){
        JSONObject jsonForm = new JSONObject();
        try {
            jsonForm.put("name", this.name);
            jsonForm.put("timestamp", this.timestamp);
            jsonForm.put("notes", this.notes);

        }
        catch (JSONException e) {
            Log.d("Some tag", Log.getStackTraceString(e.getCause().getCause()));

        }
        return jsonForm;
    }
}