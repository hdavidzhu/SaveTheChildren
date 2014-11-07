package com.hdavidzhu.savethechildren;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Form {

    String name;
    String starttime;
    String startdate;
    String endtime;
    String enddate;
    String notes;

    public Form(){}

    public Form(String name, String starttime, String startdate, String endtime, String enddate, String notes){
        this.name = name;
        this.starttime = starttime;
        this.startdate = startdate;
        this.endtime = endtime;
        this.enddate = enddate;
        this.notes = notes;
    }

    public void setName(String name) {this.name = name;}
    public void setStarttime(String starttime) {this.starttime= starttime;}
    public void setStartdate(String startdate) {this.startdate = startdate;}
    public void setEndtime(String endtime) {this.endtime = endtime;}
    public void setEnddate(String enddate) {this.enddate = enddate;}
    public void setNotes(String notes) {this.notes = notes;}


    public String getName() {return name;}
    public String getStarttime() {return starttime;}
    public String getStartdate() {return startdate;}
    public String getEndtime() {return endtime;}
    public String getEnddate() {return enddate;}
    public String getNotes() {return notes;}

    public JSONObject javaToJSONObjectConverter (){
        JSONObject jsonForm = new JSONObject();
        try {
            jsonForm.put("name", this.name);
//            jsonForm.put("starttime", this.starttime);
//            jsonForm.put("startdate", this.startdate);
//            jsonForm.put("endtime", this.endtime);
//            jsonForm.put("enddate", this.enddate);
//            jsonForm.put("notes", this.notes);
        }
        catch (JSONException e) {
            Log.d("Some tag", Log.getStackTraceString(e.getCause().getCause()));

        }
        return jsonForm;
    }
}