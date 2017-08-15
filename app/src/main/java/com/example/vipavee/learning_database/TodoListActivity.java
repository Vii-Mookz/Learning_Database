package com.example.vipavee.learning_database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vipavee.learning_database.data.DatabaseHelper;
import com.example.vipavee.learning_database.data.TodosContract;

public class TodoListActivity extends AppCompatActivity {

    String[] itemname = {
            "Get theatre tickets",
            "Order pizza for tonight",
            "Go to Ratchaburi",
            "Play Video Game at 19.30",
            "Sleep"
    };

    private void readData() {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {TodosContract.TodosEntry.COLUMN_TEXT,
                TodosContract.TodosEntry.COLUMN_CREATED,
                TodosContract.TodosEntry.COLUMN_EXPIRED,
                TodosContract.TodosEntry.COLUMN_DONE,
                TodosContract.TodosEntry.COLUMN_CATEGORY};
        String selection = TodosContract.TodosEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.query(TodosContract.TodosEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        int i = cursor.getCount();
        Log.d("Record Count", String.valueOf((i)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // DatabaseHelper helper = new DatabaseHelper(this);
        //SQLiteDatabase db = helper.getReadableDatabase();
//        CreateTodo();
        readData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv = (ListView) findViewById(R.id.lvTodos);
//adds the custom layout
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.todo_list_item,
                R.id.tvNote, itemname));
//adds the click event to the listView, reading the content
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(TodoListActivity.this, TodoActivity.class);
                String content = (String) lv.getItemAtPosition(pos);
                intent.putExtra("Content", content);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void CreateTodo() {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = "INSERT INTO todo ("
                + TodosContract.TodosEntry.COLUMN_TEXT + ","
                + TodosContract.TodosEntry.COLUMN_CATEGORY + ","
                + TodosContract.TodosEntry.COLUMN_CREATED + ","
                + TodosContract.TodosEntry.COLUMN_EXPIRED + ","
                + TodosContract.TodosEntry.COLUMN_DONE + ")"
                + "VALUES (\"Go to the gym\",1,\"2017-08-14\",\"\",0)";
        db.execSQL(query);

        ContentValues values = new ContentValues();
        values.put(TodosContract.TodosEntry.COLUMN_TEXT, "Call Note");
        values.put(TodosContract.TodosEntry.COLUMN_CATEGORY, 1);
        values.put(TodosContract.TodosEntry.COLUMN_CREATED, "2017-08-15");
        values.put(TodosContract.TodosEntry.COLUMN_DONE, 0);
        long t_id = db.insert(TodosContract.TodosEntry.TABLE_NAME, null, values);

    }
}