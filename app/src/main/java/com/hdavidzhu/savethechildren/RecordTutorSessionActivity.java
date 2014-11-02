package com.hdavidzhu.savethechildren;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecordTutorSessionActivity extends Activity {

    EditText nameEditText;
    EditText startTimeEditText;
    EditText startDateEditText;
    EditText endTimeEditText;
    EditText endDateEditText;
    Button submitButton;

    View.OnClickListener submitButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_tutor_session);

        nameEditText = (EditText) findViewById(R.id.et_name);
        startTimeEditText = (EditText) findViewById(R.id.et_start_time);
        startDateEditText = (EditText) findViewById(R.id.et_start_date);
        endTimeEditText = (EditText) findViewById(R.id.et_end_time);
        endDateEditText = (EditText) findViewById(R.id.et_end_date);
        submitButton = (Button) findViewById(R.id.bt_submit);

        // Instantiate the RequestQueue.

        submitButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("something","something");
            }
        };

        submitButton.setOnClickListener(submitButtonListener);
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