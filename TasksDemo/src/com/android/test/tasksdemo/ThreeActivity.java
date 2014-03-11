package com.android.test.tasksdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);

		Button button = (Button) findViewById(R.id.next);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(ThreeActivity.this, "最后一页了", Toast.LENGTH_SHORT).show();
			}
		});

		Button button2 = (Button) findViewById(R.id.return_main);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ThreeActivity.this, OneActivity.class);
				startActivity(intent);
			}
		});
	}

}
