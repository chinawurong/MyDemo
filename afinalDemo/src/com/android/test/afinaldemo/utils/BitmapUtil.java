package com.android.test.afinaldemo.utils;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.test.afinaldemo.R;

public class BitmapUtil {

	private static final String IMGPATH = Environment.getExternalStorageDirectory() + "/CLDuo/";

	public BitmapUtil() {
	}

	public static BitmapUtil getThis() {
		BitmapUtil bitmapUtil = new BitmapUtil();
		return bitmapUtil;
	}

	public void display(Context ctx, ImageView imageView, String urlPath) {
		if (TextUtils.isEmpty(urlPath)) {
			imageView.setImageResource(R.drawable.ic_launcher);
			return;
		}
		FinalBitmap finalBitmap = FinalBitmap.create(ctx);
		finalBitmap.configDiskCachePath(IMGPATH);
		finalBitmap.configLoadfailImage(R.drawable.ic_launcher);//图片加载失败时候显示的图片
		finalBitmap.configLoadingImage(R.drawable.ic_launcher);//图片正在加载的时候显示的图片
		finalBitmap.display(imageView, urlPath);
	}
}
