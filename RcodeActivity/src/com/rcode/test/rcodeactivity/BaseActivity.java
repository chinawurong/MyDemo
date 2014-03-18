package com.rcode.test.rcodeactivity;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		BaseActivity.initFindViewById(this);
	}

	public static void initFindViewById(Activity sourceActivity) {
		View sourceView = sourceActivity.getWindow().getDecorView();
		Field[] fields = sourceActivity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				FindViewById findViewById = field.getAnnotation(FindViewById.class);
				if (findViewById != null) {
					int viewId = findViewById.id();
					try {
						field.setAccessible(true);
						field.set(sourceActivity, sourceView.findViewById(viewId));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
