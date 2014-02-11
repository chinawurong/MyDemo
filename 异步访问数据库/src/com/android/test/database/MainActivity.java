package com.android.test.database;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LoaderManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getLoaderManager();
		manager.initLoader(1001, null, callbacks);
	}

	private LoaderCallbacks<Cursor> callbacks = new LoaderCallbacks<Cursor>() {
		public void onLoaderReset(Loader<Cursor> loader) {
		}

		public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
			// 完成数据提取，更新UI
			StringBuffer sb = new StringBuffer();
			while (cursor.moveToNext()) {
				sb.append(cursor.getString(cursor.getColumnIndex("name")));
				sb.append("\n");
			}
			TextView name = (TextView) findViewById(R.id.name);
			name.setText(sb.toString());
		}

		public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
			// 创建查询条件
			CursorLoader loader = new CursorLoader(MainActivity.this);
			Uri uri = Uri.parse("content://com.android.test.database.UserContentProvider/user");
			loader.setUri(uri);
			return loader;
		}
	};

}
