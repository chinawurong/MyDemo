package com.android.test.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.android.test.database.dbhelper.DBHelper;

/** 封装对数据库的操作 */
public class UserContentProvider extends ContentProvider {

	private DBHelper dbHelper;
	private final static UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private final static int USER = 1;//单条记录
	private final static int USERS = 2;//多条记录

	static {
		URI_MATCHER.addURI("com.android.test.database.UserContentProvider", "user/#", USER);
		URI_MATCHER.addURI("com.android.test.database.UserContentProvider", "user", USERS);
	}

	public UserContentProvider() {
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = -1;//表示影响数据库的行数
		int flag = URI_MATCHER.match(uri);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		try {
			switch (flag) {
			case USER:
				long id = ContentUris.parseId(uri);
				String whereValue = " id = " + id;
				if (!TextUtils.isEmpty(selection)) {
					whereValue += " and " + selection;
				}
				count = database.delete("user", whereValue, selectionArgs);
				break;
			case USERS:
				count = database.delete("user", selection, selectionArgs);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		int flag = URI_MATCHER.match(uri);
		switch (flag) {
		case USER:
			return " vnd.android.cursor.item/user";
		case USERS:
			return "vnd.android.cursor.dir/users";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri resultUri = null;
		int flag = URI_MATCHER.match(uri);
		switch (flag) {
		case USERS:
			SQLiteDatabase database = dbHelper.getWritableDatabase();
			long id = database.insert("user", null, values);
			resultUri = ContentUris.withAppendedId(uri, id);
			break;
		}
		return resultUri;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		int flag = URI_MATCHER.match(uri);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		try {
			switch (flag) {
			case USER:
				long id = ContentUris.parseId(uri);
				String whereValue = " id = " + id;
				if (!TextUtils.isEmpty(selection)) {
					whereValue += " and " + selection;
				}
				cursor = database.query("user", null, whereValue, selectionArgs, null, null, null);
				break;
			case USERS:
				cursor = database.query("user", null, selection, selectionArgs, null, null, null);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int count = -1;//表示影响数据库的行数
		int flag = URI_MATCHER.match(uri);
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		try {
			switch (flag) {
			case USER:
				long id = ContentUris.parseId(uri);
				String whereValue = " id = " + id;
				if (!TextUtils.isEmpty(selection)) {
					whereValue += " and " + selection;
				}
				count = database.update("user", values, whereValue, selectionArgs);
				break;
			case USERS:
				count = database.update("user", values, selection, selectionArgs);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
