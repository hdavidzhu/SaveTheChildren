package com.hdavidzhu.savethechildren;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Form {

    String name;
    String notes;
    int year;
    int month;
    int day;
    int hour;
    int minute;

    public Form(String name, int year, int month, int day, int hour, int minute, String notes){
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.notes = notes;
    }

    public JSONObject javaToJSONObjectConverter (){
        JSONObject jsonForm = new JSONObject();
        try {
            jsonForm.put("name", this.name);
            jsonForm.put("year", this.year);
            jsonForm.put("month", this.month);
            jsonForm.put("day", this.day);
            jsonForm.put("hour", this.hour);
            jsonForm.put("minute", this.minute);
            jsonForm.put("notes", this.notes);

        }
        catch (JSONException e) {
            Log.d("Some tag", Log.getStackTraceString(e.getCause().getCause()));
        }
        return jsonForm;
    }
}