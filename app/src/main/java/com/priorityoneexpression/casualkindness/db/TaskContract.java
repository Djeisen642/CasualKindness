package com.priorityoneexpression.casualkindness.db;

import android.provider.BaseColumns;

/**
 * Created by Jason on 2/9/2015.
 */
public class TaskContract {
    public static final String DB_NAME = "com.priorityoneexpression.casualkindness.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns {
		public static final String DESCRIPTION = "task_description";
        public static final String NAME = "task_name";
        public static final String _ID = BaseColumns._ID;
    }
}
