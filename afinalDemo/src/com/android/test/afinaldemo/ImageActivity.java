package com.android.test.afinaldemo;

import com.android.test.afinaldemo.utils.BitmapUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		ImageView image = (ImageView) findViewById(R.id.img);
		BitmapUtil.getThis().display(ImageActivity.this, image, getIntent().getStringExtra("imgUrl"));
	}
}