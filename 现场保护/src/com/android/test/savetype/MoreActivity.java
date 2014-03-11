package com.android.test.savetype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MoreActivity extends Activity {

	private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("下一个页面");
		setContentView(textView);
		bundle = getIntent().getExtras();
		String text = bundle.getInt("flag1", 0) + "-" + bundle.getInt("flag2", 0) + "-" + bundle.getInt("flag3", 0);
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		
//		if (savedInstanceState != null) {
//			bundle = savedInstanceState.getBundle("flag");
//		}

		textView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MoreActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (bundle.getBoolean("isflag", false)) {
			outState.putBundle("flag", bundle);
		}
	}
}
