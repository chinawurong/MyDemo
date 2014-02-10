package com.android.test.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class UserTest extends AndroidTestCase {

	public UserTest() {
		// TODO Auto-generated constructor stub
	}

	public void add() {
		ContentResolver contentResolver = getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "wanglei");
		Uri url = Uri.parse("content://com.android.test.database.UserContentProvider/user");
		Uri resultUri = contentResolver.insert(url, values);
		Log.e("------------>>", resultUri.toString());
	}

	public void delete() {
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri url = Uri.parse("content://com.android.test.database.UserContentProvider/user/1");
		int count = contentResolver.delete(url, null, null);
		Log.e("--------->>", count + "");
	}

	public void update() {
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri url = Uri.parse("content://com.android.test.database.UserContentProvider/user/2");
		ContentValues values = new ContentValues();
		values.put("name", "chunye");
		int count = contentResolver.update(url, values, null, null);
		Log.e("--------->>", count + "");
	}
	
	public void query(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.android.test.database.UserContentProvider/user");
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		while (cursor.moveToNext()) {
			Log.e("------------>>", cursor.getString(cursor.getColumnIndex("name")));
		}
	}
}
