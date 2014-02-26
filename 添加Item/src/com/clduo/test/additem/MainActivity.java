package com.clduo.test.additem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TableLayout table = (TableLayout) findViewById(R.id.table);
		for (int i = 0; i < 5; i++) {
			TableRow tablerow = new TableRow(MainActivity.this);
			for (int j = 0; j < 4; j++) {
				TextView testview = (TextView) getLayoutInflater().inflate(R.layout.layout_iten, null);
				testview.setText(i + "-" + j);
				tablerow.addView(testview);
			}
			table.addView(tablerow);
		}

		TableRow tablerow = new TableRow(MainActivity.this);
		TextView testview = (TextView) getLayoutInflater().inflate(R.layout.layout_iten, null);
		testview.setText("-");
		tablerow.addView(testview);
		table.addView(tablerow);
	}
}
