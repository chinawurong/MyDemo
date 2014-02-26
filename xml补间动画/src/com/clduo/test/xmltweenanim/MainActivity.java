package com.clduo.test.xmltweenanim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import com.clduo.test.xmltweenanim.R;

public class MainActivity extends Activity {

	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}

	/** 透明度
	 * @param v */
	public void click1(View v) {
		iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_anim));
	}

	/** 缩放
	 * @param v */
	public void click2(View v) {
		iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_anim));
	}

	/** 旋转
	 * @param v */
	public void click3(View v) {
		iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_anim));
	}

	/** 平移
	 * @param v */
	public void click4(View v) {
		iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.trans_anim));
	}

	/** 组合
	 * @param v */
	public void click5(View v) {
		iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.set_anim));
	}
}
