package com.clduo.test.tweenedanim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

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
		AlphaAnimation alpAnmi = new AlphaAnimation(1.0f, 0.0f);
		alpAnmi.setDuration(2000);//指定动画播放的时间
		alpAnmi.setRepeatCount(2);//动画重复播放次数
		alpAnmi.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放
		iv.startAnimation(alpAnmi);
	}

	/** 缩放
	 * @param v */
	public void click2(View v) {
		ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnim.setDuration(300);//指定动画播放的时间
		scaleAnim.setRepeatCount(1);//动画重复播放次数
		scaleAnim.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放
		iv.startAnimation(scaleAnim);
	}

	/** 旋转
	 * @param v */
	public void click3(View v) {
		RotateAnimation rotateAnim = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(2000);//指定动画播放的时间
		rotateAnim.setRepeatCount(3);//动画重复播放次数
		rotateAnim.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放
		iv.startAnimation(rotateAnim);
	}

	/** 平移
	 * @param v */
	public void click4(View v) {
		TranslateAnimation translateAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.1f);
		translateAnim.setDuration(2000);//指定动画播放的时间
		translateAnim.setRepeatCount(3);//动画重复播放次数
		translateAnim.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放
		iv.startAnimation(translateAnim);
	}

	/** 组合
	 * @param v */
	public void click5(View v) {
		AnimationSet setAnim = new AnimationSet(false);
		setAnim.setRepeatCount(3);
		setAnim.setRepeatMode(Animation.REVERSE);

		TranslateAnimation translateAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f);
		translateAnim.setDuration(100);//指定动画播放的时间
		translateAnim.setRepeatCount(3);//动画重复播放次数
		translateAnim.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		TranslateAnimation translateAnim2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f);
		translateAnim2.setDuration(200);//指定动画播放的时间
		translateAnim2.setRepeatCount(3);//动画重复播放次数
		translateAnim2.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		TranslateAnimation translateAnim3 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f);
		translateAnim3.setDuration(120);//指定动画播放的时间
		translateAnim3.setRepeatCount(3);//动画重复播放次数
		translateAnim3.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		TranslateAnimation translateAnim4 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f);
		translateAnim4.setDuration(60);//指定动画播放的时间
		translateAnim4.setRepeatCount(3);//动画重复播放次数
		translateAnim4.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		setAnim.addAnimation(translateAnim2);
		setAnim.addAnimation(translateAnim);
		setAnim.addAnimation(translateAnim4);
		setAnim.addAnimation(translateAnim3);
		iv.startAnimation(setAnim);
	}
}
