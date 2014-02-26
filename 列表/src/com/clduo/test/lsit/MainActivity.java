package com.clduo.test.lsit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private BaseAdapter adapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new MyAdapter(this);
		listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public MyAdapter(Context context) {
			this.inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return 20;
		}

		@Override
		public Object getItem(int position) {
			return new String(position + "");
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(MainActivity.this);
			textView.setText("" + position);
			textView.setPadding(10, 20, 10, 20);
			return textView;
		}

	}

}
