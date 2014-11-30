package com.hdavidzhu.savethechildren;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity {


    //temporary main menu

    // Items on the menu.
    //String classes[] = { "RecordTutorSessionActivity", "Students" };
    String classes[] = {"MainActivity", "Students" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main Menu", "Getting into Main Menu");
        Log.d("OnCreate", "This is before click");


        setListAdapter(new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("MainMenu", "This is on Click");

        String cheese = classes[position];
        try {
            Class ourClass = Class.forName("com.hdavidzhu.savethechildren." + cheese);
            Intent ourIntent = new Intent(MainMenu.this, ourClass);
            startActivity(ourIntent);

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}