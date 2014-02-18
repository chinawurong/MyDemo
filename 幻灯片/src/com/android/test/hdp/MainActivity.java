package com.android.test.hdp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	private ViewFlipper flipper;
	private ViewPager pager;
	private PagerAdapter adapter;
	private RadioGroup group;
	private Handler handler = new Handler();
	private float x;
	private int pageindex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getViewFlipper();
		getViewPager();
	}

	private void getViewPager() {
		pager = (ViewPager) findViewById(R.id.viewpager);
		group = (RadioGroup) findViewById(R.id.radio_group);
		int[] pics = { R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4 };
		List<View> views = new ArrayList<View>();
		for (int imgId : pics) {
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgId);
			views.add(iv);
		}
		adapter = new MyAdapter(views);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				pageindex = position;
				RadioButton radioBtn = (RadioButton) group.getChildAt(pageindex);
				radioBtn.setChecked(true);
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int position) {
			}
		});

		for (int i = 0, len = group.getChildCount(); i < len; i++) {
			final int pageI = i;
			group.getChildAt(i).setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					pager.setCurrentItem(pageI);
					pageindex = pageI;
				}
			});
		}

		handler.post(pageRun);
	}

	private Runnable pageRun = new Runnable() {
		public void run() {
			if (pageindex >= 4) {
				pageindex = 0;
			}
			pager.setCurrentItem(pageindex);
			pageindex++;
			handler.postDelayed(pageRun, 2000);
		}
	};

	private class MyAdapter extends PagerAdapter {
		private List<View> views;

		public MyAdapter(List<View> views) {
			this.views = views;
		}

		public int getCount() {
			return views.size();
		}

		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position), 0);
			return views.get(position);
		}

		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}
	}

	private void getViewFlipper() {
		flipper = (ViewFlipper) findViewById(R.id.view_flipper);
		flipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）  
		flipper.setFlipInterval(2000);
		flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.right_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.left_out));
		flipper.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == event.ACTION_DOWN) {
					x = event.getX();
					flipper.stopFlipping();
				}
				if (event.getAction() == event.ACTION_UP) {
					if (x > event.getX()) {
						flipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_in));
						flipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_out));
						flipper.showNext();
					} else {
						flipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in));
						flipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.right_out));
						flipper.showPrevious();
					}
					flipper.startFlipping();
				}
				return true;
			}
		});
	}
}
