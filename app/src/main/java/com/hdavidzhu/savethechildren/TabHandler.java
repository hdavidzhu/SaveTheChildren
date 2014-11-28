package com.hdavidzhu.savethechildren;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by casey on 11/28/14.
 */
public class TabHandler extends Activity {
    ActionBar.Tab NewEntry, Roster;

    Fragment fragmentNewEntry = new RecordTutorSessionActivity();
    Fragment fragmentRoster = new RosterTest();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_test);//create layout for this

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        NewEntry = actionBar.newTab().setText("1");

        NewEntry.setTabListener(new MyTabListener(fragmentNewEntry));
        Roster.setTabListener(new MyTabListener(fragmentRoster));


        actionBar.addTab(NewEntry);
        actionBar.addTab(Roster);
    }

}
