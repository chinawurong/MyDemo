package com.android.test.ssjl;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FinalActivity {

	private @ViewInject(id = R.id.delivery_city)
	TextView cityTex;
	private @ViewInject(id = R.id.delivery_county)
	TextView countyTex;

	private @ViewInject(id = R.id.scroll_province)
	ScrollView scrollProvince;
	private @ViewInject(id = R.id.scroll_city)
	ScrollView scrollCity;
	private @ViewInject(id = R.id.layout_county)
	ScrollView scrollCounty;

	private @ViewInject(id = R.id.radio_province)
	RadioGroup provinceGroup;
	private @ViewInject(id = R.id.radio_city)
	RadioGroup cityGroup;
	private @ViewInject(id = R.id.radio_county)
	RadioGroup countyGroup;

	private StringBuffer provinceBf = new StringBuffer();
	private boolean isUpdataCounty = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cityTex.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				View layoutCity = findViewById(R.id.layout_city);
				if (layoutCity.getVisibility() == View.VISIBLE) {
					layoutCity.setVisibility(View.GONE);
					layoutCity.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_out));
					return;
				}
				String[] citys = cityTex.getText().toString().split(",");
				showSelectCity(citys);
			}
		});
		countyTex.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (scrollCounty.getVisibility() == View.VISIBLE) {
					scrollCounty.setVisibility(View.GONE);
					scrollCounty.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_out));
					return;
				}
				String[] citys = cityTex.getText().toString().split(",");
				showSelectCounty(citys[citys.length - 1], countyTex.getText().toString());
			}
		});
	}

	/** 选择区
	 * @param city
	 * @param str */
	private void showSelectCounty(String city, String str) {
		if (TextUtils.isEmpty(city) || CountryUtil.getCountys(city) == null) {
			Toast.makeText(MainActivity.this, "请先选择市", Toast.LENGTH_SHORT).show();
			return;
		}
		countyGroup.removeAllViews();
		for (int i = 0, len = CountryUtil.getCountys(city).length; i < len; i++) {
			String county = CountryUtil.getCountys(city)[i];
			RadioButton countyRadio = (RadioButton) getLayoutInflater().inflate(R.layout.radio_item, null);
			countyRadio.setText(county);
			countyGroup.addView(countyRadio);
			if (str.equals(county)) {
				countyRadio.setChecked(true);
				scrollCounty.postDelayed(new scrollRun(i, scrollCounty), 200);
			}
		}
		countyGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton countyRadio = (RadioButton) group.findViewById(checkedId);
				countyTex.setText(countyRadio.getText());
			}
		});
		View layoutCity = findViewById(R.id.layout_city);
		if (layoutCity.getVisibility() == View.VISIBLE) {
			layoutCity.setVisibility(View.GONE);
			layoutCity.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_out));
		}
		scrollCounty.setVisibility(View.VISIBLE);
		scrollCounty.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_in_suspend));
	}

	/** 省市选择 */
	private void showSelectCity(String[] strs) {
		provinceGroup.removeAllViews();
		String[] provinces = CountryUtil.getProvinces();
		for (int i = 0, len = provinces.length; i < len; i++) {
			String province = provinces[i];
			RadioButton provinceRadio = (RadioButton) getLayoutInflater().inflate(R.layout.radio_item, null);
			provinceRadio.setText(province);
			provinceGroup.addView(provinceRadio);
			if (strs[0].equals(province)) {
				isUpdataCounty = false;
				provinceRadio.setChecked(true);
				scrollProvince.postDelayed(new scrollRun(i, scrollProvince), 200);
			}
		}
		if (strs.length > 1 && !TextUtils.isEmpty(strs[1]) && CountryUtil.getCitys(strs[0]) != null) {
			cityGroup.removeAllViews();
			String[] citys = CountryUtil.getCitys(strs[0]);
			for (int i = 0, len = citys.length; i < len; i++) {
				String city = citys[i];
				RadioButton cityRadio = (RadioButton) getLayoutInflater().inflate(R.layout.radio_item, null);
				cityRadio.setText(city);
				cityGroup.addView(cityRadio);
				if (strs[1].equals(city)) {
					isUpdataCounty = false;
					cityRadio.setChecked(true);
					scrollCity.postDelayed(new scrollRun(i, scrollCity), 200);
				}
			}
		}
		provinceGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (isUpdataCounty) {
					countyTex.setText("");
					scrollCounty.scrollTo(0, 0);
				}
				RadioButton provinceRadio = (RadioButton) group.findViewById(checkedId);
				cityTex.setText(provinceRadio.getText());
				provinceBf.delete(0, provinceBf.length());
				provinceBf.append(provinceRadio.getText());
				cityGroup.removeAllViews();
				String[] citys = CountryUtil.getCitys(provinceRadio.getText().toString());
				if (citys != null) {
					for (int i = 0, len = citys.length; i < len; i++) {
						String city = citys[i];
						RadioButton cityRadio = (RadioButton) getLayoutInflater().inflate(R.layout.radio_item, null);
						cityRadio.setText(city);
						cityGroup.addView(cityRadio);
					}
				}
			}
		});
		cityGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (isUpdataCounty) {
					countyTex.setText("");
					scrollCounty.scrollTo(0, 0);
				}
				RadioButton cityRadio = (RadioButton) group.findViewById(checkedId);
				cityTex.setText(provinceBf.toString() + "," + cityRadio.getText().toString());
			}
		});
		isUpdataCounty = true;
		if (scrollCounty.getVisibility() == View.VISIBLE) {
			scrollCounty.setVisibility(View.GONE);
			scrollCounty.setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_out));
		}
		findViewById(R.id.layout_city).setVisibility(View.VISIBLE);
		findViewById(R.id.layout_city).setAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_in_suspend));
	}

	private class scrollRun implements Runnable {
		private int i;
		private ScrollView view;

		public scrollRun(int i, ScrollView view) {
			this.i = i;
			this.view = view;
		}

		public void run() {
			float scale = getResources().getDisplayMetrics().density;
			view.scrollTo(0, i * (int) (50 * scale + 0.5f));
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			View layoutCity = findViewById(R.id.layout_city);
			if (layoutCity.isShown()) {
				layoutCity.setVisibility(View.GONE);
				layoutCity.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_out));
			} else if (scrollCounty.isShown()) {
				scrollCounty.setVisibility(View.GONE);
				scrollCounty.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.bottom_out));
			} else {
				this.finish();
			}
		}
		return false;
	}
}
