package com.clduo.test.rcode.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/** Json工具类
 * @author wanglei */
public class JsonUtil {

	/** 使用JSON工具把数据转换成json对象
	 * @param value 是对解析的集合的类型 */
	public static String toJson(Object value) {
		return JSON.toJSONString(value);
	}

	/** 对单个javabean进行解析
	 * @param <T>
	 * @param json 要解析的json字符串
	 * @param cls
	 * @return */
	public static <T> T getObj(String json, Class<T> cls) {
		T t = null;
		try {
			t = JSON.parseObject(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/** 对list类型进行解析
	 * @param <T>
	 * @param json
	 * @param cls
	 * @return */
	public static <T> List<T> getListObj(String json, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		if (!TextUtils.isEmpty(json)) {
			try {
				list = JSON.parseArray(json, cls);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/** 对MapString类型数据进行解析
	 * @param json
	 * @return */
	public static Map<String, String> getMapStr(String json) {
		Map<String, String> mapStr = new HashMap<String, String>();
		if (!TextUtils.isEmpty(json)) {
			try {
				mapStr = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapStr;
	}

	/** 对MapObject类型数据进行解析
	 * @param json
	 * @return */
	public static Map<String, Object> getMapObj(String json) {
		Map<String, Object> mapStr = new HashMap<String, Object>();
		if (!TextUtils.isEmpty(json)) {
			try {
				mapStr = JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapStr;
	}

	/** 对listmap类型进行解析
	 * @param json
	 * @return */
	public static List<Map<String, Object>> getListMap(String json) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (!TextUtils.isEmpty(json)) {
			try {
				list = JSON.parseObject(json, new TypeReference<List<Map<String, Object>>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<Map<String, String>> getListMapStr(String json) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (!TextUtils.isEmpty(json)) {
			try {
				list = JSON.parseObject(json, new TypeReference<List<Map<String, String>>>() {
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
