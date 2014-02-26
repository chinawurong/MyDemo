package com.android.test.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button updateBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		updateBtn = (Button) findViewById(R.id.update);
		updateBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, UpdateService.class);
				stopService(intent);
				startService(intent);
			}
		});
	}
}
