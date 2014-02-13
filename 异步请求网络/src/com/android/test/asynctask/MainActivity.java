package com.android.test.asynctask;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button button;
	private String imgPath = "http://365.74443.com/uploads/allimg/120424/1-120424150535.jpg";
	private ImageView imageView;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button1);
		imageView = (ImageView) findViewById(R.id.img1);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new MyTask().execute(imgPath);
			}
		});
	}

	private class MyTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(MainActivity.this, "", "请稍后...");
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(params[0]);
			try {
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					byte[] data = EntityUtils.toByteArray(entity);
					return BitmapFactory.decodeByteArray(data, 0, data.length);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				imageView.setImageBitmap(result);
			}
			dialog.dismiss();
		}

	}

}
