package com.android.test.web;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private WebView webView;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//如果你想点击一个链接然后让应用关闭可以这样做
				String breakUrl = "http://music.baidu.com/";//定义退出的标签
				if (url.contains(breakUrl)) {
					MainActivity.this.finish();//退出
				}
				dialog = ProgressDialog.show(MainActivity.this, null, null);
				webView.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				dialog.dismiss();
			}
		});
		dialog = ProgressDialog.show(this, null, null);
		webView.loadUrl("http://www.baidu.com");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
