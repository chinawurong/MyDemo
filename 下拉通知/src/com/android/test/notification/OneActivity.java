package com.android.test.notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				OneActivity.this.finish();
			}
		});
	}

}
