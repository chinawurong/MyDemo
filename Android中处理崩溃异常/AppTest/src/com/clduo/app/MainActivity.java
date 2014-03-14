package com.clduo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String s;
	private TextView showCode;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showCode();
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println(s.equals("any string"));
			}
		});
	}

	private void showCode() {
		showCode = (TextView) findViewById(R.id.show_code);
		StringBuffer sb = new StringBuffer();
		sb.append("button = (Button) findViewById(R.id.button);").append("\n");
		sb.append("button.setOnClickListener(new View.OnClickListener() {").append("\n");
		sb.append("	public void onClick(View v) {").append("\n");
		sb.append("		System.out.println(s.equals(\"any string\"));").append("\n");
		sb.append("	}").append("\n");
		sb.append("});").append("\n");
		showCode.setText(sb.toString());
	}

}
