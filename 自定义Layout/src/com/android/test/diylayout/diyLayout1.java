package com.android.test.diylayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class diyLayout1 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout1);
		View view = findViewById(android.R.id.content);
		View itemView = getLayoutInflater().inflate(R.layout.item_layout, null);
	}
}
