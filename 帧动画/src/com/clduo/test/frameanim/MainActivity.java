package com.clduo.test.frameanim;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	private AnimationDrawable animationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		iv.setBackgroundResource(R.drawable.saizi_anim);
		animationDrawable = (AnimationDrawable) iv.getBackground();

	}

	public void click(View v) {
		animationDrawable.start();
	}
}
