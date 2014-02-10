package com.android.test.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity {

	private Button button1, button2;
	private NotificationManager manager;
	private NotificationCompat.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//创建一个通知管理类
		
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, OneActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				builder = new NotificationCompat.Builder(MainActivity.this);
				builder.setContentIntent(pendingIntent);
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setContentTitle("通知标题");
				builder.setContentText("通知内容");
				builder.setTicker("new");
				builder.setDefaults(Notification.DEFAULT_ALL);
				builder.setAutoCancel(true);//点击通知跳转并清除

				Notification notification = builder.build();
				manager.notify(1000, notification);
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_view);
				remoteViews.setImageViewResource(R.id.img, R.drawable.ic_launcher);
				remoteViews.setTextViewText(R.id.title, "自定义通知标题");
				remoteViews.setTextViewText(R.id.text, "自定义通知内容");

				Intent intent = new Intent(MainActivity.this, OneActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				builder = new NotificationCompat.Builder(MainActivity.this);
				builder.setContentIntent(pendingIntent);
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setContentTitle("通知标题");
				builder.setContentText("通知内容");
				builder.setTicker("new");
				builder.setDefaults(Notification.DEFAULT_ALL);
				builder.setAutoCancel(true);//点击通知跳转并清除

				builder.setContent(remoteViews);

				Notification notification = builder.build();
				manager.notify(1001, notification);
			}
		});
	}

}
