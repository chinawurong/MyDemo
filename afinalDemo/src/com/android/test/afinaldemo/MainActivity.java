package com.android.test.afinaldemo;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.test.afinaldemo.utils.BitmapUtil;

public class MainActivity extends Activity {
	private LinearLayout content1, content2, content3;

	private @ViewInject(id = R.id.content1)LinearLayout content1L;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		content1 = (LinearLayout) findViewById(R.id.content1);
		content2 = (LinearLayout) findViewById(R.id.content2);
		content3 = (LinearLayout) findViewById(R.id.content3);

		String[] urls = { "http://www.huaseji.com/tag/5/", "http://www.huaseji.com/tag/2/", "http://www.huaseji.com/tag/41/", "http://www.huaseji.com/tag/25/", "http://www.huaseji.com/tag/23/", "http://www.huaseji.com/tag/19/", "http://www.huaseji.com/tag/17/", "http://www.huaseji.com/tag/12/", "http://www.huaseji.com/tag/8/", "http://www.huaseji.com/tag/7/" };

		FinalHttp http = new FinalHttp();
		for (String url : urls) {
			http.get(url, new AjaxCallBack<String>() {
				public void onSuccess(String result) {
					Document document = Jsoup.parse(result);
					doDocument(document);
				}
			});
		}
	}

	private void doDocument(Document doc) {
		Elements pngs = doc.select("img[src$=.jpg]");
		int i = 3;
		for (Element el : pngs) {
			ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_image, null);
			String imgUrl = el.attr("src");
			imageView.setOnClickListener(new ClickListener(imgUrl));
			BitmapUtil.getThis().display(this, imageView, imgUrl);
			int j = i % 3;
			switch (j) {
			case 0:
				content1.addView(imageView);
				break;
			case 1:
				content2.addView(imageView);
				break;
			case 2:
				content3.addView(imageView);
				break;
			}
			i++;
		}
	}

	private class ClickListener implements OnClickListener {
		private String imgUrl;

		public ClickListener(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public void onClick(View v) {
			ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			scaleAnim.setDuration(200);
			scaleAnim.setRepeatCount(1);
			scaleAnim.setRepeatMode(Animation.REVERSE);
			v.startAnimation(scaleAnim);
			
//			Intent intent = new Intent(MainActivity.this,ImageActivity.class);
//			intent.putExtra("imgUrl", imgUrl);
//			startActivity(intent);
		}
	};
}
