package com.clduo.test.saizi;

import java.util.Random;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.clduo.test.saizi.ShakeListener.OnShakeListener;

public class MainActivity extends Activity {

	private ImageView iv, iv2, iv3;
	private ShakeListener shake;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		iv2 = (ImageView) findViewById(R.id.iv2);
		iv3 = (ImageView) findViewById(R.id.iv3);

		handler = new Handler();
		shake = new ShakeListener(this);
		shake.setOnShakeListener(new OnShakeListener() {
			public void onShake() {
				new VibratorTool(getApplicationContext());
				iv.setBackgroundResource(R.drawable.saizi_anim);
				iv2.setBackgroundResource(R.drawable.saizi_anim);
				iv3.setBackgroundResource(R.drawable.saizi_anim);
				final AnimationDrawable drawable = (AnimationDrawable) iv.getBackground();
				final AnimationDrawable drawable2 = (AnimationDrawable) iv2.getBackground();
				final AnimationDrawable drawable3 = (AnimationDrawable) iv3.getBackground();
				drawable.start();
				drawable2.start();
				drawable3.start();
				iv.startAnimation(tweeAnim());
				iv2.startAnimation(tweeAnim());
				iv3.startAnimation(tweeAnim());
				handler.postDelayed(new Runnable() {
					public void run() {
						drawable.stop();
						drawable2.stop();
						drawable3.stop();
						iv.setBackgroundResource(getBackGroup());
						iv2.setBackgroundResource(getBackGroup());
						iv3.setBackgroundResource(getBackGroup());
					}
				}, 1000);
			}
		});
	}

	private int getBackGroup() {
		Random random = new Random();
		switch (random.nextInt(5)) {
		case 0:
			return R.drawable.dice1;
		case 1:
			return R.drawable.dice2;
		case 2:
			return R.drawable.dice3;
		case 3:
			return R.drawable.dice4;
		case 4:
			return R.drawable.dice5;
		case 5:
			return R.drawable.dice6;
		}
		return R.drawable.dice6;
	}

	private AnimationSet tweeAnim() {
		AnimationSet setAnim = new AnimationSet(false);
		setAnim.setRepeatCount(3);
		setAnim.setRepeatMode(Animation.REVERSE);
		Random random = new Random();
		TranslateAnimation translateAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f);
		translateAnim.setDuration(random.nextInt(10)*10+100);//指定动画播放的时间
		translateAnim.setRepeatCount(3);//动画重复播放次数
		translateAnim.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		TranslateAnimation translateAnim2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f);
		translateAnim2.setDuration(random.nextInt(10)*10+100);//指定动画播放的时间
		translateAnim2.setRepeatCount(3);//动画重复播放次数
		translateAnim2.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		TranslateAnimation translateAnim3 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f);
		translateAnim3.setDuration(random.nextInt(10)*10+100);//指定动画播放的时间
		translateAnim3.setRepeatCount(3);//动画重复播放次数
		translateAnim3.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		TranslateAnimation translateAnim4 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0.15f);
		translateAnim4.setDuration(random.nextInt(10)*10+100);//指定动画播放的时间
		translateAnim4.setRepeatCount(3);//动画重复播放次数
		translateAnim4.setRepeatMode(Animation.REVERSE);//动画重复播放的模式 REVERSE:反着播放

		setAnim.addAnimation(translateAnim2);
		setAnim.addAnimation(translateAnim);
		setAnim.addAnimation(translateAnim4);
		setAnim.addAnimation(translateAnim3);
		return setAnim;
	}
}
