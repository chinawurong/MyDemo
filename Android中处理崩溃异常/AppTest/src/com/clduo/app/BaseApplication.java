package com.clduo.app;

import android.app.Application;

public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();
		exceptionHandler.init(getApplicationContext());
	}
}
