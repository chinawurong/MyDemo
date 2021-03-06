package com.android.test.diylayout;

import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.clduo.test.rcode.http.HttpBO;
import com.clduo.test.rcode.utils.DensityUtil;
import com.clduo.test.rcode.utils.DeviceUtil;
import com.clduo.test.rcode.utils.JsonUtil;

public class diyLayout2 extends Activity {
	private int widthP, heightP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout2);
		HttpBO.getInitInfo(getScreen(), DeviceUtil.versionName(this), new AjaxCallBack<String>() {
			public void onSuccess(String result) {

				Map<String, String> baseMap = JsonUtil.getMapStr(result);
				Map<String, String> resultMap = JsonUtil.getMapStr(baseMap.get("data"));
				List<Map<String, String>> resultMaps = JsonUtil.getListMapStr(resultMap.get("indexLayout"));
				for (Map<String, String> map : resultMaps) {

					Map<String, String> position = JsonUtil.getMapStr(map.get("position"));
					int w = Integer.parseInt(position.get("width"));
					int h = Integer.parseInt(position.get("height"));
					int x = Integer.parseInt(position.get("originX"));
					int y = Integer.parseInt(position.get("originY"));

					RelativeLayout.LayoutParams params = new LayoutParams(widthP * w, heightP * h);
					params.leftMargin = widthP * x;
					params.topMargin = heightP * y;
					View itemView = getLayoutInflater().inflate(R.layout.item_layout, null);
					ImageView imageView = (ImageView) itemView.findViewById(R.id.img);
					List<String> urls = JsonUtil.getListObj(map.get("picUrl"), String.class);

					FinalBitmap bitmap = FinalBitmap.create(diyLayout2.this);
					bitmap.display(imageView, urls.get(0));
					imageView.setOnClickListener(clickListener);

					RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.content);
					mainLayout.addView(itemView, params);
				}
			}
		});

	}

	private View.OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			scaleAnim.setDuration(200);
			scaleAnim.setRepeatCount(1);
			scaleAnim.setRepeatMode(Animation.REVERSE);
			v.startAnimation(scaleAnim);
		}
	};

	/** 获取手机分辨率 */
	private String getScreen() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		widthP = metric.widthPixels / 3;
		heightP = (metric.heightPixels - DensityUtil.dip2px(this, 150)) / 4;
		double d = metric.heightPixels / metric.widthPixels;
		double screen0 = 1.5;
		double screen1 = 1.67;
		double screen2 = 1.78;
		if (d < screen0) {
			return "0";
		} else if (d < screen1) {
			double d1 = d - screen0;
			double d2 = screen1 - d;
			if (d1 < d2) {
				return "0";
			} else {
				return "1";
			}
		} else if (d < screen2) {
			double d1 = d - screen1;
			double d2 = screen2 - d;
			if (d1 < d2) {
				return "1";
			} else {
				return "2";
			}
		} else {
			return "2";
		}
	}
}
