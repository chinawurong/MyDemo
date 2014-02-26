package com.android.test.xxjrf;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	private RadioGroup group;
	private FragmentManager manager;
	private FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getSupportFragmentManager();
		group = (RadioGroup) findViewById(R.id.radio_group);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				transaction = manager.beginTransaction();
				if (group.getChildAt(0).getId() == checkedId) {
					transaction.replace(R.id.content, new Fragment1());
				} else if (group.getChildAt(1).getId() == checkedId) {
					transaction.replace(R.id.content, new Fragment2());
				} else if (group.getChildAt(2).getId() == checkedId) {
					transaction.replace(R.id.content, new Fragment3());
				} else if (group.getChildAt(3).getId() == checkedId) {
					transaction.replace(R.id.content, new Fragment4());
				}
				transaction.commit();
			}
		});
		RadioButton radio = (RadioButton) group.getChildAt(0);
		radio.setChecked(true);
	}

}
