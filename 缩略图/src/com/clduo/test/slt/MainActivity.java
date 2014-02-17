package com.clduo.test.slt;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText bili;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView yuantu = (ImageView) findViewById(R.id.yuan_tu);
		yuantu.setImageResource(R.drawable.share_pic);
		bili = (EditText) findViewById(R.id.bili);
	}

	public void onClickSltBtn(View v) {
		Drawable drawable = getResources().getDrawable(R.drawable.share_pic);
		Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
		float fl = Float.parseFloat(bili.getText().toString());
		bitmap = smallBitmap(bitmap, fl);
		//获取图片大小
		int len = bmpToByteArray(bitmap, false).length;
		TextView tuLen = (TextView) findViewById(R.id.tupian_len);
		tuLen.setText(len + "|" + (len / 1024));

		ImageView zjtu = (ImageView) findViewById(R.id.sl_tu);
		zjtu.setImageBitmap(bitmap);
	}

	/** Bitmap缩小的方法 */
	private Bitmap smallBitmap(Bitmap bitmap, float fl) {
		Matrix matrix = new Matrix();
		matrix.postScale(fl, fl); //长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	private byte[] bmpToByteArray(Bitmap bmp, boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
