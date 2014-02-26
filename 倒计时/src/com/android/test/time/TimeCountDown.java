package com.android.test.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.CountDownTimer;

public class TimeCountDown extends CountDownTimer {
	private TimeCallback callback;
	private SimpleDateFormat format;

	public TimeCountDown(long millisInFuture, TimeCallback callback) {
		super(millisInFuture, 1000);
		this.callback = callback;
		format = new SimpleDateFormat("mm:ss");
	}

	@Override
	public void onTick(long millisUntilFinished) {
		callback.timeTick(format.format(new Date(millisUntilFinished)));
	}

	@Override
	public void onFinish() {
		callback.timeFinish();
	}

	public interface TimeCallback {
		public void timeTick(String timeFormat);

		public void timeFinish();
	}
}
