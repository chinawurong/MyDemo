package com.clduo.test.saizi;

import android.content.Context;
import android.os.Vibrator;

/** 震动事件工具类
 * @author wanglei */
public class VibratorTool {

	public VibratorTool() {
	}

	public VibratorTool(Context context) {
		onVibrator(context);
	}

	public void onVibrator(Context context) {
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		if (vibrator == null) {
			Vibrator localVibrator = (Vibrator) context.getApplicationContext().getSystemService("vivrator");
			vibrator = localVibrator;
		}
		vibrator.vibrate(50);
	}
}
