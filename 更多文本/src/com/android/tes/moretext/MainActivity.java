package com.android.tes.moretext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private TextView content;
	private ToggleButton showText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showText = (ToggleButton) findViewById(R.id.show_all);
		content = (TextView) findViewById(R.id.content);
		showText.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				MoreTextAnimation animation = new MoreTextAnimation(content, 224, 24);
				animation.setDuration(500);
				animation.setShowView(isChecked);
				if (isChecked) {
					content.startAnimation(animation);
				} else {
					content.startAnimation(animation);
				}
			}
		});
	}
}
