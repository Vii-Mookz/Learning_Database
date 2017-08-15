package com.example.vipavee.learning_database.data;

import android.provider.BaseColumns;

/**
 * Created by Vipavee on 15/08/2017.
 */

public final class TodosContract {
    public static final class TodosEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "todo";
        //column (field) names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_EXPIRED = "expired";
        public static final String COLUMN_DONE = "done";
        public static final String COLUMN_CATEGORY = "category";
    }

    public static final class CategoriesEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "categories";
        //column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION = "description";
    }
}