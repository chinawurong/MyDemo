package com.example.looperdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler handler;
	private Button button1;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text1);
		textView.setText(showCode());
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Message msg = handler.obtainMessage();
				msg.obj = "我被点击了";
				handler.sendMessage(msg);
			}
		});
		Thread myTh = new myThread();
		myTh.start();
	}

	private class myThread extends Thread {
		@Override
		public void run() {
			Looper.prepare();
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					String str = (String) msg.obj;
					Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
				}
			};
			Looper.loop();
		}
	}

	private String showCode() {
		StringBuffer sb = new StringBuffer();
		sb.append("@Override").append("\n");
		sb.append("protected void onCreate(Bundle savedInstanceState) {").append("\n");
		sb.append("	super.onCreate(savedInstanceState);").append("\n");
		sb.append("	setContentView(R.layout.activity_main);").append("\n");
		sb.append("	button1 = (Button) findViewById(R.id.button1);").append("\n");
		sb.append("	button1.setOnClickListener(new OnClickListener() {").append("\n");
		sb.append("		@Override").append("\n");
		sb.append("		public void onClick(View v) {").append("\n");
		sb.append("			Message msg = handler.obtainMessage();").append("\n");
		sb.append("			msg.obj = \"我被点击了\";").append("\n");
		sb.append("			handler.sendMessage(msg);").append("\n");
		sb.append("		}").append("\n");
		sb.append("	});").append("\n");
		sb.append("	Thread myTh = new myThread();").append("\n");
		sb.append("	myTh.start();").append("\n");
		sb.append("}").append("\n");
		sb.append("").append("\n");
		sb.append("private class myThread extends Thread {").append("\n");
		sb.append("	@Override").append("\n");
		sb.append("	public void run() {").append("\n");
		sb.append("		Looper.prepare();").append("\n");
		sb.append("		handler = new Handler() {").append("\n");
		sb.append("			@Override").append("\n");
		sb.append("			public void handleMessage(Message msg) {").append("\n");
		sb.append("				String str = (String) msg.obj;").append("\n");
		sb.append("				Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();").append("\n");
		sb.append("			}").append("\n");
		sb.append("		};").append("\n");
		sb.append("		Looper.loop();").append("\n");
		sb.append("	}").append("\n");
		sb.append("}").append("\n");
		return sb.toString();
	}
}
