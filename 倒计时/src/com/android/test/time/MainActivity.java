package com.android.test.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView, text2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.text);
		text2 = (TextView) findViewById(R.id.text2);
		//自定义的
		TimeCountDown countDown = new TimeCountDown(10000, new TimeCountDown.TimeCallback() {
			public void timeTick(String timeFormat) {
				textView.setText(timeFormat);
			}

			public void timeFinish() {
				textView.setText("结束了");
			}
		});
		countDown.start();

		//系统的
		CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
			public void onTick(long millisUntilFinished) {
				SimpleDateFormat format = new SimpleDateFormat("mm:ss");
				text2.setText(format.format(new Date(millisUntilFinished)));
			}

			public void onFinish() {
				text2.setText("结束了");
			}
		};
		countDownTimer.start();
	}

}
