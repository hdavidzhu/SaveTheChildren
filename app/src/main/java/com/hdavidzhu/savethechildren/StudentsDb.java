package com.hdavidzhu.savethechildren;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;

public class StudentsDb {

    Manager manager;
    Context context;
    Database database;
    String dbName = "studentslist";

    private static final String TAG = "Couch";

    public StudentsDb (Context context) throws CouchbaseLiteException, IOException {
        this.context = context;
    }

    public void setUpDb () {
        try {
            manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS);
            Log.d(TAG, "Manager created");
        } catch (IOException e) {
            Log.e(TAG, "Cannot create manager object");
            return;
        }

        if (!Manager.isValidDatabaseName(dbName)) {
            Log.e(TAG, "Bad database name");
            return;
        }

        // create a new database
        try {
            database = manager.getDatabase(dbName);
            Log.d (TAG, "Database created");
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot get database");
            return;
        }
    }
}