package com.clduo.test.rcode.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.android.test.diylayout.R;

/** 获取设备信息工具类
 * @author wanglei */
public class DeviceUtil {

	/** 获取手机设备信息
	 * @param context
	 * @return */
	public static String data(Context context) {
		return "Android " + version() + " " + brands() + " " + getLocalMacAddress(context) + " " + operators(context) + " " + netWorkType(context) + " " + context.getString(R.string.market) + " " + versionName(context);
	}

	/** 获取手机android版本
	 * @return */
	private static String version() {
		return TextUtils.isEmpty(Build.VERSION.RELEASE) ? "-" : Build.VERSION.RELEASE;
	}

	/** 获取手机品牌型号
	 * @return */
	private static String brands() {
		String manufacturer = TextUtils.isEmpty(Build.MANUFACTURER) ? "-" : Build.MANUFACTURER;
		String model = TextUtils.isEmpty(Build.MODEL) ? "-" : Build.MODEL;
		return manufacturer + " " + model.replaceAll(" ", "_");
	}

	/** 获取手机MAC地址
	 * @param context
	 * @return */
	public static String getLocalMacAddress(Context context) {
		WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
		if (null != info && !TextUtils.isEmpty(info.getMacAddress())) {
			return info.getMacAddress();
		}
		return "-";
	}

	/** 服务商名称： 例如：中国移动、联通 */
	private static String operators(Context context) {
		TelephonyManager iPhoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String iNumeric = iPhoneManager.getSimOperator();
		if (iNumeric.length() > 0) {
			if (iNumeric.equals("46000") || iNumeric.equals("46002")) {
				return "10086";// 中国移动
			} else if (iNumeric.equals("46001")) {
				return "10010";// 中国联通
			} else if (iNumeric.equals("46003")) {
				return "10000";// 中国电信
			}
		}
		return "-";
	}

	/** 获取网络状态，wifi,wap,2g,3g. */
	public static String netWorkType(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			String type = networkInfo.getTypeName();
			if (type.equalsIgnoreCase("WIFI")) {
				return "WIFI";
			} else if (type.equalsIgnoreCase("MOBILE")) {
				String proxyHost = android.net.Proxy.getDefaultHost();
				return TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? "3G" : "2G") : "WAP";
			}
		}
		return "-";
	}

	private static boolean isFastMobileNetwork(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			return false; // ~ 50-100 kbps
		case TelephonyManager.NETWORK_TYPE_CDMA:
			return false; // ~ 14-64 kbps
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return false; // ~ 50-100 kbps
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return true; // ~ 400-1000 kbps
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return true; // ~ 600-1400 kbps
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return false; // ~ 100 kbps
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return true; // ~ 2-14 Mbps
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return true; // ~ 700-1700 kbps
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return true; // ~ 1-23 Mbps
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return true; // ~ 400-7000 kbps
		case TelephonyManager.NETWORK_TYPE_EHRPD:
			return true; // ~ 1-2 Mbps
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			return true; // ~ 5 Mbps
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return true; // ~ 10-20 Mbps
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return false; // ~25 kbps
		case TelephonyManager.NETWORK_TYPE_LTE:
			return true; // ~ 10+ Mbps
		case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			return false;
		default:
			return false;
		}
	}

	/** 获取app版本号
	 * @return */
	public static String versionName(Context context) {
		PackageInfo packInfo;
		PackageManager pm = context.getPackageManager();
		try {
			packInfo = pm.getPackageInfo(context.getPackageName(), 0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			return "-";
		}
	}

}
