package com.example.vipavee.learning_database.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vipavee on 15/08/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "categories.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CATEGORIES_CREATE=
            "CREATE TABLE " + TodosContract.CategoriesEntry.TABLE_NAME + " (" +
                    TodosContract.CategoriesEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodosContract.CategoriesEntry.COLUMN_DESCRIPTION + " TEXT " +
                    ")";
    private static final String TABLE_TODOS_CREATE =
            "CREATE TABLE " + TodosContract.TodosEntry.TABLE_NAME + " (" +
                    TodosContract.TodosEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodosContract.TodosEntry.COLUMN_TEXT + " TEXT, " +
                    TodosContract.TodosEntry.COLUMN_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    TodosContract.TodosEntry.COLUMN_EXPIRED + " TEXT, " +
                    TodosContract.TodosEntry.COLUMN_DONE + " INTEGER, " +
                    TodosContract.TodosEntry.COLUMN_CATEGORY + " INTEGER, " +
                    " FOREIGN KEY("+ TodosContract.TodosEntry.COLUMN_CATEGORY + ") REFERENCES " +
                    TodosContract.CategoriesEntry.TABLE_NAME +
                    "(" + TodosContract.CategoriesEntry._ID +") " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CATEGORIES_CREATE);
        db.execSQL(TABLE_TODOS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodosContract.TodosEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TodosContract.CategoriesEntry.TABLE_NAME);
        onCreate(db);
    }
}

