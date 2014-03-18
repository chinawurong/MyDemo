package com.rcode.test.rcodeactivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private @FindViewById(id = R.id.text)
	TextView textV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textV.setText("我成功了~~!");
	}

}
