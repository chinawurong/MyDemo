package com.android.test.update;

import java.io.File;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

/** 更新app服务
 * @author wanglei */
public class UpdateService extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private NotificationManager manager;
	private NotificationCompat.Builder builder;
	private RemoteViews rViews;

	@Override
	public void onCreate() {
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//创建一个通知管理类

		rViews = new RemoteViews(getPackageName(), R.layout.update_view);
		rViews.setImageViewResource(R.id.logo, R.drawable.ic_launcher);
		rViews.setTextViewText(R.id.title, "更新彩礼多应用");

		Intent sendIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, sendIntent, 0);
		builder = new NotificationCompat.Builder(this);
		builder.setContentIntent(pendingIntent);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle("更新彩礼多应用");
		builder.setContentText("更新彩礼多应用");
		builder.setTicker("更新彩礼多应用");
		builder.setAutoCancel(true);//点击通知跳转并清除
		builder.setContent(rViews);

		Notification notification = builder.build();
		manager.notify(1001, notification);

		//下载APK
		FinalHttp http = new FinalHttp();
		http.download("http://d.clduo.com/and", "/mnt/sdcard/Clduo/urdateAPK/clduo.apk", new AjaxCallBack<File>() {
			@Override
			public void onLoading(long count, long current) {
				rViews.setProgressBar(R.id.upadte_bar, 100, (int) (current / (count / 100)), false);
				rViews.setTextViewText(R.id.update_progress, "下载 " + (current / (count / 100)) + "%");
				builder.setContent(rViews);
				Notification notification = builder.build();
				manager.notify(1001, notification);
			}

			public void onSuccess(File t) {
				rViews.setProgressBar(R.id.upadte_bar, 100, 100, false);
				rViews.setTextViewText(R.id.update_progress, "下载完成");
				builder.setContent(rViews);
				Notification notification = builder.build();
				manager.notify(1001, notification);

				Intent intent = new Intent(Intent.ACTION_VIEW);
				File file = new File("/mnt/sdcard/Clduo/urdateAPK/clduo.apk");
				intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

				manager.cancel(1001);

				UpdateService.this.stopSelf();
			};
		});
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

}
