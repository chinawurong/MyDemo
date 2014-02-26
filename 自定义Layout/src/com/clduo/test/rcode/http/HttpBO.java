package com.clduo.test.rcode.http;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

public class HttpBO {

	public static void getInitInfo(String resolution, String versionNo, AjaxCallBack<String> callBack) {
		FinalHttp http = new FinalHttp();
		AjaxParams params = new AjaxParams();
		params.put("versionNo", versionNo);
		params.put("resolution", resolution);
		params.put("clientSort", "ANDROID");
		http.post("http://115.236.89.58:7002/member/getInitInfo.htm", params, callBack);
	}
}
