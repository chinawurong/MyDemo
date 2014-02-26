package com.clduo.test.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();
		Fargment1 fargment1 = new Fargment1();
		Fargment2 fargment2 = new Fargment2();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if (width > height) {
			transaction.replace(android.R.id.content, fargment1);
		} else {
			transaction.replace(android.R.id.content, fargment2);
		}
		transaction.commit();
	}

}
