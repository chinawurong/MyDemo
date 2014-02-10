package com.android.test.database.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static String name = "user.db";
	private static int version = 1;

	public DBHelper(Context context) {
		super(context, name, null, version);
	}

	public void onCreate(SQLiteDatabase database) {
		String sql = "create table user (id integer primary key autoincrement,name varchar(64))";
		database.execSQL(sql);
	}

	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

	}

}
