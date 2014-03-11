package com.android.test.savetype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private ToggleButton toggleButton;
	private Button next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
		next = (Button) findViewById(R.id.textView1);
		next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MoreActivity.class);
				intent.putExtra("flag1", 1);
				intent.putExtra("flag2", 2);
				intent.putExtra("flag3", 3);
				intent.putExtra("isflag", toggleButton.isChecked());
				startActivity(intent);
			}
		});
		if (savedInstanceState != null) {
			Toast.makeText(this, "" + savedInstanceState.getBoolean("toggleType", false), 1).show();
			toggleButton.setChecked(savedInstanceState.getBoolean("toggleType", false));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("toggleType", toggleButton.isChecked());
	}
}
