package com.android.test.xzfc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView img;
	private int iCount = 0;
	private int rCount = 0;
	private float x = 0, y = 0;
	private boolean isShow = true;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img = (ImageView) findViewById(R.id.img);
		handler.post(runnable);
	}

	private void anim(int i, int r) {
		RotateAnimation rotateAnim = new RotateAnimation(i, r, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnim.setDuration(1);//指定动画播放的时间
		rotateAnim.setFillAfter(true);
		img.startAnimation(rotateAnim);
	}

	private Runnable runnable = new Runnable() {
		public void run() {
			if (isShow) {
				rCount += 10;
			} else {
				rCount -= 10;
			}
			anim(iCount, rCount);
			iCount = rCount;
			handler.postDelayed(runnable, 50);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == event.ACTION_DOWN) {
			x = event.getX();
			y = event.getY();
		}
		if (event.getAction() == event.ACTION_UP) {
			if (x > event.getX()) {
				isShow = true;
			} else {
				isShow = false;
			}
		}
		if (event.getAction() == event.ACTION_MOVE) {
			if (y > event.getY()) {
				rCount += 20;
			} else {
				rCount -= 20;
			}
			y = event.getY();
			anim(iCount, rCount);
			iCount = rCount;
		}
		return super.onTouchEvent(event);
	}
}
