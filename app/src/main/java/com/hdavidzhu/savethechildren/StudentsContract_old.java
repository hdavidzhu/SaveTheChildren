//package com.hdavidzhu.savethechildren;
//
//        import android.provider.BaseColumns;
//
//public final class StudentsContract {
//
//    // Empty constructor to prevent accidental instantiation.
//    public StudentsContract(){}
//
//    private static final String TEXT_TYPE = " TEXT";
//    private static final String COMMA_SEP = ",";
//
//    // Inner class that defines the table contents
//    public static abstract class StudentEntry implements BaseColumns {
//        public static final String TABLE_NAME = "student";
//        public static final String COLUMN_NAME_ENTRY_ID = "studentid";
//        public static final String COLUMN_NAME_TITLE = "title";
//        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
//
//        private static final String SQL_CREATE_ENTRIES =
//                "CREATE TABLE " + TABLE_NAME + " (" +
//                        StudentEntry._ID + " INTEGER PRIMARY KEY," +
//                        StudentEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
//                        StudentEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
//                        " )";
//
//        private static final String SQL_DELETE_ENTRIES =
//                "DROP TABLE IF EXISTS " + TABLE_NAME;
//    }
//}